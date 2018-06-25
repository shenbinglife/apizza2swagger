package apizza2swagger.domain.swagger;

import com.google.gson.annotations.SerializedName;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Parameter {

    private String name;

    private String in;

    private String description;

    private Boolean required;

    private Scheme schema;

    private String type;

    @SerializedName("default")
    private String defaults;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Scheme getSchema() {
        return schema;
    }

    public void setSchema(Scheme schema) {
        this.schema = schema;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", in='" + in + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                ", schema=" + schema +
                '}';
    }
}
