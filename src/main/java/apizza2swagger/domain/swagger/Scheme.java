package apizza2swagger.domain.swagger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apizza2swagger.domain.apizza.ResponseParam;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Scheme {

    private String $ref;

    private String type;

    private List<String> required;

    private Map<String, Object> properties;

    private List<Scheme> items;

    private Object example;

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public List<Scheme> getItems() {
        return items;
    }

    public void setItems(List<Scheme> items) {
        this.items = items;
    }

    public Object getExample() {
        return example;
    }

    public void setExample(Object example) {
        this.example = example;
    }

    public void addProperty(Object k, Properties v) {
        if(properties == null) {
            properties = new HashMap<>();
        }
        properties.put(String.valueOf(k), v);
    }
}
