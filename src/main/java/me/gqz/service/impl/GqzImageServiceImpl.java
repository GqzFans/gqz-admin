package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppImage;
import me.gqz.mapper.GqzAppImageMapper;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;
import me.gqz.model.dto.req.OperateGqzImageReqDTO;
import me.gqz.model.dto.req.QueryGqzImageReqDTO;
import me.gqz.service.GqzImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: GqzImageServiceImpl. </p>
 * <p>Description 高秋梓资源站图片Service </p>
 * @author dragon
 * @date 2018/7/14 下午4:43
 */
@Slf4j
@Service
public class GqzImageServiceImpl implements GqzImageService {

    @Resource
    private GqzAppImageMapper appImageMapper;

    /**
     * <p>Title: addGqzImage. </p>
     * <p>资源站添加图片 </p>
     * @param insertGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/14 下午4:44
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGqzImage(InsertGqzImageReqDTO insertGqzImageReqDTO, AuthUserDTO authUser) {
        List<GqzAppImage> imageList = new ArrayList<>();
        // 用户信息
        String userId = authUser.getUserId();
        String nickName = authUser.getNickName();
        // 图片信息
        String imageDescription = insertGqzImageReqDTO.getImageDescription();
        Integer listSize = insertGqzImageReqDTO.getImageList().size();
        for (int i = 0; i < listSize; i++) {
            String imageUrl = insertGqzImageReqDTO.getImageList().get(i).getAttachmentUrl();
            GqzAppImage appImage = new GqzAppImage();
            appImage.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            appImage.setCreateUserId(userId);
            appImage.setCreateUserName(nickName);
            appImage.setCreateTime(new Date());
            appImage.setImageDescription(imageDescription);
            appImage.setImageStatus(SystemBaseConstants.Y);
            appImage.setImageUrl(imageUrl);
            appImage.setVersion(SystemBaseConstants.VERSION_INIT);
            imageList.add(appImage);
        }
        Integer count = appImageMapper.addGqzImage(imageList);
        log.info("上传图片数量 = {} , 保存图片数量 = {}", listSize, count);
        if (listSize.equals(count)) {
            return true;
        } else {
            throw new BusinessException("保存图片信息出错");
        }
    }

    /**
     * <p>Title: queryGqzImageList. </p>
     * <p>图片管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/14 下午11:33
     * @return List<GqzAppImage>
     */
    @Override
    public List<GqzAppImage> queryGqzImageList(QueryGqzImageReqDTO param) {
        return appImageMapper.queryGqzImageList(param);
    }

    /**
     * <p>Title: dropImageById. </p>
     * <p>图片管理下架图片 </p>
     * @param operateGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/15 下午6:03
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean dropImageById(OperateGqzImageReqDTO operateGqzImageReqDTO, AuthUserDTO authUser) {
        GqzAppImage appImage = new GqzAppImage();
        appImage.setId(operateGqzImageReqDTO.getId());
        appImage.setVersion(operateGqzImageReqDTO.getVersion());
        appImage.setImageStatus(SystemBaseConstants.N);
        appImage.setUpdateTime(new Date());
        appImage.setUpdateUserId(authUser.getUserId());
        appImage.setUpdateUserName(authUser.getNickName());
        Integer count = appImageMapper.dropImageById(appImage);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("下架图片失败");
        }
    }

    /**
     * <p>Title: upImageById. </p>
     * <p>图片管理上架图片 </p>
     * @param operateGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/15 下午6:03
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upImageById(OperateGqzImageReqDTO operateGqzImageReqDTO, AuthUserDTO authUser) {
        GqzAppImage appImage = new GqzAppImage();
        appImage.setId(operateGqzImageReqDTO.getId());
        appImage.setVersion(operateGqzImageReqDTO.getVersion());
        appImage.setImageStatus(SystemBaseConstants.Y);
        appImage.setUpdateTime(new Date());
        appImage.setUpdateUserId(authUser.getUserId());
        appImage.setUpdateUserName(authUser.getNickName());
        Integer count = appImageMapper.upImageById(appImage);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("上架图片失败");
        }
    }

    /**
     * <p>Title: deleteImageById. </p>
     * <p>图片管理删除图片 </p>
     * @param operateGqzImageReqDTO
     * @author dragon
     * @date 2018/7/15 下午7:58
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteImageById(OperateGqzImageReqDTO operateGqzImageReqDTO) {
        GqzAppImage appImage = new GqzAppImage();
        appImage.setId(operateGqzImageReqDTO.getId());
        appImage.setVersion(operateGqzImageReqDTO.getVersion());
        int count = appImageMapper.delete(appImage);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("删除图片失败");
        }
    }
}
