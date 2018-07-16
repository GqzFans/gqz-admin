package me.gqz.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: DashBoardDataStatisticsVO. </p>
 * <p>Description 高秋梓资源站数据统计 </p>
 * @author dragon
 * @date 2018/7/16 下午2:35
 */
@Data
@ApiModel("高秋梓资源站数据统计")
public class DashBoardDataStatisticsVO implements Serializable {

    private static final long serialVersionUID = 2517694310189917974L;

    @ApiModelProperty("图片数量")
    private Integer gqzImageCount;

    @ApiModelProperty("表情包数量")
    private Integer gqzEmoticonCount;

    @ApiModelProperty("视频数量")
    private Integer gqzVideoCount;

    @ApiModelProperty("图片上传占比（月统计）")
    private Double uploadImageMonthPercentage;

    @ApiModelProperty("表情包上传占比（月统计）")
    private Double uploadEmoticonMonthPercentage;

    @ApiModelProperty("视频上传占比（月统计）")
    private Double uploadVideoMonthPercentage;
}
