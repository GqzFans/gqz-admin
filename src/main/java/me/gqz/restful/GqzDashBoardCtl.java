package me.gqz.restful;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.domain.GqzAppVersion;
import me.gqz.model.dto.res.RecentUserLogResDTO;
import me.gqz.model.vo.DashBoardDataStatisticsVO;
import me.gqz.service.GqzDashBoardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: GqzDashBoardCtl. </p>
 * <p>Description 高秋梓资源站-首页仪表盘 </p>
 * @author dragon
 * @date 2018/7/16 下午1:44
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/dashboard", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzDashBoardCtl", tags = "高秋梓资源站-首页仪表盘", description = "高秋梓资源站-首页仪表盘", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzDashBoardCtl extends BaseController {

    @Resource
    private GqzDashBoardService dashBoardService;

    /**
     * <p>Title: getRecentUserLog. </p>
     * <p>获取最近登录的用户 </p>
     * @author dragon
     * @date 2018/7/16 下午1:53
     * @return Wrapper<List<RecentUserLogResDTO>>
     */
    @BusinessLog(logInfo = "获取最近登录的用户")
    @ResponseBody
    @RequestMapping(value = "/getRecentUserLog", method = {RequestMethod.POST})
    @ApiOperation(value = "获取最近登录的用户列表查询", httpMethod = "POST", notes = "返回获取最近登录的用户列表")
    public Wrapper<List<RecentUserLogResDTO>> getRecentUserLog() {
        try {
            List<RecentUserLogResDTO> list = dashBoardService.getRecentUserLog();
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
        } catch (BusinessException ex) {
            log.error("获取最近登录的用户出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("获取最近登录的用户出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: getDataStatistics. </p>
     * <p>高秋梓资源站数据统计 </p>
     * @author dragon
     * @date 2018/7/16 下午2:32
     * @return Wrapper<DashBoardDataStatisticsVO>
     */
    @BusinessLog(logInfo = "获取高秋梓资源站数据统计")
    @ResponseBody
    @RequestMapping(value = "/getDataStatistics", method = {RequestMethod.POST})
    @ApiOperation(value = "获取高秋梓资源站数据统计查询", httpMethod = "POST", notes = "返回获取高秋梓资源站数据统计")
    public Wrapper<DashBoardDataStatisticsVO> getDataStatistics() {
        try {
            DashBoardDataStatisticsVO dataStatisticsVO = dashBoardService.getDataStatistics();
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataStatisticsVO);
        } catch (BusinessException ex) {
            log.error("获取高秋梓资源站数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("获取高秋梓资源站数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: getVersionInfo. </p>
     * <p>获取高秋梓资源站上线版本信息 </p>
     * @author dragon
     * @date 2018/7/16 下午3:30
     * @return Wrapper<List<GqzAppVersion>>
     */
    @BusinessLog(logInfo = "获取高秋梓资源站上线版本信息")
    @ResponseBody
    @RequestMapping(value = "/getVersionInfo", method = {RequestMethod.GET})
    @ApiOperation(value = "获取高秋梓资源站上线版本信息", httpMethod = "GET", notes = "返回获取高秋梓资源站上线版本信息")
    public Wrapper<List<GqzAppVersion>> getVersionInfo() {
        try {
            List<GqzAppVersion> appVersionList = dashBoardService.getVersionInfo();
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, appVersionList);
        } catch (BusinessException ex) {
            log.error("获取高秋梓资源站数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("获取高秋梓资源站数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
