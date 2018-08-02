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

    private static final long serialVersionUID = -4221261606371327328L;

    @ApiModelProperty(value = "微视分析ID")
    private String id;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}
