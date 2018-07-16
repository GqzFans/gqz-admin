package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.gqz.model.dto.res.AttachmentResDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: InsertGqzEmoticonReqDTO. </p>
 * <p>Description 资源站添加表情包请求DTO </p>
 * @author dragon
 * @date 2018/7/16 上午10:36
 */
@Data
@ApiModel(value = "资源站添加表情包请求DTO")
public class InsertGqzEmoticonReqDTO implements Serializable {

    private static final long serialVersionUID = -1436941998053947845L;

    @ApiModelProperty(value = "表情包说明文案")
    private String emoticonDescription;

    @ApiModelProperty(value = "表情包集合，目前仅限单张上传")
    private List<AttachmentResDTO> imageList;
}
