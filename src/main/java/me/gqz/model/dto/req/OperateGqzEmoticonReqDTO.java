package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: OperateGqzEmoticonReqDTO. </p>
 * <p>Description 资源站操作表情包请求DTO </p>
 * @author dragon
 * @date 2018/7/16 上午11:23
 */
@Data
@ApiModel(value = "资源站操作表情包请求DTO")
public class OperateGqzEmoticonReqDTO implements Serializable {

    private static final long serialVersionUID = -6294799674927565993L;

    @ApiModelProperty(value = "表情包ID")
    private String id;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "下架或上架操作符")
    private String model;
}
