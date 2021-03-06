package apizza2swagger.domain.swagger;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/20
 * @since since
 */
public class Info {

    private String description = "";
    private String version = "";

    private String title = "";

    private String termsOfService="";


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }
}
