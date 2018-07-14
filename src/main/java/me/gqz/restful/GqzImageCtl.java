package me.gqz.restful;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;
import me.gqz.service.GqzImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
