package apizza2swagger.domain.apizza;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Api {

    private String api_role;

    private String category_id;

    private String id;

    private String method;

    private String name;

    private String progress;

    private String project_id;

    private String rank;

    private String status;

    private String type;

    private String url;

    private String user_id;

    private String version;

    public String getApi_role() {
        return api_role;
    }

    public void setApi_role(String api_role) {
        this.api_role = api_role;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Api{" +
                "api_role='" + api_role + '\'' +
                ", category_id='" + category_id + '\'' +
                ", id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", progress='" + progress + '\'' +
                ", project_id='" + project_id + '\'' +
                ", rank='" + rank + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", user_id='" + user_id + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
