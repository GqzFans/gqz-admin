package me.gqz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: GqzAppImage. </p>
 * @author dragon
 * @date 2018/7/14 下午4:19
 */
@Data
@Table(name = "gqz_app_image")
public class GqzAppImage implements Serializable {
    private static final long serialVersionUID = 7094722363548224119L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 图片网络路径
     */
    @Column(name = "image_url")
    @ApiModelProperty(value = "图片网络路径")
    private String imageUrl;

    /**
     * 图片描述
     */
    @Column(name = "image_description")
    @ApiModelProperty(value = "图片描述")
    private String imageDescription;

    /**
     * 创建人ID
     */
    @Column(name = "create_user_id")
    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    /**
     * 创建人昵称
     */
    @Column(name = "create_user_name")
    @ApiModelProperty(value = "创建人昵称")
    private String createUserName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 图片状态
     */
    @Column(name = "image_status")
    @ApiModelProperty(value = "图片状态")
    private String imageStatus;

    /**
     * 更新人ID
     */
    @Column(name = "update_user_id")
    @ApiModelProperty(value = "更新人ID")
    private String updateUserId;

    /**
     * 更新人昵称
     */
    @Column(name = "update_user_name")
    @ApiModelProperty(value = "更新人昵称")
    private String updateUserName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 版本号
     */
    @Column(name = "version")
    @ApiModelProperty(value = "版本号")
    private Integer version;
}