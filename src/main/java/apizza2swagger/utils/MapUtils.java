package apizza2swagger.utils;

import java.util.HashMap;
import java.util.Map;

import apizza2swagger.SimpleException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class MapUtils {

    public static <T> T map2bean(Map map, Class<T> clazz) {
        try {
            Object o = clazz.newInstance();
            BeanUtils.populate(o, map);
            return (T) o;
        } catch (Exception e) {
            throw new SimpleException(e.getMessage(), e);
        }
    }

    public static <T> T map2bean(Map map, T t) {
        try {
            BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            throw new SimpleException(e.getMessage(), e);
        }
    }

    public static <T,K> Map<T, K> newMap(T type, K type1) {
        Map <T, K> m = new HashMap<T, K>();
        m.put(type, type1);
        return m;
    }
}
