package me.gqz.service;

import me.gqz.domain.GqzTencentWsDataLog;

import java.util.List;

/**
 * <p>Title: GqzTencentWsDataService. </p>
 * <p>Description 高秋梓数据组-微视数据分析日志Service </p>
 * @author dragon
 * @date 2018/8/2 下午10:00
 */
public interface GqzTencentWsDataLogService extends IService<GqzTencentWsDataLog> {
    /**
     * <p>Title: processWsData. </p>
     * <p>处理数据分析数据 </p>
     * @param id
     * @author dragon
     * @date 2018/8/3 下午10:56
     * @return Boolean
     */
    Boolean processWsData(String id);

    /**
     * <p>Title: getWsDataLogByWsId. </p>
     * <p>通过短视频ID获取数据分析日志 </p>
     * @param id
     * @author dragon
     * @date 2018/8/3 下午11:35
     * @return List<GqzTencentWsDataLog>
     */
    List<GqzTencentWsDataLog> getWsDataLogByWsId(String id);
}
