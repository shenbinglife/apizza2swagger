package apizza2swagger.domain;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import apizza2swagger.utils.GsonUtil;

import apizza2swagger.ApizzaHelper;
import apizza2swagger.domain.apizza.*;
import apizza2swagger.domain.swagger.*;
import apizza2swagger.domain.swagger.Properties;
import apizza2swagger.utils.ContentType;
import apizza2swagger.utils.ListUtils;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Converter {

    private Definitions definitions = new Definitions();

    private ApizzaHelper helper;

    public void convert(ApizzaHelper helper, String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        this.helper = helper;
        List<CooperProject> project = helper.getCooperProject();
        for (Project p : project) {
            FileUtils.writeStringToFile(new File(path + "\\" + p.getName() + ".json"), toSwagger(p).toJson(),
                            StandardCharsets.UTF_8, false);
        }
    }

    private Swagger toSwagger(Project p) throws Exception {
        definitions.clear();
        Swagger swagger = new Swagger();
        Info info = swagger.getInfo();
        info.setTitle(p.getName());
        List<ApiFolder> apiFolders = helper.getApiFolders(p.getId());
        swagger.setTags(apiFolders.stream().map(ApiFolder::getName).map(m -> {
            Tag tag = new Tag();
            tag.setName(m);
            return tag;
        }).collect(Collectors.toList()));

        Map<String, Path> paths = new HashMap<>();
        apiFolders.forEach(apiFolder -> {
            List<Api> api_list = apiFolder.getApi_list();
            for (Api api : api_list) {
                try {
                    ApiDetail apiDetail = helper.getApiDetail(api.getId());
                    apiDetail.setFolderName(apiFolder.getName());
                    String url = api.getUrl();
                    int i = url.indexOf("}}");
                    if (i > 0) {
                        url = url.substring(i + 2);
                    } else {
                        System.out.println(String.format("跳过API接口：[%s][%s][%s]", p.getName(), apiDetail.getName(),url));
                        continue;
                    }
                    i = url.indexOf("?");
                    if (i > 0) {
                        url = url.substring(0, i);
                    }
                    url = url.replaceAll("\\{\\{", "{").replaceAll("\\}\\}", "}");
                    paths.put(url, toPath(apiDetail));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        swagger.setPaths(paths);
        swagger.setDefinitions(definitions);
        return swagger;
    }

    private Path toPath(ApiDetail apiDetail) {
        Path path = new Path();
        path.put(apiDetail.getMethod().toLowerCase(), toSwaggerApiDetail(apiDetail));
        return path;
    }

    private SwaggerApiDetail toSwaggerApiDetail(ApiDetail apiDetail) {
        SwaggerApiDetail detail = new SwaggerApiDetail();
        detail.setDescription(apiDetail.getResponse_doc());
        detail.setSummary(apiDetail.getName());
        detail.setOperationId(apiDetail.getId());
        detail.setTags(ListUtils.newList(apiDetail.getFolderName()));
        List<String> consums = new ArrayList<>();
        if (StringUtils.isNotBlank(apiDetail.getBody_raw())) {
            consums.add(ContentType.JSON);
        }
        if (!apiDetail.getBody_type().equalsIgnoreCase("raw")) {
            consums.add(rebuildBodyType(apiDetail.getBody_type()));
        }
        detail.setConsumes(consums);
        if (StringUtils.isNotBlank(apiDetail.getResponse_example())) {
            detail.setProduces(ListUtils.newList(ContentType.JSON));
        }
        List<Parameter> list = new ArrayList<>();

        List<BodyParam> header_params = apiDetail.getHeader_params();
        if (header_params != null) {
            List<Parameter> collect = header_params.stream().map(header -> {
                Parameter parameter = new Parameter();
                parameter.setIn("header");
                parameter.setType(header.getType());
                parameter.setName(header.getKey());
                parameter.setRequired(header.getRequire() == 1);
                parameter.setDescription(header.getDesc());
                parameter.setDefaults(header.getValue());
                return parameter;
            }).collect(Collectors.toList());
            list.addAll(collect);
        }
        List<BodyParam> query_params = apiDetail.getQuery_params();
        if (query_params != null) {
            List<Parameter> collect = query_params.stream().map(query -> {
                Parameter parameter = new Parameter();
                parameter.setIn("query");
                parameter.setType(query.getType());
                parameter.setName(query.getKey());
                parameter.setRequired(query.getRequire() == 1);
                parameter.setDescription(query.getDesc());
                parameter.setDefaults(query.getValue());
                return parameter;
            }).collect(Collectors.toList());
            list.addAll(collect);
        }

        String url = apiDetail.getUrl();
        Map<String, String> urlParams = getUrlParams(url);
        for (String s : urlParams.keySet()) {
            boolean b = query_params != null && query_params.stream().map(ResponseParam::getKey)
                            .anyMatch(key -> key.equalsIgnoreCase(s));
            if (!b) {
                Parameter p = new Parameter();
                p.setIn("query");
                p.setName(s);
                p.setType("string");
                p.setDefaults(urlParams.get(s));
                list.add(p);
            }
        }
        String rebuidUrl = rebuildUrl(url);
        int idex = -1;
        while ((idex = rebuidUrl.indexOf("{")) > 0) {
            int edex = rebuidUrl.indexOf("}");
            String pathP = rebuidUrl.substring(idex + 1, edex);
            boolean b = list.stream().map(Parameter::getName).anyMatch(key -> key.equalsIgnoreCase(pathP));
            if (!b) {
//                System.out.println("添加path参数：" + pathP);
                Parameter p = new Parameter();
                p.setIn("path");
                p.setName(pathP);
                p.setType("string");
                p.setRequired(true);
                list.add(p);
            }
            rebuidUrl = rebuidUrl.substring(edex + 1);
        }


        List<BodyParam> body_params = apiDetail.getBody_params();
        List<ResponseParam> response_params = apiDetail.getResponse_example_params();

        String requestRowExample = apiDetail.getBody_raw_example();
        String responseRowExample = apiDetail.getResponse_example();

        String bodyType = apiDetail.getBody_type();
        if ("raw".equals(bodyType)) {
            Parameter p = toBodyParam(body_params, requestRowExample);
            list.add(p);
        } else if ("x-www-form-urlencoded".equals(bodyType) || "form-data".equals(bodyType)) {
            if (body_params != null) {
                List<Parameter> collect = body_params.stream().map(query -> {
                    Parameter parameter = new Parameter();
                    parameter.setIn("formData");
                    parameter.setType(query.getType());
                    parameter.setName(query.getKey());
                    parameter.setRequired(query.getRequire() == 1);
                    parameter.setDescription(query.getDesc());
                    parameter.setDefaults(query.getValue());
                    return parameter;
                }).collect(Collectors.toList());
                list.addAll(collect);
            }
        }
        detail.setParameters(list);
        Map<String, ResponseDetail> response = new HashMap<>();
        response.put("200", toResponseDetail(response_params, responseRowExample));
        detail.setResponses(response);
        return detail;
    }

    private String rebuildUrl(String url) {
        int i = url.indexOf("}}");
        if (i > 0) {
            url = url.substring(i + 2);
        } else {
            throw new IllegalArgumentException("不支持解析路径参数的url" + url);
        }
        i = url.indexOf("?");
        if (i > 0) {
            url = url.substring(0, i);
        }
        url = url.replaceAll("\\{\\{", "{").replaceAll("\\}\\}", "}");
        return url;
    }

    private Map<String, String> getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        int index = url.indexOf("?");
        if (index > 0) {
            String param = url.substring(index + 1);
            String[] params = param.split("&");
            for (int i = 0; i < params.length; i++) {
                String[] p = params[i].split("=");
                if (p.length == 2) {
                    map.put(p[0], p[1]);
                }
            }
        }
        return map;
    }

    private String rebuildBodyType(String body_type) {
        String t = null;
        if (body_type.contains("form")) {
            return "multipart/form-data";
        } else if (body_type.contains("www")) {
            return "application/x-www-form-urlencoded";
        } else {
            return null;
        }
    }

    private ResponseDetail toResponseDetail(List<ResponseParam> response_params, String responseRowExample) {
        ResponseDetail responseDetail = new ResponseDetail();
        responseDetail.setSchema(toResponseScheme(response_params, responseRowExample));
        return responseDetail;
    }

    private boolean isJson(String responseRowExample) {
        return responseRowExample.startsWith("{") || responseRowExample.startsWith("[");
    }

    private boolean isJsonObj(String json) {
        return json.startsWith("{");
    }

    private boolean isJsonArr(String json) {
        return json.startsWith("[");
    }

    private Parameter toBodyParam(List<BodyParam> body_params, String row) {
        Parameter parameter = new Parameter();
        parameter.setIn("body");
        parameter.setName("body");
        List<ResponseParam> l = body_params == null ? new ArrayList<>() : body_params.stream().map(b -> {
            ResponseParam responseParam = new ResponseParam();
            responseParam.setDesc(b.getDesc());
            responseParam.setKey(b.getKey());
            responseParam.setType(b.getType());
            return responseParam;
        }).collect(Collectors.toList());

        parameter.setSchema(toResponseScheme(l, row));
        return parameter;
    }

    private Scheme toResponseScheme(List<ResponseParam> body_params, String json) {
        if(StringUtils.isBlank(json)) {
            return null;
        }
        Scheme scheme = new Scheme();
        if (isJsonObj(json)) {
            scheme.set$ref(to$Ref(body_params, json, ""));
        } else if (isJsonArr(json)) {
            scheme.setType("array");
            Scheme item = new Scheme();
            item.set$ref(to$Ref(body_params, json, ""));
            scheme.setItems(Arrays.asList(item));
        }
        return scheme;
    }

    private String to$Ref(List<ResponseParam> body_params, String json, String parent) {
        String uid = UUID.randomUUID().toString();
        String UUID = "#/definitions/" + uid;

        definitions.put(uid, toDefinition(body_params, json, parent));

        return UUID;
    }

    private Definition toDefinition(List<ResponseParam> body_params, String json, String parent) {
        if (body_params == null) {
            body_params = new ArrayList<>();
        }
        Object obj = GsonUtil.fromJson(json);
        while (obj instanceof List) {
            try {
                obj = ((List) obj).get(0);
            } catch (IndexOutOfBoundsException e) {
                Definition definition = new Definition();
                definition.setType("array");
                definition.setType("string");
                return definition;
            }
        }
        if (isSimpleType(obj)) {
            Definition definition = new Definition();
            definition.setType("array");
            definition.setType("string");
            return definition;
        }
        Definition definition = new Definition();
        definition.setType("object");
        Set<? extends Map.Entry<?, ?>> entries = ((Map<?, ?>) obj).entrySet();
        for (Map.Entry entry : entries) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (isSimpleType(v)) {
                Properties properties = new Properties();
                properties.setType(getType(body_params, k, parent));
                properties.setDescription(getDesc(body_params, k, parent));
                definition.addProperty(k, properties);
            } else {
                Properties properties = new Properties();
                properties.set$ref(to$Ref(body_params, GsonUtil.toJson(v),
                                StringUtils.isBlank(parent) ? String.valueOf(k) : parent + "." + k));
                definition.addProperty(k, properties);
            }
        }

        return definition;
    }

    private String getDesc(List<ResponseParam> body_params, Object k, String parent) {
        return body_params.stream().filter(p -> p.getKey().equals(StringUtils.isBlank(parent) ? k : parent + "." + k))
                        .map(ResponseParam::getDesc).findAny().orElse("");
    }

    private String getType(List<ResponseParam> body_params, Object k, String parent) {
        if (body_params == null) {
            return "string";
        }
        return body_params.stream().filter(p -> {
            String name = StringUtils.isBlank(parent) ? String.valueOf(k) : parent + "." + k;
            return p.getKey() != null && p.getKey().equals(name);
        }).findAny().map(ResponseParam::getType).orElse("string");
    }

    private boolean isSimpleType(Object v) {
        return v == null || ClassUtils.isPrimitiveOrWrapper(v.getClass()) || v instanceof String;
    }

}
