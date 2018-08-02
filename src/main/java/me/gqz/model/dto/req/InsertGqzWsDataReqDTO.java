package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: InsertGqzWsDataReqDTO. </p>
 * <p>Description 数据站添加微视分析请求DTO </p>
 * @author dragon
 * @date 2018/8/2 下午6:05
 */
@Data
@ApiModel(value = "数据站添加微视分析请求DTO")
public class InsertGqzWsDataReqDTO implements Serializable {

    private static final long serialVersionUID = -104787620366132441L;
    /**
     * 微视短视频源地址
     */
    @ApiModelProperty(value = "微视短视频源地址")
    private String wsVideoUrl;

    /**
     * 微视短视频ID
     */
    @ApiModelProperty(value = "微视短视频ID")
    private String wsVideoId;

    /**
     * 微视短视频参考标题
     */
    @ApiModelProperty(value = "微视短视频参考标题")
    private String wsVideoTitle;
}
