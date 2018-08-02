package me.gqz.model.vo;

import lombok.Data;
import me.gqz.domain.GqzTencentWsData;

/**
 * <p>Title: GqzTencentWsDataVO. </p>
 * @author dragon
 * @date 2018/8/2 下午10:20
 */
@Data
public class GqzTencentWsDataVO extends GqzTencentWsData {
    /**
     * 实时查询播放量
     */
    private Integer playNum;
}
