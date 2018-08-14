package me.gqz.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: BeanListUtils. </p>
 * @author dragon
 * @date 2018/8/2 下午10:26
 */
public class BeanListUtils {

    /**
     * <p>Title: copyTo. </p>
     * <p>复制集合 </p>
     * @param source
     * @param destinationClass
     * @author net
     * @Date 2018/3/4 15:15
     * @return List<E>
     */
    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) throws IllegalAccessException, InvocationTargetException, InstantiationException{
        if (source.size() == 0) {
            return Collections.emptyList();
        }
        List<E> res = new ArrayList<E>(source.size());
        for (Object o : source) {
            E e = destinationClass.newInstance();
            BeanUtils.copyProperties(o, e);
            res.add(e);
        }
        return res;
    }

}
