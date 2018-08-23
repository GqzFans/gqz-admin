package me.gqz.schedule;

import lombok.extern.slf4j.Slf4j;
import me.gqz.core.utils.DatetimeUtils;
import me.gqz.domain.GqzTencentWsData;
import me.gqz.service.GqzTencentWsDataLogService;
import me.gqz.service.GqzTencentWsDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: WsDataSchedule. </p>
 * <p>Description 腾讯微视调度任务 </p>
 * @author dragon
 * @date 2018/8/3 下午9:31
 */
@Slf4j
@Component
public class WsDataSchedule {

    @Resource
    private GqzTencentWsDataService wsDataService;
    @Resource
    private GqzTencentWsDataLogService wsDataLogService;

    @Scheduled(cron="0 0 0/1 * * ?")
    public void startWsDataListener() {
        List<GqzTencentWsData> wsDataList = wsDataService.getStartWsData();
        int workerTotalCount = wsDataList.size();
        if (workerTotalCount > 0) {
            ExecutorService threadPool = Executors.newFixedThreadPool(workerTotalCount);
            for (int i = 0; i < workerTotalCount; i++) {
                final int current = i;
                threadPool.execute(() -> {
                    String id = wsDataList.get(current).getWsVideoId();
                    log.info("当前时间 = {} -> 开始处理微视数据分析任务VID = {}", DatetimeUtils.getNowTimeYMDHMS(), id);
                    wsDataLogService.processWsData(id);
                });
            }
            threadPool.shutdown();
        }
    }
}
