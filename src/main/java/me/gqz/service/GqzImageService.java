package me.gqz.service;

import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;

/**
 * <p>Title: GqzImageService. </p>
 * <p>Description 高秋梓资源站图片Service </p>
 * @author dragon
 * @date 2018/7/14 下午4:23
 */
public interface GqzImageService {
    /**
     * <p>Title: addGqzImage. </p>
     * <p>资源站添加图片 </p>
     * @param insertGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/14 下午4:41
     * @return boolean
     */
    Boolean addGqzImage(InsertGqzImageReqDTO insertGqzImageReqDTO, AuthUserDTO authUser);
}
