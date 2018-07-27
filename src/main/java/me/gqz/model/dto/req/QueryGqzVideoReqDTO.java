package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: QueryGqzVideoReqDTO. </p>
 * <p>Description 视频管理列表请求DTO </p>
 * @author dragon
 * @date 2018/7/26 下午5:49
 */
@Data
@ApiModel(value = "视频管理列表请求DTO")
public class QueryGqzVideoReqDTO implements Serializable {

    private static final long serialVersionUID = -5098322714553839412L;
    @ApiModelProperty(value = "创建用户")
    private String createUserName;

    @ApiModelProperty(value = "开始时间")
    private String beginDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
