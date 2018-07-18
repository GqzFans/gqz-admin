package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: InsertGqzFeedbackReqDTO. </p>
 * <p>Description 小程序意见反馈请求DTO </p>
 * @author dragon
 * @date 2018/7/18 下午5:11
 */
@Data
@ApiModel(value = "小程序意见反馈请求DTO")
public class InsertGqzFeedbackReqDTO implements Serializable {

    private static final long serialVersionUID = -987570367347073216L;

    @ApiModelProperty(value = "微信用户昵称")
    private String nickName;

    @ApiModelProperty(value = "意见反馈")
    private String feedbackContent;
}
