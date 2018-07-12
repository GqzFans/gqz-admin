package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.gqz.constant.SystemBaseConstants;

import java.io.Serializable;

/**
 * <p>Title: DropUserReqDTO. </p>
 * <p>Description 停用用户登录请求DTO </p>
 * @author dragon
 * @date 2018/7/12 下午4:25
 */
@Data
@ApiModel(value = "停用用户登录请求DTO")
public class DropUserReqDTO implements Serializable {

    private static final long serialVersionUID = 8649388755938706491L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态-停用状态")
    private String userStatus = SystemBaseConstants.N;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String version;
}
