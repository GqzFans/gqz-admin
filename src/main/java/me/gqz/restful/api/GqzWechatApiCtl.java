package me.gqz.restful.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.core.base.Page;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.domain.GqzAppImage;
import me.gqz.domain.GqzAppVideo;
import me.gqz.model.dto.req.InsertGqzFeedbackReqDTO;
import me.gqz.restful.BaseController;
import me.gqz.service.FeedbackService;
import me.gqz.service.GqzEmoticonService;
import me.gqz.service.GqzImageService;
import me.gqz.service.GqzVideoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>Title: GqzWechatApiCtl. </p>
 * <p>Description 小程序-开放接口 </p>
 * @author dragon
 * @date 2018/7/18 下午5:07
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/open", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzWechatApiCtl", tags = "小程序-开放接口", description = "小程序-开放接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzWechatApiCtl extends BaseController {

    @Resource
    private FeedbackService feedbackService;
    @Resource
    private GqzImageService imageService;
    @Resource
    private GqzEmoticonService emoticonService;
    @Resource
    private GqzVideoService videoService;
    
    /**
     * <p>Title: submitFeedback. </p>
     * <p>小程序提交意见反馈 </p>
     * @param 
     * @author dragon
     * @date 2018/7/18 下午5:09
     * @return result
     */
    @BusinessLog(logInfo = "小程序提交意见反馈")
    @ResponseBody
    @RequestMapping(value = "/submitFeedback", method = {RequestMethod.POST})
    @ApiOperation(value = "小程序提交意见反馈", httpMethod = "POST", notes = "返回提交结果")
    public Wrapper<?> submitFeedback(@ApiParam(name = "insertGqzFeedbackReqDTO", value = "意见反馈参数") @RequestBody InsertGqzFeedbackReqDTO insertGqzFeedbackReqDTO) {
        try {
            Boolean result = feedbackService.submitFeedback(insertGqzFeedbackReqDTO);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("小程序提交意见反馈出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("小程序提交意见反馈出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: appGetGqzImageList. </p>
     * <p>小程序查询图片列表分页接口 </p>
     * @param page
     * @author dragon
     * @date 2018/7/18 下午5:52
     * @return Wrapper<PageInfo<GqzAppImage>>
     */
    @BusinessLog(logInfo = "小程序查询图片列表分页接口")
    @ResponseBody
    @RequestMapping(value = "/appGetGqzImageList", method = {RequestMethod.POST})
    @ApiOperation(value = "小程序查询图片列表分页接口", httpMethod = "POST", notes = "返回小程序查询图片列表分页查询")
    public Wrapper<PageInfo<GqzAppImage>> appGetGqzImageList(@ApiParam(name = "page", value = "图片列表分页参数") @RequestBody Page<Object> page) {
        try {
            log.info("小程序查询图片列表分页查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppImage> list = imageService.appGetGqzImageList();
            PageInfo<GqzAppImage> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("小程序查询图片列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("小程序查询图片列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }


    /**
     * <p>Title: appGetGqzEmoticonList. </p>
     * <p>小程序查询表情包列表分页接口 </p>
     * @param page
     * @author dragon
     * @date 2018/7/18 下午8:29
     * @return Wrapper<PageInfo<GqzAppEmoticon>>
     */
    @BusinessLog(logInfo = "小程序查询表情包列表分页接口")
    @ResponseBody
    @RequestMapping(value = "/appGetGqzEmoticonList", method = {RequestMethod.POST})
    @ApiOperation(value = "小程序查询表情包列表分页接口", httpMethod = "POST", notes = "返回小程序查询表情包列表分页查询")
    public Wrapper<PageInfo<GqzAppEmoticon>> appGetGqzEmoticonList(@ApiParam(name = "page", value = "表情包列表分页参数") @RequestBody Page<Object> page) {
        try {
            log.info("小程序查询图片列表分页查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppEmoticon> list = emoticonService.appGetGqzEmoticonList();
            PageInfo<GqzAppEmoticon> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("小程序查询表情包列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("小程序查询表情包列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: appGetGqzVideoList. </p>
     * <p>小程序查询视频列表分页接口 </p>
     * @param page
     * @author dragon
     * @date 2018/7/26 下午10:09
     * @return Wrapper<PageInfo<GqzAppVideo>>
     */
    @BusinessLog(logInfo = "小程序查询视频列表分页接口")
    @ResponseBody
    @RequestMapping(value = "/appGetGqzVideoList", method = {RequestMethod.POST})
    @ApiOperation(value = "小程序查询视频列表分页接口", httpMethod = "POST", notes = "返回小程序查询视频列表分页查询")
    public Wrapper<PageInfo<GqzAppVideo>> appGetGqzVideoList(@ApiParam(name = "page", value = "视频列表分页参数") @RequestBody Page<Object> page) {
        try {
            log.info("小程序查询视频列表分页查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppVideo> list = videoService.appGetGqzVideoList();
            PageInfo<GqzAppVideo> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("小程序查询视频列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("小程序查询视频列表分页查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
