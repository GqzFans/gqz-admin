package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzAppVideo;
import me.gqz.model.dto.req.QueryGqzVideoReqDTO;

import java.util.List;

/**
 * <p>Title: GqzAppVideoMapper. </p>
 * @author dragon
 * @date 2018/7/26 下午2:22
 */
public interface GqzAppVideoMapper extends MyMapper<GqzAppVideo> {
    /**
     * <p>Title: queryGqzVideoList. </p>
     * <p>视频管理分页列表查询 </p>
     * @param param
     * @author dragon
     * @date 2018/7/26 下午5:50
     * @return List<GqzAppVideo>
     */
    List<GqzAppVideo> queryGqzVideoList(QueryGqzVideoReqDTO param);

    /**
     * <p>Title: upVideoById. </p>
     * <p>上架视频 </p>
     * @param video
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    Integer upVideoById(GqzAppVideo video);

    /**
     * <p>Title: dropVideoById. </p>
     * <p>下架视频 </p>
     * @param video
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    Integer dropVideoById(GqzAppVideo video);

    /**
     * <p>Title: appGetGqzVideoList. </p>
     * <p>小程序查询视频列表分页接口 </p>
     * @author dragon
     * @date 2018/7/26 下午10:11
     * @return List<GqzAppVideo>
     */
    List<GqzAppVideo> appGetGqzVideoList();
}