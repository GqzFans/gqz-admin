package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppImage;
import me.gqz.mapper.GqzAppImageMapper;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;
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
}
