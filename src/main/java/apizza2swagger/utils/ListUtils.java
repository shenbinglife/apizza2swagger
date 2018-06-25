package apizza2swagger.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/4/12
 * @since since
 */
public class ListUtils {

    public static <T> List<T> newList(T ... t) {
        if(t == null) {
            return new ArrayList<>();
        } else {
            List<T> list = new ArrayList<>();
            list.addAll(Arrays.asList(t));
            return list;
        }
    }
}
