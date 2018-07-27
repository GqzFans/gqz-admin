package me.gqz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: GqzAppVideo. </p>
 * @author dragon
 * @date 2018/7/26 下午2:21
 */
@Data
@Table(name = "gqz_app_video")
public class GqzAppVideo implements Serializable {
    private static final long serialVersionUID = -2900920868092130301L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 视频地址
     */
    @Column(name = "video_url")
    @ApiModelProperty(value = "视频地址")
    private String videoUrl;

    /**
     * 视频vid
     */
    @Column(name = "video_vid")
    @ApiModelProperty(value = "视频vid")
    private String videoVid;

    /**
     * 视频类型
     */
    @Column(name = "video_type")
    @ApiModelProperty(value = "视频类型")
    private String videoType;

    /**
     * 视频标题
     */
    @Column(name = "video_title")
    @ApiModelProperty(value = "视频标题")
    private String videoTitle;

    /**
     * 视频状态
     */
    @Column(name = "video_status")
    @ApiModelProperty(value = "视频状态")
    private String videoStatus;

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
    @ApiModelProperty(value = "版本号")
    @Column(name = "version")
    private Integer version;

}