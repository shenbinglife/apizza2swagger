package apizza2swagger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import apizza2swagger.domain.apizza.*;
import apizza2swagger.utils.MapUtils;
import apizza2swagger.utils.UriUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apizza2swagger.utils.GsonUtil;

import okhttp3.*;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class ApizzaHelper {

    private static final Logger logger = LoggerFactory.getLogger(ApizzaHelper.class);

    private String email;

    private String password;

    private String exportPath = "d:";

    private String token;

    private User user;

    private OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES).build();

    public ApizzaHelper(String email, String password, String exportPath) {
        this.email = email;
        this.password = password;
        this.exportPath = exportPath;
    }

    /**
     * 获取个人项目
     */
    public List<Project> getProject() throws Exception {
        String token = getToken();
        FormBody formBody = new FormBody.Builder().add("token", token).build();

        Response response = client.newCall(new Request.Builder().url(ApizzaCons.PROJECT_LIST).post(formBody).build())
                        .execute();
        String res = response.body().string();
        ApizzaData apizzaData = GsonUtil.fromJson(res, ApizzaData.class);
        if (apizzaData.isSuccess()) {
            Map<String, Object> objectMap = (Map<String, Object>) apizzaData.getData();
            List<Map> project_list = (List<Map>) objectMap.get("project_list");
            return project_list.stream().map(map -> MapUtils.map2bean(map, Project.class)).collect(Collectors.toList());
        } else {
            logger.info(res);
            throw new SimpleException(apizzaData.getInfo());
        }
    }

    public List<CooperProject> getCooperProject() throws Exception {
        List<CooperProject> cooperProjectApizzaData =
                        simplePostWithToken(ApizzaCons.COOPER_PROJECT, "project_list", List.class, CooperProject.class);
        return cooperProjectApizzaData;
    }

    public List<ApiFolder> getApiFolders(String projectId) throws Exception {
        String url = ApizzaCons.FOLDER_LIST + "?project_id=" + projectId;
        return simplePostWithToken(url, "list", List.class, ApiFolder.class);
    }

    public ApiDetail getApiDetail(String apiId) throws Exception {
        String url = ApizzaCons.API_DETAIL + "?autoversion=1&id=" + apiId;
        return simplePostWithToken(url, "", ApiDetail.class);
    }

    private <T> T simplePostWithToken(String url, String node, Class<T> f, Class<?>... t) throws Exception {
        String token = getToken();
        Map<String, String> params = UriUtils.getQueryMap(url);
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for(String key : params.keySet()) {
            formBodyBuilder.add(key, params.get(key));
        }
        FormBody formBody = formBodyBuilder.add("token", token).build();



        Response response = client.newCall(new Request.Builder().url(url).post(formBody).build()).execute();
        String res = response.body().string();
        ApizzaData<Map> data = GsonUtil.fromJson(res,
                        new GsonUtil.ParameterizedTypeImpl(ApizzaData.class, new Class[] {Map.class}));
        if (!data.isSuccess()) {
            throw new SimpleException(data.getInfo());
        }
        Object jsonMap = StringUtils.isBlank(node) ? data.getData() : data.getData().get(node);
        String nodeJsonString = GsonUtil.toJson(jsonMap);
        T tdata;
        if(t == null || t.length == 0) {
            tdata = GsonUtil.fromJson(nodeJsonString, f);
        } else {
            tdata = GsonUtil.fromJson(nodeJsonString, new GsonUtil.ParameterizedTypeImpl(f, t));
        }
        return tdata;
    }

    /**
     * 生成Token
     */
    public String getToken() throws Exception {
        if (token == null) {
            FormBody.Builder formBody = new FormBody.Builder();// 创建表单请求体
            formBody.add("name", "").add("email", email).add("password", password);
            Request request = new Request.Builder().post(formBody.build())
                            .addHeader("X-Requested-With", "XMLHttpRequest").url(ApizzaCons.LOGIN_URL).build();
            Response response = client.newCall(request).execute();
            String res = response.body().string();

            ApizzaData apizzaData = GsonUtil.fromJson(res, ApizzaData.class);
            if (apizzaData.isSuccess()) {
                User u = new User();
                BeanUtils.populate(u, (Map<String, ? extends Object>) apizzaData.getData());

                this.token = u.getToken();
                this.user = u;
                checkToken(u.getToken(), u.getId());
                return u.getToken();
            } else {
                throw new SimpleException(apizzaData.getInfo());
            }
        }
        return token;
    }

    private void checkToken(String token, String id) throws IOException {
        HttpUrl url = HttpUrl.parse(ApizzaCons.CHECK_TOKEN).newBuilder().addQueryParameter("token", token)
                        .addQueryParameter("user_id", id).build();
        logger.trace("check token url: {}", url.toString());
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        ApizzaData apizzaData = GsonUtil.fromJson(res, ApizzaData.class);
        logger.trace("apizza check token response: {}", apizzaData);
        if (apizzaData.isSuccess()) {
            updateToken();
            return;
        } else {
            throw new SimpleException(apizzaData.getInfo());
        }
    }

    private void updateToken() throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("name", user.getName()).add("email", email).add("password", password).add("token", token)
                        .add("user_id", user.getId());
        Request request = new Request.Builder().post(formBody.build()).url(ApizzaCons.UPDATE_TOKEN).build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        ApizzaData apizzaData = GsonUtil.fromJson(string, ApizzaData.class);
        logger.trace("update token success: {}, info: {}", response.isSuccessful(), apizzaData.getInfo());
    }

    public static void main(String[] args) throws Exception {
        ApizzaHelper apizzaHelper = new ApizzaHelper("shenbinglife@163.com", "96e79218965eb72c92a549dd5a330112", null);
        // apizzaHelper.getCooperProject();
        System.out.println(apizzaHelper.simplePostWithToken(ApizzaCons.PROJECT_LIST, "project_list", List.class,
                        Project.class));
        System.out.println(apizzaHelper.getCooperProject());

//        System.out.println(apizzaHelper.getApiFolders("d6c2e5829a0c9cc638b890866c50ac34"));
        System.out.println(apizzaHelper.getApiDetail("bf767f83beda4c3b941a8113e73dd9e6"));
    }
}
