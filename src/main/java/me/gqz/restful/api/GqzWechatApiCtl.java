package me.gqz.restful.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.model.dto.req.InsertGqzFeedbackReqDTO;
import me.gqz.restful.BaseController;
import me.gqz.service.FeedbackService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
}
