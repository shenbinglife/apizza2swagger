package apizza2swagger;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class SimpleException extends RuntimeException {

    public SimpleException(String message) {
        super(message, null, true, true);
    }

    public SimpleException(String message, Throwable cause) {
        super(message, cause, true, true);
    }


    public SimpleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
