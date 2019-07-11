package me.gqz.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import me.gqz.core.utils.DatetimeUtils;
import me.gqz.domain.GqzTencentWsData;
import me.gqz.service.GqzTencentWsDataLogService;
import me.gqz.service.GqzTencentWsDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>Title: WsDataSchedule. </p>
 * <p>Description 腾讯微视调度任务 </p>
 * @author dragon
 * @date 2018/8/3 下午9:31
 */
@Slf4j
@Component
public class WsDataSchedule {

    private static final ThreadFactory threadFactory= new ThreadFactoryBuilder().setNameFormat("thread-pool-scheduled-ws-%d").build();
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 2,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);

    @Resource
    private GqzTencentWsDataService wsDataService;
    @Resource
    private GqzTencentWsDataLogService wsDataLogService;

    @Scheduled(cron="0 0 0/1 * * ?")
    public void startWsDataListener() {
        List<GqzTencentWsData> wsDataList = wsDataService.getStartWsData();
        int workerTotalCount = wsDataList.size();
        if (workerTotalCount > 0) {
            for (int i = 0; i < workerTotalCount; i++) {
                final int current = i;
                threadPool.execute(() -> {
                    String id = wsDataList.get(current).getWsVideoId();
                    log.info("当前时间 = {} -> 开始处理微视数据分析任务VID = {}", DatetimeUtils.getNowTimeYMDHMS(), id);
                    wsDataLogService.processWsData(id);
                });
            }
        }
    }
}
