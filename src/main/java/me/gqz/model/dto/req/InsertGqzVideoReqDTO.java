package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: InsertGqzVideoReqDTO. </p>
 * <p>Description 资源站添加视频请求DTO </p>
 * @author dragon
 * @date 2018/7/26 下午5:26
 */
@Data
@ApiModel(value = "资源站添加视频请求DTO")
public class InsertGqzVideoReqDTO implements Serializable {

    private static final long serialVersionUID = 7498281495371316215L;
    @ApiModelProperty(value = "视频标题")
    private String videoTitle;

    @ApiModelProperty(value = "腾讯视频源地址")
    private String videoUrl;

    @ApiModelProperty(value = "视频VID")
    private String videoVid;

    @ApiModelProperty(value = "视频类型")
    private String videoType;
}
