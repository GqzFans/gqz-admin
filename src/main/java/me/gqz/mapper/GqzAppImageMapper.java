package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzAppImage;

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
}