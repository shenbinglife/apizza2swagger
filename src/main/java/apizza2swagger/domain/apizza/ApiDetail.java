package apizza2swagger.domain.apizza;

import java.util.List;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class ApiDetail {

    private String api_role;

    private List<BodyParam> body_params;

    private String body_raw;

    private String body_raw_example;

    private String body_type;

    private String category_id;

    private String create_time;

    private List<BodyParam> header_params;

    private String id;

    private String method;

    private String name;

    private String project_id;

    private Integer rank;

    private String raw_content_type;

    private String response_doc;

    private String response_example;

    private List<ResponseParam> response_example_params;

    private Integer status;

    private String type;

    private String url;

    private String version;

    private String folderName;

    private List<BodyParam> query_params;

    public List<BodyParam> getQuery_params() {
        return query_params;
    }

    public void setQuery_params(List<BodyParam> query_params) {
        this.query_params = query_params;
    }

    public String getApi_role() {
        return api_role;
    }

    public void setApi_role(String api_role) {
        this.api_role = api_role;
    }

    public List<BodyParam> getBody_params() {
        return body_params;
    }

    public void setBody_params(List<BodyParam> body_params) {
        this.body_params = body_params;
    }

    public String getBody_raw() {
        return body_raw;
    }

    public void setBody_raw(String body_raw) {
        this.body_raw = body_raw;
    }

    public String getBody_raw_example() {
        return body_raw_example;
    }

    public void setBody_raw_example(String body_raw_example) {
        this.body_raw_example = body_raw_example;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<BodyParam> getHeader_params() {
        return header_params;
    }

    public void setHeader_params(List<BodyParam> header_params) {
        this.header_params = header_params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRaw_content_type() {
        return raw_content_type;
    }

    public void setRaw_content_type(String raw_content_type) {
        this.raw_content_type = raw_content_type;
    }

    public String getResponse_doc() {
        return response_doc;
    }

    public void setResponse_doc(String response_doc) {
        this.response_doc = response_doc;
    }

    public String getResponse_example() {
        return response_example;
    }

    public void setResponse_example(String response_example) {
        this.response_example = response_example;
    }

    public List<ResponseParam> getResponse_example_params() {
        return response_example_params;
    }

    public void setResponse_example_params(List<ResponseParam> response_example_params) {
        this.response_example_params = response_example_params;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        return "ApiDetail{" +
                "api_role='" + api_role + '\'' +
                ", body_params=" + body_params +
                ", body_raw='" + body_raw + '\'' +
                ", body_raw_example='" + body_raw_example + '\'' +
                ", body_type='" + body_type + '\'' +
                ", category_id='" + category_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", header_params=" + header_params +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", project_id='" + project_id + '\'' +
                ", rank=" + rank +
                ", raw_content_type='" + raw_content_type + '\'' +
                ", response_doc='" + response_doc + '\'' +
                ", response_example='" + response_example + '\'' +
                ", response_example_params=" + response_example_params +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
