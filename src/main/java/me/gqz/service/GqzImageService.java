package me.gqz.service;

import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppImage;
import me.gqz.model.dto.req.InsertGqzImageReqDTO;
import me.gqz.model.dto.req.OperateGqzImageReqDTO;
import me.gqz.model.dto.req.QueryGqzImageReqDTO;

import java.util.List;

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

    /**
     * <p>Title: queryGqzImageList. </p>
     * <p>图片管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/14 下午11:33
     * @return List<GqzAppImage>
     */
    List<GqzAppImage> queryGqzImageList(QueryGqzImageReqDTO param);

    /**
     * <p>Title: dropImageById. </p>
     * <p>图片管理下架图片 </p>
     * @param operateGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/15 下午6:03
     * @return Boolean
     */
    Boolean dropImageById(OperateGqzImageReqDTO operateGqzImageReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: upImageById. </p>
     * <p>图片管理上架图片 </p>
     * @param operateGqzImageReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/15 下午6:03
     * @return Boolean
     */
    Boolean upImageById(OperateGqzImageReqDTO operateGqzImageReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: deleteImageById. </p>
     * <p>图片管理删除图片 </p>
     * @param operateGqzImageReqDTO
     * @author dragon
     * @date 2018/7/15 下午7:58
     * @return Boolean
     */
    Boolean deleteImageById(OperateGqzImageReqDTO operateGqzImageReqDTO);
}
