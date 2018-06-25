package apizza2swagger.domain.swagger;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class Properties {

    private String name;

    private String type;

    private String foramt;

    private String description;

    private String $ref;

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getForamt() {
        return foramt;
    }

    public void setForamt(String foramt) {
        this.foramt = foramt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
