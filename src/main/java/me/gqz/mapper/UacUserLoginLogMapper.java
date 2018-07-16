package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.UacUserLoginLog;
import me.gqz.model.dto.res.RecentUserLogResDTO;

import java.util.List;

/**
 * <p>Title: UacUserLoginLogMapper. </p>
 * <p>Description 用户账户中心登录系统日志Mapper </p>
 * @author dragon
 * @date 2018/4/8 下午2:45
 */
public interface UacUserLoginLogMapper extends MyMapper<UacUserLoginLog> {
    /**
     * <p>Title: getRecentUserLog. </p>
     * <p>获取最近登录的用户列表查询 </p>
     * @author dragon
     * @date 2018/7/16 下午1:55
     * @return List<RecentUserLogResDTO>
     */
    List<RecentUserLogResDTO> getRecentUserLog();
}
