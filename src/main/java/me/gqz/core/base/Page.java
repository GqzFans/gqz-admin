package me.gqz.core.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: Page. </p>
 * <p>Description 分页入参 </p>
 * @author dragon
 * @date 2018/7/12 下午3:05
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 4328830211330646129L;
    @ApiModelProperty("页码")
    private Integer pageNum;
    @ApiModelProperty("页面大小")
    private Integer pageSize;
    @ApiModelProperty("查询参数")
    private T param;
}
