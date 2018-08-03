package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: OperateGqzWsDataReqDTO. </p>
 * <p>Description 数据站操作微视数据分析请求DTO </p>
 * @author dragon
 * @date 2018/8/2 下午10:54
 */
@Data
@ApiModel(value = "数据站操作微视数据分析请求DTO")
public class OperateGqzWsDataReqDTO implements Serializable {

    private static final long serialVersionUID = -3884105078442043565L;
    @ApiModelProperty(value = "微视分析ID")
    private String id;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "开启或关闭数据统计操作符")
    private String model;
}
