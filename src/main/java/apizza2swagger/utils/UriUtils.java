package apizza2swagger.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class UriUtils {

    public static Map<String, String> getQueryMap(String url) {
        Map<String, String> map = new HashMap<String, String>();
        int i = url.indexOf("?");
        if (i < 0) {
            return map;
        }
        String query = url.substring(i + 1);
        String[] params = query.split("&");
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}
