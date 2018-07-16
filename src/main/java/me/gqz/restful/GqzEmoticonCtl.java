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
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.model.dto.req.InsertGqzEmoticonReqDTO;
import me.gqz.model.dto.req.OperateGqzEmoticonReqDTO;
import me.gqz.model.dto.req.QueryGqzEmoticonReqDTO;
import me.gqz.service.GqzEmoticonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: GqzEmoticonCtl. </p>
 * <p>Description 高秋梓资源站-内容管理-表情包管理 </p>
 * @author dragon
 * @date 2018/7/15 下午10:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/content/emoticon", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzEmoticonCtl", tags = "高秋梓资源站-内容管理-表情包管理", description = "高秋梓资源站-内容管理-表情包管理接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzEmoticonCtl extends BaseController {

    @Resource
    private GqzEmoticonService emoticonService;

    /**
     * <p>Title: addGqzEmoticon. </p>
     * <p>资源站添加表情包 </p>
     * @param insertGqzEmoticonReqDTO
     * @author dragon
     * @date 2018/7/16 上午10:37
     * @return result
     */
    @BusinessLog(logInfo = "资源站添加表情包")
    @ResponseBody
    @RequestMapping(value = "/addGqzEmoticon", method = {RequestMethod.POST})
    @ApiOperation(value = "资源站添加表情包", httpMethod = "POST", notes = "返回添加表情包结果")
    public Wrapper<?> addGqzEmoticon(@ApiParam(name = "insertGqzEmoticonReqDTO", value = "资源站添加表情包参数") @RequestBody InsertGqzEmoticonReqDTO insertGqzEmoticonReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(insertGqzEmoticonReqDTO.getEmoticonDescription()) ||
                    insertGqzEmoticonReqDTO.getImageList().size() < 1) {
                throw new BusinessException("部分参数为空，保存失败");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = emoticonService.addGqzEmoticon(insertGqzEmoticonReqDTO, authUser);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("资源站添加表情包出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("资源站添加表情包出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: queryGqzEmoticonList. </p>
     * <p>表情包管理分页列表查询 </p>
     * @param page
     * @author dragon
     * @date 2018/7/16 上午10:58
     * @return Wrapper<PageInfo<GqzAppEmoticon>>
     */
    @BusinessLog(logInfo = "表情包管理分页列表查询")
    @ResponseBody
    @RequestMapping(value = "/queryGqzEmoticonList", method = {RequestMethod.POST})
    @ApiOperation(value = "表情包管理分页列表查询", httpMethod = "POST", notes = "返回表情包管理分页列表查询")
    public Wrapper<PageInfo<GqzAppEmoticon>> queryGqzEmoticonList(@ApiParam(name = "page", value = "表情包管理分页参数") @RequestBody Page<QueryGqzEmoticonReqDTO> page) {
        try {
            log.info("表情包管理分页列表查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppEmoticon> list = emoticonService.queryGqzEmoticonList(page.getParam());
            PageInfo<GqzAppEmoticon> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("表情包管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("表情包管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    @BusinessLog(logInfo = "表情包管理下架或上架图片")
    @ResponseBody
    @RequestMapping(value = "/operateEmoticonById", method = {RequestMethod.POST})
    @ApiOperation(value = "表情包管理下架或上架图片", httpMethod = "POST", notes = "返回表情包管理下架或上架图片结果")
    public Wrapper<?> operateEmoticonById(@ApiParam(name = "operateGqzEmoticonReqDTO", value = "表情包擦操作参数") @RequestBody OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO) {
        Boolean result;
        try {
            String model = operateGqzEmoticonReqDTO.getModel();
            String imageId = operateGqzEmoticonReqDTO.getId();
            Integer version = operateGqzEmoticonReqDTO.getVersion();
            if (CommUsualUtils.isSEmptyOrNull(imageId) || CommUsualUtils.isOEmptyOrNull(version) || CommUsualUtils.isSEmptyOrNull(model)) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            if (SystemBaseConstants.UP.equals(model)) {
                log.warn("表情包管理上架：ID = {}", imageId);
                result = emoticonService.upEmoticonById(operateGqzEmoticonReqDTO, authUser);
            } else if (SystemBaseConstants.DROP.equals(model)) {
                log.warn("表情包管理下架：ID = {}", imageId);
                result = emoticonService.dropEmoticonById(operateGqzEmoticonReqDTO, authUser);
            } else {
                throw new BusinessException("操作非法");
            }
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("表情包管理下架或上架图片出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("表情包管理下架或上架图片出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: deleteEmoticonById. </p>
     * <p>表情包管理-删除表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @author dragon
     * @date 2018/7/16 上午11:46
     * @return result
     */
    @BusinessLog(logInfo = "表情包管理-删除表情包")
    @ResponseBody
    @RequestMapping(value = "/deleteEmoticonById", method = {RequestMethod.POST})
    @ApiOperation(value = "表情包管理删除表情包", httpMethod = "POST", notes = "返回图片管理删除表情包结果")
    public Wrapper<?> deleteEmoticonById(@ApiParam(name = "operateGqzEmoticonReqDTO", value = "操作表情包参数") @RequestBody OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(operateGqzEmoticonReqDTO.getId()) || CommUsualUtils.isOEmptyOrNull(operateGqzEmoticonReqDTO.getVersion())) {
                throw new BusinessException("参数不能为空");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = emoticonService.deleteEmoticonById(operateGqzEmoticonReqDTO);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("表情包管理删除表情包出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("表情包管理删除表情包出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
