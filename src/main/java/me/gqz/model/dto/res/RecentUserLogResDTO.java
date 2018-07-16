package me.gqz.model.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>Title: RecentUserLogResDTO. </p>
 * <p>Description 最近用户登录信息 </p>
 * @author dragon
 * @date 2018/7/16 下午1:52
 */
@Data
@ApiModel("最近用户登录信息")
public class RecentUserLogResDTO implements Serializable {

    private static final long serialVersionUID = 8918675337413649362L;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("登录IP地址")
    private String loginIp;

    @ApiModelProperty("登录时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String loginTime;
}
