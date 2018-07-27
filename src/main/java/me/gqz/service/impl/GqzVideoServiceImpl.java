package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppVideo;
import me.gqz.mapper.GqzAppVideoMapper;
import me.gqz.model.dto.req.InsertGqzVideoReqDTO;
import me.gqz.model.dto.req.OperateGqzVideoReqDTO;
import me.gqz.model.dto.req.QueryGqzVideoReqDTO;
import me.gqz.service.GqzVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: GqzVideoServiceImpl. </p>
 * <p>Description 高秋梓资源站视频Service </p>
 * @author dragon
 * @date 2018/7/26 下午5:26
 */
@Slf4j
@Service
public class GqzVideoServiceImpl extends BaseService<GqzAppVideo> implements GqzVideoService {

    @Resource
    private GqzAppVideoMapper videoMapper;

    /**
     * <p>Title: addGqzVideo. </p>
     * <p>资源站添加视频 </p>
     * @param insertGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午5:27
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGqzVideo(InsertGqzVideoReqDTO insertGqzVideoReqDTO, AuthUserDTO authUser) {
        // 用户信息
        String userId = authUser.getUserId();
        String nickName = authUser.getNickName();
        // 视频信息
        String videoTitle = insertGqzVideoReqDTO.getVideoTitle();
        String videoUrl = insertGqzVideoReqDTO.getVideoUrl();
        String videoVid = insertGqzVideoReqDTO.getVideoVid();
        String videoType = insertGqzVideoReqDTO.getVideoType();
        // 数据
        GqzAppVideo video = new GqzAppVideo();
        video.setCreateTime(new Date());
        video.setCreateUserId(userId);
        video.setCreateUserName(nickName);
        video.setVersion(SystemBaseConstants.VERSION_INIT);
        video.setVideoTitle(videoTitle);
        video.setVideoUrl(videoUrl);
        video.setVideoVid(videoVid);
        video.setVideoType(videoType);
        video.setVideoStatus(SystemBaseConstants.Y);
        Integer count = videoMapper.insert(video);
        log.info("资源站添加视频 = {}", insertGqzVideoReqDTO);
        if (count == 1) {
            return true;
        } else {
            throw new BusinessException("添加视频信息出错");
        }
    }

    /**
     * <p>Title: queryGqzVideoList. </p>
     * <p>视频管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/26 下午5:50
     * @return List<GqzAppVideo>
     */
    @Override
    public List<GqzAppVideo> queryGqzVideoList(QueryGqzVideoReqDTO param) {
        return videoMapper.queryGqzVideoList(param);
    }

    /**
     * <p>Title: deleteVideoById. </p>
     * <p>视频管理删除图片 </p>
     * @param operateGqzVideoReqDTO
     * @author dragon
     * @date 2018/7/26 下午9:36
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO) {
        GqzAppVideo video = new GqzAppVideo();
        video.setId(operateGqzVideoReqDTO.getId());
        video.setVersion(operateGqzVideoReqDTO.getVersion());
        int count = videoMapper.delete(video);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("删除图片失败");
        }
    }

    /**
     * <p>Title: upVideoById. </p>
     * <p>上架视频 </p>
     * @param operateGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO, AuthUserDTO authUser) {
        GqzAppVideo video = new GqzAppVideo();
        video.setId(operateGqzVideoReqDTO.getId());
        video.setVersion(operateGqzVideoReqDTO.getVersion());
        video.setVideoStatus(SystemBaseConstants.Y);
        video.setUpdateTime(new Date());
        video.setUpdateUserId(authUser.getUserId());
        video.setUpdateUserName(authUser.getNickName());
        Integer count = videoMapper.upVideoById(video);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("上架视频失败");
        }
    }

    /**
     * <p>Title: dropVideoById. </p>
     * <p>下架视频 </p>
     * @param operateGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean dropVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO, AuthUserDTO authUser) {
        GqzAppVideo video = new GqzAppVideo();
        video.setId(operateGqzVideoReqDTO.getId());
        video.setVersion(operateGqzVideoReqDTO.getVersion());
        video.setVideoStatus(SystemBaseConstants.N);
        video.setUpdateTime(new Date());
        video.setUpdateUserId(authUser.getUserId());
        video.setUpdateUserName(authUser.getNickName());
        Integer count = videoMapper.dropVideoById(video);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("下架视频失败");
        }
    }

    /**
     * <p>Title: appGetGqzVideoList. </p>
     * <p>小程序查询视频列表分页接口 </p>
     * @author dragon
     * @date 2018/7/26 下午10:11
     * @return List<GqzAppVideo>
     */
    @Override
    public List<GqzAppVideo> appGetGqzVideoList() {
        return videoMapper.appGetGqzVideoList();
    }

    /**
     * <p>Title: queryThisMonthUploadCount. </p>
     * <p>获取本月新增数据数量 </p>
     * @author dragon
     * @date 2018/7/27 下午1:48
     * @return Integer
     */
    @Override
    public Integer queryThisMonthUploadCount() {
        return videoMapper.queryThisMonthUploadCount();
    }
}
