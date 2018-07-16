package me.gqz.service;

import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.model.dto.req.InsertGqzEmoticonReqDTO;
import me.gqz.model.dto.req.OperateGqzEmoticonReqDTO;
import me.gqz.model.dto.req.QueryGqzEmoticonReqDTO;

import java.util.List;

/**
 * <p>Title: GqzEmoticonService. </p>
 * <p>Description 高秋梓资源站表情包Service </p>
 * @author dragon
 * @date 2018/7/15 下午10:24
 */
public interface GqzEmoticonService {
    /**
     * <p>Title: addGqzEmoticon. </p>
     * <p>资源站添加表情包 </p>
     * @param insertGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午10:37
     * @return Boolean
     */
    Boolean addGqzEmoticon(InsertGqzEmoticonReqDTO insertGqzEmoticonReqDTO, AuthUserDTO authUser);

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
     * @param operateGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Boolean
     */
    Boolean upEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: dropEmoticonById. </p>
     * <p>表情包管理下架表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Boolean
     */
    Boolean dropEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: deleteEmoticonById. </p>
     * <p>表情包管理-删除表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @author dragon
     * @date 2018/7/16 上午11:46
     * @return Boolean
     */
    Boolean deleteEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO);
}
