package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzTencentWsData;

import java.util.List;

/**
 * <p>Title: GqzTencentWsDataMapper. </p>
 * @author dragon
 * @date 2018/8/2 下午5:25
 */
public interface GqzTencentWsDataMapper extends MyMapper<GqzTencentWsData> {
    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>开启数据统计 </p>
     * @param wsData
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Integer
     */
    Integer upWsDataWorkerListenerById(GqzTencentWsData wsData);

    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>关闭数据统计 </p>
     * @param wsData
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Integer
     */
    Integer dropWsDataWorkerListenerById(GqzTencentWsData wsData);

    /**
     * <p>Title: getStartWsData. </p>
     * <p>查询开启数据统计的微视短视频集合 </p>
     * @author dragon
     * @date 2018/8/3 下午10:22
     * @return List<GqzTencentWsData>
     */
    List<GqzTencentWsData> getStartWsData();

    /**
     * <p>Title: queryWsDataList. </p>
     * <p>查询分析列表 </p>
     * @author dragon
     * @date 2018/8/9 下午12:35
     * @return List<GqzTencentWsData>
     */
    List<GqzTencentWsData> queryWsDataList();
}