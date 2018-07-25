package me.gqz.service;

import me.gqz.domain.GqzAppFeedback;
import me.gqz.model.dto.req.InsertGqzFeedbackReqDTO;

import java.util.List;

/**
 * <p>Title: FeedbackService. </p>
 * <p>Description 小程序意见反馈Service </p>
 * @author dragon
 * @date 2018/7/18 下午5:13
 */
public interface FeedbackService extends IService<GqzAppFeedback> {
    /**
     * <p>Title: submitFeedback. </p>
     * <p>小程序提交意见反馈 </p>
     * @param insertGqzFeedbackReqDTO
     * @author dragon
     * @date 2018/7/18 下午5:14
     * @return Boolean
     */
    Boolean submitFeedback(InsertGqzFeedbackReqDTO insertGqzFeedbackReqDTO);

    /**
     * <p>Title: queryFeedback. </p>
     * <p>查询意见反馈列表 </p>
     * @author dragon
     * @date 2018/7/25 下午12:42
     * @return List<GqzAppFeedback>
     */
    List<GqzAppFeedback> queryFeedback();
}
