package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: QueryGqzImageReqDTO. </p>
 * <p>Description 图片管理列表请求DTO </p>
 * @author dragon
 * @date 2018/7/14 下午11:30
 */
@Data
@ApiModel(value = "图片管理列表请求DTO")
public class QueryGqzImageReqDTO implements Serializable {

    private static final long serialVersionUID = 4462986559954838739L;

    @ApiModelProperty(value = "创建用户")
    private String createUserName;

    @ApiModelProperty(value = "开始时间")
    private String beginDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
