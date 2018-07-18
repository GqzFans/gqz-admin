package me.gqz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: GqzAppFeedback. </p>
 * @author dragon
 * @date 2018/7/18 下午5:03
 */
@Data
@Table(name = "gqz_app_feedback")
public class GqzAppFeedback implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 微信昵称
     */
    @Column(name = "nick_name")
    @ApiModelProperty(value = "微信昵称")
    private String nickName;

    /**
     * 意见内容
     */
    @Column(name = "feedback_content")
    @ApiModelProperty(value = "意见内容")
    private String feedbackContent;

    /**
     * 发布时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}