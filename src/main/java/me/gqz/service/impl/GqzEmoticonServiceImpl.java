package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppEmoticon;
import me.gqz.mapper.GqzAppEmoticonMapper;
import me.gqz.model.dto.req.InsertGqzEmoticonReqDTO;
import me.gqz.model.dto.req.OperateGqzEmoticonReqDTO;
import me.gqz.model.dto.req.QueryGqzEmoticonReqDTO;
import me.gqz.service.GqzEmoticonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: GqzEmoticonServiceImpl. </p>
 * <p>Description 高秋梓资源站表情包Service </p>
 * @author dragon
 * @date 2018/7/15 下午10:25
 */
@Slf4j
@Service
public class GqzEmoticonServiceImpl extends BaseService<GqzAppEmoticon> implements GqzEmoticonService {

    @Resource
    private GqzAppEmoticonMapper appEmoticonMapper;

    /**
     * <p>Title: addGqzEmoticon. </p>
     * <p>资源站添加表情包 </p>
     * @param insertGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午10:37
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGqzEmoticon(InsertGqzEmoticonReqDTO insertGqzEmoticonReqDTO, AuthUserDTO authUser) {
        List<GqzAppEmoticon> emoticonList = new ArrayList<>();
        // 用户信息
        String userId = authUser.getUserId();
        String nickName = authUser.getNickName();
        // 表情包信息
        String emoticonDescription = insertGqzEmoticonReqDTO.getEmoticonDescription();
        Integer listSize = insertGqzEmoticonReqDTO.getImageList().size();
        for (int i = 0; i < listSize; i++) {
            String emoticonUrl = insertGqzEmoticonReqDTO.getImageList().get(i).getAttachmentUrl();
            GqzAppEmoticon appEmoticon = new GqzAppEmoticon();
            appEmoticon.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            appEmoticon.setCreateUserId(userId);
            appEmoticon.setCreateUserName(nickName);
            appEmoticon.setCreateTime(new Date());
            appEmoticon.setEmoticonDescription(emoticonDescription);
            appEmoticon.setEmoticonStatus(SystemBaseConstants.Y);
            appEmoticon.setEmoticonUrl(emoticonUrl);
            appEmoticon.setVersion(SystemBaseConstants.VERSION_INIT);
            emoticonList.add(appEmoticon);
        }
        Integer count = appEmoticonMapper.addGqzEmoticon(emoticonList);
        log.info("上传表情包数量 = {} , 保存表情包数量 = {}", listSize, count);
        if (listSize.equals(count)) {
            return true;
        } else {
            throw new BusinessException("保存表情包信息出错");
        }
    }

    /**
     * <p>Title: queryGqzEmoticonList. </p>
     * <p>表情包管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/16 上午10:58
     * @return List<GqzAppEmoticon>
     */
    @Override
    public List<GqzAppEmoticon> queryGqzEmoticonList(QueryGqzEmoticonReqDTO param) {
        return appEmoticonMapper.queryGqzEmoticonList(param);
    }

    /**
     * <p>Title: upEmoticonById. </p>
     * <p>表情包管理上架表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO, AuthUserDTO authUser) {
        GqzAppEmoticon appEmoticon = new GqzAppEmoticon();
        appEmoticon.setId(operateGqzEmoticonReqDTO.getId());
        appEmoticon.setVersion(operateGqzEmoticonReqDTO.getVersion());
        appEmoticon.setEmoticonStatus(SystemBaseConstants.Y);
        appEmoticon.setUpdateTime(new Date());
        appEmoticon.setUpdateUserId(authUser.getUserId());
        appEmoticon.setUpdateUserName(authUser.getNickName());
        Integer count = appEmoticonMapper.upEmoticonById(appEmoticon);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("上架表情包失败");
        }
    }

    /**
     * <p>Title: dropEmoticonById. </p>
     * <p>表情包管理下架表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/16 上午11:25
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean dropEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO, AuthUserDTO authUser) {
        GqzAppEmoticon appEmoticon = new GqzAppEmoticon();
        appEmoticon.setId(operateGqzEmoticonReqDTO.getId());
        appEmoticon.setVersion(operateGqzEmoticonReqDTO.getVersion());
        appEmoticon.setEmoticonStatus(SystemBaseConstants.N);
        appEmoticon.setUpdateTime(new Date());
        appEmoticon.setUpdateUserId(authUser.getUserId());
        appEmoticon.setUpdateUserName(authUser.getNickName());
        Integer count = appEmoticonMapper.dropEmoticonById(appEmoticon);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("下架表情包失败");
        }
    }

    /**
     * <p>Title: deleteEmoticonById. </p>
     * <p>表情包管理-删除表情包 </p>
     * @param operateGqzEmoticonReqDTO
     * @author dragon
     * @date 2018/7/16 上午11:48
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteEmoticonById(OperateGqzEmoticonReqDTO operateGqzEmoticonReqDTO) {
        GqzAppEmoticon appEmoticon = new GqzAppEmoticon();
        appEmoticon.setId(operateGqzEmoticonReqDTO.getId());
        appEmoticon.setVersion(operateGqzEmoticonReqDTO.getVersion());
        int count = appEmoticonMapper.delete(appEmoticon);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("删除表情包失败");
        }
    }

    /**
     * <p>Title: queryThisMonthUploadCount. </p>
     * <p>获取本月新增数据数量 </p>
     * @author dragon
     * @date 2018/7/16 下午2:51
     * @return Integer
     */
    @Override
    public Integer queryThisMonthUploadCount() {
        return appEmoticonMapper.queryThisMonthUploadCount();
    }

    /**
     * <p>Title: appGetGqzEmoticonList. </p>
     * <p>小程序查询表情包列表分页 </p>
     * @author dragon
     * @date 2018/7/18 下午8:29
     * @return List<GqzAppEmoticon>
     */
    @Override
    public List<GqzAppEmoticon> appGetGqzEmoticonList() {
        return appEmoticonMapper.appGetGqzEmoticonList();
    }
}
