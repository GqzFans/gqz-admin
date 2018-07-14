package me.gqz.restful;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.core.base.Page;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.domain.GqzAppImage;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;
import me.gqz.model.dto.req.QueryGqzImageReqDTO;
import me.gqz.model.vo.UserListVO;
import me.gqz.service.GqzImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: GqzImageCtl. </p>
 * <p>Description 图片管理 </p>
 * @author dragon
 * @date 2018/7/14 下午3:36
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/content/image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzImageCtl", tags = "高秋梓资源站-内容管理-图片管理", description = "高秋梓资源站-内容管理-图片管理接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzImageCtl extends BaseController {

    @Resource
    private GqzImageService imageService;

    /**
     * <p>Title: addGqzImage. </p>
     * <p>资源站添加图片 </p>
     * @param insertGqzImageReqDTO
     * @author dragon
     * @date 2018/7/14 下午11:28
     * @return result
     */
    @BusinessLog(logInfo = "资源站添加图片")
    @ResponseBody
    @RequestMapping(value = "/addGqzImage", method = {RequestMethod.POST})
    @ApiOperation(value = "资源站添加图片", httpMethod = "POST", notes = "返回添加图片结果")
    public Wrapper<?> addGqzImage(@ApiParam(name = "id", value = "资源站添加图片参数") @RequestBody InsertGqzImageReqDTO insertGqzImageReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(insertGqzImageReqDTO.getImageDescription()) ||
                    insertGqzImageReqDTO.getImageList().size() < 1) {
                throw new BusinessException("部分参数为空，保存失败");
            }
            AuthUserDTO authUser = getAuthUserByToken();
            if (CommUsualUtils.isOEmptyOrNull(authUser)) {
                throw new BusinessException("获取用户信息失败");
            }
            Boolean result = imageService.addGqzImage(insertGqzImageReqDTO, authUser);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("资源站添加图片出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("资源站添加图片出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: queryGqzImageList. </p>
     * <p>图片管理分页列表查询 </p>
     * @param page
     * @author dragon
     * @date 2018/7/14 下午11:29
     * @return Wrapper<PageInfo<GqzAppImage>>
     */
    @BusinessLog(logInfo = "图片管理分页列表查询")
    @ResponseBody
    @RequestMapping(value = "/queryGqzImageList", method = {RequestMethod.POST})
    @ApiOperation(value = "图片管理分页列表查询", httpMethod = "POST", notes = "返回图片管理分页列表查询")
    public Wrapper<PageInfo<GqzAppImage>> queryGqzImageList(@ApiParam(name = "page", value = "图片管理分页参数") @RequestBody Page<QueryGqzImageReqDTO> page) {
        try {
            log.info("图片管理分页列表查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppImage> list = imageService.queryGqzImageList(page.getParam());
            PageInfo<GqzAppImage> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("图片管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("图片管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
