package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: UacChangePasswordReqDTO. </p>
 * @author dragon
 * @date 2018/7/23 下午5:59
 */
@Data
@ApiModel(value = "修改用户密码请求DTO")
public class UacChangePasswordReqDTO implements Serializable {

    private static final long serialVersionUID = -8177519131818346258L;
    @ApiModelProperty(value = "流水号")
    private String serialNo;

    @ApiModelProperty(value = "原始密码")
    private String loginPwd;

    @ApiModelProperty(value = "用户新密码")
    private String newLoginPwd;
}
