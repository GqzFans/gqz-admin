package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzAppImage;
import me.gqz.model.dto.req.QueryGqzImageReqDTO;

import java.util.List;

/**
 * <p>Title: GqzAppImageMapper. </p>
 * <p>Description 高秋梓资源站图片管理 </p>
 * @author dragon
 * @date 2018/7/14 下午4:21
 */
public interface GqzAppImageMapper extends MyMapper<GqzAppImage> {
    /**
     * <p>Title: addGqzImage. </p>
     * <p>资源站添加图片-批量 </p>
     * @param imageList
     * @author dragon
     * @date 2018/7/14 下午5:04
     * @return count
     */
    Integer addGqzImage(List<GqzAppImage> imageList);

    /**
     * <p>Title: queryGqzImageList. </p>
     * <p>图片管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/14 下午11:33
     * @return List<GqzAppImage>
     */
    List<GqzAppImage> queryGqzImageList(QueryGqzImageReqDTO param);
}