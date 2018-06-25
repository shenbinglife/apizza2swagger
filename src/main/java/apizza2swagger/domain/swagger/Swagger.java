package apizza2swagger.domain.swagger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import apizza2swagger.utils.GsonUtil;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Swagger {

    private String swagger="2.0";

    private String host = "127.0.0.1";

    private String basePath = "/v1";

    private List<Tag> tags;

    private List<String> schemes = Arrays.asList("http");

    private Map<String, Path> paths;

    private SecurityDefinitions securityDefinitions;

    private Definitions definitions;

    private ExternalDocs externalDocs;

    private Info info = new Info();

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getSwagger() {
        return swagger;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<String> schemes) {
        this.schemes = schemes;
    }

    public Map<String, Path> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Path> paths) {
        this.paths = paths;
    }

    public SecurityDefinitions getSecurityDefinitions() {
        return securityDefinitions;
    }

    public void setSecurityDefinitions(SecurityDefinitions securityDefinitions) {
        this.securityDefinitions = securityDefinitions;
    }

    public Definitions getDefinitions() {
        return definitions;
    }

    public void setDefinitions(Definitions definitions) {
        this.definitions = definitions;
    }

    public ExternalDocs getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocs externalDocs) {
        this.externalDocs = externalDocs;
    }

    public String toJson() {
        return GsonUtil.toJson(this);
    }
}
