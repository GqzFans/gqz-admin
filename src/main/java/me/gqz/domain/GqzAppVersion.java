package me.gqz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>Title: GqzAppVersion. </p>
 * @author dragon
 * @date 2018/7/16 下午3:26
 */
@Data
@Table(name = "gqz_app_version")
public class GqzAppVersion implements Serializable {
    private static final long serialVersionUID = -3772640011972817192L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 版本号
     */
    @Column(name = "version_name")
    @ApiModelProperty(value = "版本号")
    private String versionName;

    /**
     * 版本描述
     */
    @Column(name = "version_description")
    @ApiModelProperty(value = "版本描述")
    private String versionDescription;

    /**
     * 上线时间
     */
    @Column(name = "version_time")
    @ApiModelProperty(value = "上线时间")
    private String versionTime;
}