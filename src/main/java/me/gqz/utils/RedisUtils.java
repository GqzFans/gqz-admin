package me.gqz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dragon
 * @date 2018年03月30日22:34:57
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate rt;

    /**
     * <p>Title: getNowCode. </p>
     * <p>获取最新code </p>
     * @param envLock
     * @param prefix
     * @param prefixLen
     * @author dragon
     * @date 2018/7/13 下午3:23
     * @return str
     */
    public String getNowCode(String envLock, String prefix, int prefixLen) {
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String dateStr = df.format(new Date());
        String key = envLock + prefix + dateStr;
        String valuePrefix = prefix + dateStr;
        Long newValue = 1L;
        try {
            synchronized (this) {
                ValueOperations<String, String> ops  = rt.opsForValue();
                if (!rt.hasKey(key)) {
                    ops.set(key, String.valueOf(1L));
                    rt.expire(key, 1L, TimeUnit.DAYS);
                } else {
                    newValue = ops.increment(key, 1L);
                }
            }
        } catch (Exception ex) {
            log.error("Redis生成序列号发生错误", ex);
        }
        String zeroStr = "";
        if (null != newValue && prefixLen > 0 && String.valueOf(newValue).length() < prefixLen) {
            for (int i = 0; i < (prefixLen - String.valueOf(newValue).length()); i++) {
                zeroStr += "0";
            }
        }
        return valuePrefix + zeroStr + newValue;
    }

    /**
     * <p>Title: getUserSerialNo. </p>
     * <p>Redis生成用户账号 </p>
     * @author dragon
     * @date 2018/4/8 下午5:59
     */
    public String getUserSerialNo() {
        Long newValue = 1L;
        String key = "UAC_USER_SERIAL_NO";
        try {
            synchronized (this) {
                ValueOperations<String, String> ops  = rt.opsForValue();
                if (!rt.hasKey(key)) {
                    ops.set(key, String.valueOf(1L));
                } else {
                    newValue = ops.increment(key, 1L);
                }
            }
        } catch (Exception ex) {
            log.error("Redis生成用户账号发生错误", ex);
        }
        return newValue.toString();
    }

}
