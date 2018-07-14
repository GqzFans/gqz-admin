package me.gqz.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.gqz.model.dto.res.AttachmentResDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: InsertGqzImageReqDTO. </p>
 * <p>Description 资源站添加图片请求DTO </p>
 * @author dragon
 * @date 2018/7/14 下午3:45
 */
@Data
@ApiModel(value = "资源站添加图片请求DTO")
public class InsertGqzImageReqDTO implements Serializable {

    @ApiModelProperty(value = "图片说明文案")
    private String imageDescription;

    @ApiModelProperty(value = "图片集合")
    private List<AttachmentResDTO> imageList;
}
