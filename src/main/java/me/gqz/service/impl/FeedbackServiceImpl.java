package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.core.exception.BusinessException;
import me.gqz.domain.GqzAppFeedback;
import me.gqz.mapper.GqzAppFeedbackMapper;
import me.gqz.model.dto.req.InsertGqzFeedbackReqDTO;
import me.gqz.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>Title: FeedbackServiceImpl. </p>
 * <p>Description 小程序意见反馈Service </p>
 * @author dragon
 * @date 2018/7/18 下午5:15
 */
@Slf4j
@Service
public class FeedbackServiceImpl extends BaseService<GqzAppFeedback> implements FeedbackService {

    @Resource
    private GqzAppFeedbackMapper feedbackMapper;

    /**
     * <p>Title: submitFeedback. </p>
     * <p>小程序提交意见反馈 </p>
     * @param insertGqzFeedbackReqDTO
     * @author dragon
     * @date 2018/7/18 下午5:14
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean submitFeedback(InsertGqzFeedbackReqDTO insertGqzFeedbackReqDTO) {
        GqzAppFeedback feedback = new GqzAppFeedback();
        feedback.setNickName(insertGqzFeedbackReqDTO.getNickName());
        feedback.setFeedbackContent(insertGqzFeedbackReqDTO.getFeedbackContent());
        feedback.setCreateTime(new Date());
        int count = feedbackMapper.insert(feedback);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("反馈提交失败");
        }
    }
}
