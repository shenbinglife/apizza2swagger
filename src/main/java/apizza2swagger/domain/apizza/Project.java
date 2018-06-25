package apizza2swagger.domain.apizza;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class Project {

    private String id;
    private String is_open;
    private String name;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", is_open='" + is_open + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
