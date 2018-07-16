package me.gqz.service;

import me.gqz.model.dto.res.RecentUserLogResDTO;
import me.gqz.model.vo.DashBoardDataStatisticsVO;

import java.util.List;

/**
 * <p>Title: GqzDashBoardService. </p>
 * <p>Description 高秋梓资源站-首页仪表盘Service </p>
 * @author dragon
 * @date 2018/7/16 下午1:55
 */
public interface GqzDashBoardService {
    /**
     * <p>Title: getRecentUserLog. </p>
     * <p>获取最近登录的用户列表查询 </p>
     * @author dragon
     * @date 2018/7/16 下午1:55
     * @return List<RecentUserLogResDTO>
     */
    List<RecentUserLogResDTO> getRecentUserLog();

    /**
     * <p>Title: getDataStatistics. </p>
     * <p>获取高秋梓资源站数据统计 </p>
     * @author dragon
     * @date 2018/7/16 下午2:37
     * @return DashBoardDataStatisticsVO
     */
    DashBoardDataStatisticsVO getDataStatistics();
}
