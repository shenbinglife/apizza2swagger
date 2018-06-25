package apizza2swagger.domain.swagger;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class ResponseDetail {

    private String description = "";

    private Scheme schema;

    private Object examples;

    public Object getExamples() {
        return examples;
    }

    public void setExamples(Object examples) {
        this.examples = examples;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Scheme getSchema() {
        return schema;
    }

    public void setSchema(Scheme schema) {
        this.schema = schema;
    }
}
