package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>Title: OperateGqzVideoReqDTO. </p>
 * <p>Description 资源站操作视频请求DTO </p>
 * @author dragon
 * @date 2018/7/26 下午9:28
 */
@Data
@ApiModel(value = "资源站操作视频请求DTO")
public class OperateGqzVideoReqDTO implements Serializable {

    private static final long serialVersionUID = 6346370506335988860L;
    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "下架或上架操作符")
    private String model;
}
