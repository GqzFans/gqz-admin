package me.gqz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: GqzTencentWsData. </p>
 * @author dragon
 * @date 2018/8/2 下午5:24
 */
@Data
@Table(name = "gqz_tencent_ws_data")
public class GqzTencentWsData implements Serializable {
    private static final long serialVersionUID = 7028982611950331151L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 微视短视频源地址
     */
    @Column(name = "ws_video_url")
    @ApiModelProperty(value = "微视短视频源地址")
    private String wsVideoUrl;

    /**
     * 微视短视频ID
     */
    @Column(name = "ws_video_id")
    @ApiModelProperty(value = "微视短视频ID")
    private String wsVideoId;

    /**
     * 微视短视频参考标题
     */
    @Column(name = "ws_video_title")
    @ApiModelProperty(value = "微视短视频参考标题")
    private String wsVideoTitle;

    /**
     * 微视短视频是否开启任务监听状态
     */
    @Column(name = "worker_listener_status")
    @ApiModelProperty(value = "微视短视频是否开启任务监听状态")
    private Integer workerListenerStatus;

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
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    @Column(name = "version")
    private Integer version;

}