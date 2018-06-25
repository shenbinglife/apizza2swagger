package apizza2swagger.domain.apizza;

import java.util.List;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class ApiFolder {

    private String id;
    private String name;
    private String comment;
    private Integer rank;

    private List<Api> api_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Api> getApi_list() {
        return api_list;
    }

    public void setApi_list(List<Api> api_list) {
        this.api_list = api_list;
    }

    @Override
    public String toString() {
        return "ApiFolder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", rank=" + rank +
                ", api_list=" + api_list +
                '}';
    }
}
