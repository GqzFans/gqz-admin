package me.gqz.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.gqz.core.exception.BusinessException;
import me.gqz.domain.GqzTencentWsDataLog;
import me.gqz.mapper.GqzTencentWsDataLogMapper;
import me.gqz.service.GqzTencentWsDataLogService;
import me.gqz.utils.TencentWsPlayNumUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: GqzTencentWsDataLogServiceImpl. </p>
 * <p>Description 高秋梓数据组-微视数据分析日志Service </p>
 * @author dragon
 * @date 2018/8/3 下午10:59
 */
@Slf4j
@Service
public class GqzTencentWsDataLogServiceImpl extends BaseService<GqzTencentWsDataLog> implements GqzTencentWsDataLogService {

    @Resource
    private GqzTencentWsDataLogMapper wsDataLogMapper;

    /**
     * <p>Title: processWsData. </p>
     * <p>处理数据分析数据 </p>
     * @param id
     * @author dragon
     * @date 2018/8/3 下午10:56
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean processWsData(String id) {
        Date date = new Date();
        GqzTencentWsDataLog wsDataLog = new GqzTencentWsDataLog();
        wsDataLog.setWsVideoId(id);
        wsDataLog.setWorkerDate(date);
        wsDataLog.setWorkerDateDescription(getNowDate(date));
        wsDataLog.setWsPlayNum(TencentWsPlayNumUtil.getTencentWsPlayNum(id));
        Integer count = wsDataLogMapper.insert(wsDataLog);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>Title: getNowDate. </p>
     * <p>获取当前时间 </p>
     * @param date
     * @author dragon
     * @date 2018/8/3 下午11:08
     * @return String
     */
    public String getNowDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(date);
    }

    /**
     * <p>Title: getWsDataLogByWsId. </p>
     * <p>通过短视频ID获取数据分析日志 </p>
     * @param id
     * @author dragon
     * @date 2018/8/3 下午11:35
     * @return List<GqzTencentWsDataLog>
     */
    @Override
    public List<GqzTencentWsDataLog> getWsDataLogByWsId(String id) {
        return wsDataLogMapper.getWsDataLogByWsId(id);
    }

}
