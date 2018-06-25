package apizza2swagger.domain.apizza;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class ApizzaData<T> {

    private String info;

    private int status;

    private T data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return 1 == status;
    }

    @Override
    public String toString() {
        return "ApizzaData{" +
                "info='" + info + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
