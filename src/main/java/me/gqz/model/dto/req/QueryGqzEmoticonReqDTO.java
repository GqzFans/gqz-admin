package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: QueryGqzEmoticonReqDTO. </p>
 * <p>Description 表情包管理列表请求DTO </p>
 * @author dragon
 * @date 2018/7/16 上午11:00
 */
@Data
@ApiModel(value = "表情包管理列表请求DTO")
public class QueryGqzEmoticonReqDTO implements Serializable {

    private static final long serialVersionUID = -1287935677777220357L;

    @ApiModelProperty(value = "创建用户")
    private String createUserName;

    @ApiModelProperty(value = "开始时间")
    private String beginDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
