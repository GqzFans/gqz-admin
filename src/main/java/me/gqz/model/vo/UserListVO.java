package me.gqz.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: UserListVO. </p>
 * <p>Description 用户管理列表VO </p>
 * @author dragon
 * @date 2018/7/12 下午2:54
 */
@Data
public class UserListVO implements Serializable {

    private static final long serialVersionUID = -669461370694550310L;
    /**
     * 主键ID
     */
    private String id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 版本号
     */
    private String version;

    /**
     * 系统来源
     */
    private String systemId;

    /**
     * 注册信息发送邮件
     */
    private String mail;
}
