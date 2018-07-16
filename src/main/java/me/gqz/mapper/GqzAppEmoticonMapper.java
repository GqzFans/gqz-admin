package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.model.dto.req.QueryGqzEmoticonReqDTO;

import java.util.List;

/**
 * <p>Title: GqzAppEmoticonMapper. </p>
 * <p>Description 高秋梓资源站表情包管理 </p>
 * @author dragon
 * @date 2018/7/15 下午10:16
 */
public interface GqzAppEmoticonMapper extends MyMapper<GqzAppEmoticon> {
    /**
     * <p>Title: addGqzEmoticon. </p>
     * <p>资源站添加表情包 </p>
     * @param emoticonList
     * @author dragon
     * @date 2018/7/16 上午10:37
     * @return Integer
     */
    Integer addGqzEmoticon(List<GqzAppEmoticon> emoticonList);

    /**
     * <p>Title: queryGqzEmoticonList. </p>
     * <p>表情包管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/16 上午10:58
     * @return List<GqzAppEmoticon>
     */
    List<GqzAppEmoticon> queryGqzEmoticonList(QueryGqzEmoticonReqDTO param);

    /**
     * <p>Title: upEmoticonById. </p>
     * <p>表情包管理上架表情包 </p>
     * @param appEmoticon
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Boolean
     */
    Integer upEmoticonById(GqzAppEmoticon appEmoticon);

    /**
     * <p>Title: dropEmoticonById. </p>
     * <p>表情包管理下架表情包 </p>
     * @param appEmoticon
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Integer
     */
    Integer dropEmoticonById(GqzAppEmoticon appEmoticon);

    /**
     * <p>Title: queryThisMonthUploadCount. </p>
     * <p>获取本月新增数据数量 </p>
     * @author dragon
     * @date 2018/7/16 下午2:51
     * @return Integer
     */
    Integer queryThisMonthUploadCount();
}