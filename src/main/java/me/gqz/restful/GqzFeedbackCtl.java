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
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.domain.GqzAppFeedback;
import me.gqz.service.FeedbackService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/system/feedback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "GqzFeedbackCtl", tags = "高秋梓资源站-意见反馈", description = "高秋梓资源站-意见反馈接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GqzFeedbackCtl extends BaseController {

    @Resource
    private FeedbackService feedbackService;

    /**
     * <p>Title: queryFeedback. </p>
     * <p>意见反馈分页列表查询 </p>
     * @param page
     * @author dragon
     * @date 2018/7/25 下午12:41
     * @return Wrapper<PageInfo<GqzAppFeedback>>
     */
    @BusinessLog(logInfo = "意见反馈分页列表查询")
    @ResponseBody
    @RequestMapping(value = "/queryFeedback", method = {RequestMethod.POST})
    @ApiOperation(value = "意见反馈分页列表查询", httpMethod = "POST", notes = "返回意见反馈分页列表查询")
    public Wrapper<PageInfo<GqzAppFeedback>> queryFeedback(@ApiParam(name = "page", value = "意见反馈分页参数") @RequestBody Page<Object> page) {
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<GqzAppFeedback> list = feedbackService.queryFeedback();
            PageInfo<GqzAppFeedback> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("表情包管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("表情包管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
