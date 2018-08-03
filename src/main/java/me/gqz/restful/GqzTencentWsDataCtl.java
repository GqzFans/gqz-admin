package me.gqz.restful;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.base.Page;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.domain.GqzTencentWsData;
import me.gqz.domain.GqzTencentWsDataLog;
import me.gqz.model.dto.req.InsertGqzWsDataReqDTO;
import me.gqz.model.dto.req.OperateGqzWsDataReqDTO;
import me.gqz.model.vo.GqzTencentWsDataVO;
import me.gqz.service.GqzTencentWsDataLogService;
import me.gqz.service.GqzTencentWsDataService;
import me.gqz.utils.BeanListUtils;
import me.gqz.utils.TencentWsPlayNumUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;


/**
 * <p>Title: GqzTencentWsDataCtl. </p>
 * <p>Description 高秋梓数据站-数据分析-微视数据分析 </p>
 * @author dragon
 * @date 2018/8/2 下午5:59
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/data/ws", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzTencentWsDataCtl", tags = "高秋梓数据站-数据分析-微视数据分析", description = "高秋梓数据站-数据分析-微视数据分析接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzTencentWsDataCtl extends BaseController {

    @Resource
    private GqzTencentWsDataService wsDataService;
    @Resource
    private GqzTencentWsDataLogService wsDataLogService;

    /**
     * <p>Title: addGqzWsData. </p>
     * <p>数据站添加微视数据分析 </p>
     * @param 
     * @author dragon
     * @date 2018/8/2 下午6:00
     * @return result
     */
    @BusinessLog(logInfo = "数据站添加微视数据分析")
    @ResponseBody
    @RequestMapping(value = "/addGqzWsData", method = {RequestMethod.POST})
    @ApiOperation(value = "数据站添加微视数据分析", httpMethod = "POST", notes = "返回添加结果")
    public Wrapper<?> addGqzWsData(@ApiParam(name = "insertGqzImageReqDTO", value = "数据站添加微视数据分析参数") @RequestBody InsertGqzWsDataReqDTO insertGqzWsDataReqDTO) {
        try {
            if (CommUsualUtils.isOEmptyOrNull(insertGqzWsDataReqDTO)) {
                throw new BusinessException("参数为空，保存失败");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = wsDataService.addGqzWsData(insertGqzWsDataReqDTO, authUser);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("数据站添加微视数据分析出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("数据站添加微视数据分析出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    @BusinessLog(logInfo = "数据站微视数据分析列表查询")
    @ResponseBody
    @RequestMapping(value = "/queryWsDataList", method = {RequestMethod.POST})
    @ApiOperation(value = "数据站微视数据分析列表查询", httpMethod = "POST", notes = "返回微视数据分析列表查询")
    public Wrapper<PageInfo<GqzTencentWsDataVO>> queryWsDataList(@ApiParam(name = "page", value = "微视数据分析列表分页参数") @RequestBody Page<Object> page) {
        try {
            log.info("数据站微视数据分析列表查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzTencentWsData> list = wsDataService.queryWsDataList();
            List<GqzTencentWsDataVO> voList = BeanListUtils.copyTo(list, GqzTencentWsDataVO.class);
            PageInfo<GqzTencentWsDataVO> pageInfo = new PageInfo<>(voList);
            // 遍历获取微视播放量
            voList = pageInfo.getList();
            voList = getWsPlayNum(voList);
            pageInfo = new PageInfo<>(voList);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("数据站微视数据分析列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("数据站微视数据分析列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: getWsPlayNum. </p>
     * <p>遍历获取微视播放量 </p>
     * @author dragon
     * @param list
     * @date 2018/8/2 下午10:10
     * @return List<GqzTencentWsData>
     */
    private List<GqzTencentWsDataVO> getWsPlayNum(List<GqzTencentWsDataVO> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            GqzTencentWsDataVO wsData = (GqzTencentWsDataVO) iterator.next();
            String feedId = wsData.getWsVideoId();
            Integer playNum = TencentWsPlayNumUtil.getTencentWsPlayNum(feedId);
            wsData.setPlayNum(playNum);
        }
        return list;
    }


    /**
     * <p>Title: deleteWsDataById. </p>
     * <p>数据站微视数据分析删除图片 </p>
     * @param
     * @author dragon
     * @date 2018/8/2 下午10:52
     * @return result
     */
    @BusinessLog(logInfo = "数据站微视数据分析删除图片")
    @ResponseBody
    @RequestMapping(value = "/deleteWsDataById", method = {RequestMethod.POST})
    @ApiOperation(value = "数据站微视数据分析删除图片", httpMethod = "POST", notes = "返回数据站微视数据分析删除图片结果")
    public Wrapper<?> deleteWsDataById(@ApiParam(name = "operateGqzImageReqDTO", value = "操作参数") @RequestBody OperateGqzWsDataReqDTO operateGqzWsDataReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(operateGqzWsDataReqDTO.getId()) || CommUsualUtils.isOEmptyOrNull(operateGqzWsDataReqDTO.getVersion())) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = wsDataService.deleteWsDataById(operateGqzWsDataReqDTO);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("数据站微视数据分析删除出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("数据站微视数据分析删除出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: operateWsDataWorkerListenerById. </p>
     * <p>开启或关闭数据统计 </p>
     * @param operateGqzWsDataReqDTO
     * @author dragon
     * @date 2018/8/3 下午5:55
     * @return result
     */
    @BusinessLog(logInfo = "开启或关闭数据统计")
    @ResponseBody
    @RequestMapping(value = "/operateWsDataWorkerListenerById", method = {RequestMethod.POST})
    @ApiOperation(value = "开启或关闭数据统计", httpMethod = "POST", notes = "返回开启或关闭数据统计结果")
    public Wrapper<?> operateWsDataById(@ApiParam(name = "operateGqzWsDataReqDTO", value = "操作参数") @RequestBody OperateGqzWsDataReqDTO operateGqzWsDataReqDTO) {
        Boolean result;
        try {
            String model = operateGqzWsDataReqDTO.getModel();
            String id = operateGqzWsDataReqDTO.getId();
            Integer version = operateGqzWsDataReqDTO.getVersion();
            if (CommUsualUtils.isSEmptyOrNull(id) || CommUsualUtils.isOEmptyOrNull(version) || CommUsualUtils.isSEmptyOrNull(model)) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            if (SystemBaseConstants.UP.equals(model)) {
                log.warn("开启数据统计：ID = {}", id);
                result = wsDataService.upWsDataWorkerListenerById(operateGqzWsDataReqDTO, authUser);
            } else if (SystemBaseConstants.DROP.equals(model)) {
                log.warn("关闭数据统计：ID = {}", id);
                result = wsDataService.dropWsDataWorkerListenerById(operateGqzWsDataReqDTO, authUser);
            } else {
                throw new BusinessException("操作非法");
            }
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("开启或关闭数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("开启或关闭数据统计出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: getWsDataLog. </p>
     * <p>通过短视频ID获取数据分析日志 </p>
     * @param id
     * @author dragon
     * @date 2018/8/3 下午11:24
     * @return result
     */
    @BusinessLog(logInfo = "通过短视频ID获取数据分析日志")
    @ResponseBody
    @RequestMapping(value = "/getWsDataLogByWsId/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "通过短视频ID获取数据分析日志", httpMethod = "POST", notes = "返回开启或关闭数据统计结果")
    public Wrapper<?> getWsDataLogByWsId(@ApiParam(name = "id", value = "微视短视频参数") @PathVariable String id) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(id)) {
                throw new BusinessException("参数不能为空");
            }
            List<GqzTencentWsDataLog> list = wsDataLogService.getWsDataLogByWsId(id);
            // 数据类型转换
            List<String> dateDescriptionList = new ArrayList<>();
            List<Integer> wsPlayNumList = new ArrayList<>();
            for (GqzTencentWsDataLog wsDataLog : list) {
                String dateDescription = wsDataLog.getWorkerDateDescription();
                Integer wsPlayNum = wsDataLog.getWsPlayNum();
                dateDescriptionList.add(dateDescription);
                wsPlayNumList.add(wsPlayNum);
            }
            String[] dateDescriptionArray = (String[]) dateDescriptionList.toArray();
            Integer[] wsPlayNumArray = (Integer[]) wsPlayNumList.toArray();
            Map<String, Object> map = new HashMap<>(list.size());
            map.put("date", dateDescriptionArray);
            map.put("playNum", wsPlayNumArray);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, map);
        } catch (BusinessException ex) {
            log.error("通过短视频ID获取数据分析日志出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("通过短视频ID获取数据分析日志出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
