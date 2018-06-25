package apizza2swagger.domain.swagger;

import java.util.List;
import java.util.Map;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class SwaggerApiDetail {

    private List<String> tags;

    private String summary;

    private String description;

    private String operationId;

    private List<String>consumes;
    private List<String> produces;
    private List<Parameter> parameters;

    private Map<String, ?> responses;

    private List<Security> security;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public List<String> getConsumes() {
        return consumes;
    }

    public void setConsumes(List<String> consumes) {
        this.consumes = consumes;
    }

    public List<String> getProduces() {
        return produces;
    }

    public void setProduces(List<String> produces) {
        this.produces = produces;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Map<String, ?> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, ?> responses) {
        this.responses = responses;
    }

    public List<Security> getSecurity() {
        return security;
    }

    public void setSecurity(List<Security> security) {
        this.security = security;
    }

    @Override
    public String toString() {
        return "SwaggerApiDetail{" +
                "tags=" + tags +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", operationId='" + operationId + '\'' +
                ", consumes=" + consumes +
                ", produces=" + produces +
                ", parameters=" + parameters +
                ", responses=" + responses +
                ", security=" + security +
                '}';
    }
}
