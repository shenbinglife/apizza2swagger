package apizza2swagger.domain.apizza;

/**
 * 协作项目
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class CooperProject extends Project {

    private String comment;

    private String create_time;

    private String role;

    private String status;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CooperProject{" +
                "comment='" + comment + '\'' +
                ", create_time='" + create_time + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                "} " + super.toString();
    }
}
