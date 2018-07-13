package me.gqz.model.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>Title: AttachmentReqDTO. </p>
 * <p>Description 上传附件回传DTO </p>
 * @author dragon
 * @date 2018/7/13 下午2:42
 */
@Data
@ApiModel("上传附件回传DTO")
public class AttachmentReqDTO implements Serializable {
    private static final long serialVersionUID = 4231782417155280127L;
    /**
     * 附件后缀
     */
    @ApiModelProperty("附件后缀")
    private String type;

    /**
     * 附件名称
     */
    @ApiModelProperty("附件名称")
    private String attachmentName;

    /**
     * 附件业务流水号
     */
    @ApiModelProperty("附件业务流水号")
    private String attachmentCode;

    /**
     * 附件OSS地址头
     */
    @ApiModelProperty("附件OSS地址头")
    private String attachmentUrl;
}
