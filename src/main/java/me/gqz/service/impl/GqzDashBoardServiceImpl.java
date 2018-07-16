package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.core.utils.DoubleUtils;
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.domain.GqzAppImage;
import me.gqz.mapper.UacUserLoginLogMapper;
import me.gqz.model.dto.res.RecentUserLogResDTO;
import me.gqz.model.vo.DashBoardDataStatisticsVO;
import me.gqz.service.GqzDashBoardService;
import me.gqz.service.GqzEmoticonService;
import me.gqz.service.GqzImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: GqzDashBoardServiceImpl. </p>
 * <p>Description 高秋梓资源站-首页仪表盘Service </p>
 * @author dragon
 * @date 2018/7/16 下午1:56
 */
@Slf4j
@Service
public class GqzDashBoardServiceImpl implements GqzDashBoardService {

    @Resource
    private UacUserLoginLogMapper uacUserLoginLogMapper;
    @Resource
    private GqzImageService imageService;
    @Resource
    private GqzEmoticonService emoticonService;

    /**
     * <p>Title: getRecentUserLog. </p>
     * <p>获取最近登录的用户列表查询 </p>
     * @author dragon
     * @date 2018/7/16 下午1:55
     * @return List<RecentUserLogResDTO>
     */
    @Override
    public List<RecentUserLogResDTO> getRecentUserLog() {
        return uacUserLoginLogMapper.getRecentUserLog();
    }

    /**
     * <p>Title: getDataStatistics. </p>
     * <p>获取高秋梓资源站数据统计 </p>
     * @author dragon
     * @date 2018/7/16 下午2:37
     * @return DashBoardDataStatisticsVO
     */
    @Override
    public DashBoardDataStatisticsVO getDataStatistics() {
        DashBoardDataStatisticsVO dataStatisticsVO = new DashBoardDataStatisticsVO();
        // 图片相关数据
        GqzAppImage appImage = new GqzAppImage();
        int imageTotalCount = imageService.selectCount(appImage);
        Integer imageThisMonthCount = imageService.queryThisMonthUploadCount();
        Double uploadImageMonthPercentage = DoubleUtils.div(imageThisMonthCount, imageTotalCount);
        dataStatisticsVO.setGqzImageCount(imageTotalCount);
        dataStatisticsVO.setUploadImageMonthPercentage(uploadImageMonthPercentage);
        // 表情包相关数据
        GqzAppEmoticon appEmoticon = new GqzAppEmoticon();
        int emoticonTotalCount = emoticonService.selectCount(appEmoticon);
        Integer emoticonThisMonthCount = emoticonService.queryThisMonthUploadCount();
        Double uploadEmoticonMonthPercentage = DoubleUtils.div(emoticonThisMonthCount, emoticonTotalCount);
        dataStatisticsVO.setGqzEmoticonCount(emoticonTotalCount);
        dataStatisticsVO.setUploadEmoticonMonthPercentage(uploadEmoticonMonthPercentage);
        // 视频相关数据
        dataStatisticsVO.setGqzVideoCount(0);
        dataStatisticsVO.setUploadVideoMonthPercentage(0.0);
        return dataStatisticsVO;
    }
}
