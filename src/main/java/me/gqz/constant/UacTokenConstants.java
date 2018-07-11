package me.gqz.constant;

/**
 * <p>Title: UacTokenConstants. </p>
 * <p>Description 用户账户中心Token常量 </p>
 * @author dragon
 * @date 2018/3/30 下午9:52
 */
public class UacTokenConstants {
    // Redis中使用
    public static final String SECRET_TOKEN = "SECRET_TOKEN";
    // Redis中使用
    public static final String TOKEN_KEY = "TOKEN_KEY";
    // 将登录信息放进ThreadLocalMap
    public static final String JWT_TOKEN = "JWT_TOKEN";
    // 拦截器；通过TOKEN获取用户信息
    public static final String AUTH_USER_DTO = "AUTH_USER_DTO";
    // 设置COOKIE
    public static final String UAC_TOKEN = "UAC_TOKEN";
}
