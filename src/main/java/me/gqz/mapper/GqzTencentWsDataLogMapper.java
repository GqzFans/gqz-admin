package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzTencentWsDataLog;

import java.util.List;

/**
 * <p>Title: GqzTencentWsDataLogMapper. </p>
 * @author dragon
 * @date 2018/8/3 下午10:09
 */
public interface GqzTencentWsDataLogMapper extends MyMapper<GqzTencentWsDataLog> {
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