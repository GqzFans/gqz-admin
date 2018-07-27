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
import me.gqz.domain.GqzAppVideo;
import me.gqz.model.dto.req.InsertGqzVideoReqDTO;
import me.gqz.model.dto.req.OperateGqzVideoReqDTO;
import me.gqz.model.dto.req.QueryGqzVideoReqDTO;
import me.gqz.service.GqzVideoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: GqzVideoCtl. </p>
 * <p>Description 高秋梓资源站-内容管理-视频管理 </p>
 * @author dragon
 * @date 2018/7/26 下午5:04
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/content/video", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzVideoCtl", tags = "高秋梓资源站-内容管理-视频管理", description = "高秋梓资源站-内容管理-视频管理接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzVideoCtl extends BaseController {

    @Resource
    private GqzVideoService videoService;

    /**
     * <p>Title: addGqzVideo. </p>
     * <p>资源站添加视频 </p>
     * @param insertGqzVideoReqDTO
     * @author dragon
     * @date 2018/7/26 下午5:22
     * @return result
     */
    @BusinessLog(logInfo = "资源站添加视频")
    @ResponseBody
    @RequestMapping(value = "/addGqzVideo", method = {RequestMethod.POST})
    @ApiOperation(value = "资源站添加视频", httpMethod = "POST", notes = "返回添加视频结果")
    public Wrapper<?> addGqzVideo(@ApiParam(name = "insertGqzImageReqDTO", value = "资源站添加视频参数") @RequestBody InsertGqzVideoReqDTO insertGqzVideoReqDTO) {
        try {
            if (CommUsualUtils.isOEmptyOrNull(insertGqzVideoReqDTO)) {
                throw new BusinessException("参数为空，保存失败");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = videoService.addGqzVideo(insertGqzVideoReqDTO, authUser);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("资源站添加视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("资源站添加视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: queryGqzVideoList. </p>
     * <p>视频管理分页列表查询 </p>
     * @param page
     * @author dragon
     * @date 2018/7/26 下午5:48
     * @return Wrapper<PageInfo<GqzAppVideo>>
     */
    @BusinessLog(logInfo = "视频管理分页列表查询")
    @ResponseBody
    @RequestMapping(value = "/queryGqzVideoList", method = {RequestMethod.POST})
    @ApiOperation(value = "视频管理分页列表查询", httpMethod = "POST", notes = "返回视频管理分页列表查询")
    public Wrapper<PageInfo<GqzAppVideo>> queryGqzVideoList(@ApiParam(name = "page", value = "视频管理分页参数") @RequestBody Page<QueryGqzVideoReqDTO> page) {
        try {
            log.info("视频管理分页列表查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppVideo> list = videoService.queryGqzVideoList(page.getParam());
            PageInfo<GqzAppVideo> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("视频管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("视频管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: deleteVideoById. </p>
     * <p>视频管理删除图片 </p>
     * @param operateGqzVideoReqDTO
     * @author dragon
     * @date 2018/7/26 下午9:29
     * @return Boolean
     */
    @BusinessLog(logInfo = "视频管理删除图片")
    @ResponseBody
    @RequestMapping(value = "/deleteVideoById", method = {RequestMethod.POST})
    @ApiOperation(value = "视频管理删除图片", httpMethod = "POST", notes = "返回视频管理删除图片结果")
    public Wrapper<?> deleteVideoById(@ApiParam(name = "operateGqzImageReqDTO", value = "操作图片参数") @RequestBody OperateGqzVideoReqDTO operateGqzVideoReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(operateGqzVideoReqDTO.getId()) || CommUsualUtils.isOEmptyOrNull(operateGqzVideoReqDTO.getVersion())) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = videoService.deleteVideoById(operateGqzVideoReqDTO);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("视频管理删除视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("视频管理删除视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: operateVideoById. </p>
     * <p>视频管理下架或上架视频 </p>
     * @param operateGqzVideoReqDTO
     * @author dragon
     * @date 2018/7/26 下午9:53
     * @return result
     */
    @BusinessLog(logInfo = "视频管理下架或上架视频")
    @ResponseBody
    @RequestMapping(value = "/operateVideoById", method = {RequestMethod.POST})
    @ApiOperation(value = "视频管理下架或上架视频", httpMethod = "POST", notes = "返回视频管理下架或上架视频结果")
    public Wrapper<?> operateVideoById(@ApiParam(name = "operateGqzImageReqDTO", value = "视频操作参数") @RequestBody OperateGqzVideoReqDTO operateGqzVideoReqDTO) {
        Boolean result;
        try {
            String model = operateGqzVideoReqDTO.getModel();
            String videoId = operateGqzVideoReqDTO.getId();
            Integer version = operateGqzVideoReqDTO.getVersion();
            if (CommUsualUtils.isSEmptyOrNull(videoId) || CommUsualUtils.isOEmptyOrNull(version) || CommUsualUtils.isSEmptyOrNull(model)) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            if (SystemBaseConstants.UP.equals(model)) {
                log.warn("视频管理上架：ID = {}", videoId);
                result = videoService.upVideoById(operateGqzVideoReqDTO, authUser);
            } else if (SystemBaseConstants.DROP.equals(model)) {
                log.warn("视频管理下架：ID = {}", videoId);
                result = videoService.dropVideoById(operateGqzVideoReqDTO, authUser);
            } else {
                throw new BusinessException("操作非法");
            }
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("视频管理下架或上架视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("视频管理下架或上架视频出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
