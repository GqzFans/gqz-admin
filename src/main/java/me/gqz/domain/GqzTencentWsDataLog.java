package me.gqz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: GqzTencentWsDataLog. </p>
 * @author dragon
 * @date 2018/8/3 下午10:07
 */
@Data
@Table(name = "gqz_tencent_ws_data_log")
public class GqzTencentWsDataLog implements Serializable {
    private static final long serialVersionUID = 2683536772202696198L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 微视短视频ID
     */
    @Column(name = "ws_video_id")
    @ApiModelProperty(value = "微视短视频ID")
    private String wsVideoId;

    /**
     * 任务处理时间
     */
    @Column(name = "worker_date")
    @ApiModelProperty(value = "任务处理时间")
    private Date workerDate;

    /**
     * 任务处理时间描述
     */
    @Column(name = "worker_date_description")
    @ApiModelProperty(value = "任务处理时间描述")
    private String workerDateDescription;

    /**
     * 微视短视频播放量
     */
    @Column(name = "ws_play_num")
    @ApiModelProperty(value = "微视短视频播放量")
    private Integer wsPlayNum;

}