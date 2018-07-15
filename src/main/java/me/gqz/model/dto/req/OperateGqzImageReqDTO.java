package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: OperateGqzImageReqDTO. </p>
 * <p>Description 资源站操作图片请求DTO </p>
 * @author dragon
 * @date 2018/7/15 下午5:58
 */
@Data
@ApiModel(value = "资源站操作图片请求DTO")
public class OperateGqzImageReqDTO implements Serializable {

    private static final long serialVersionUID = -4868903783416551034L;

    @ApiModelProperty(value = "图片ID")
    private String id;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "下架或上架操作符")
    private String model;
}
