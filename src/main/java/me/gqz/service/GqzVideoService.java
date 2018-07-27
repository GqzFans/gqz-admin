package me.gqz.service;

import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzAppVideo;
import me.gqz.model.dto.req.InsertGqzVideoReqDTO;
import me.gqz.model.dto.req.OperateGqzVideoReqDTO;
import me.gqz.model.dto.req.QueryGqzVideoReqDTO;

import java.util.List;

/**
 * <p>Title: GqzVideoService. </p>
 * <p>Description 高秋梓资源站视频Service </p>
 * @author dragon
 * @date 2018/7/26 下午5:26
 */
public interface GqzVideoService extends IService<GqzAppVideo> {
    
    /**
     * <p>Title: addGqzVideo. </p>
     * <p>资源站添加视频 </p>
     * @param insertGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午5:27
     * @return Boolean
     */
    Boolean addGqzVideo(InsertGqzVideoReqDTO insertGqzVideoReqDTO, AuthUserDTO authUser);

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
     * <p>Title: deleteVideoById. </p>
     * <p>视频管理删除图片 </p>
     * @param operateGqzVideoReqDTO
     * @author dragon
     * @date 2018/7/26 下午9:36
     * @return Boolean
     */
    Boolean deleteVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO);

    /**
     * <p>Title: upVideoById. </p>
     * <p>上架视频 </p>
     * @param operateGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    Boolean upVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: dropVideoById. </p>
     * <p>下架视频 </p>
     * @param operateGqzVideoReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/7/26 下午9:58
     * @return Boolean
     */
    Boolean dropVideoById(OperateGqzVideoReqDTO operateGqzVideoReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: appGetGqzVideoList. </p>
     * <p>小程序查询视频列表分页接口 </p>
     * @author dragon
     * @date 2018/7/26 下午10:11
     * @return List<GqzAppVideo>
     */
    List<GqzAppVideo> appGetGqzVideoList();

    /**
     * <p>Title: queryThisMonthUploadCount. </p>
     * <p>获取本月新增数据数量 </p>
     * @author dragon
     * @date 2018/7/27 下午1:48
     * @return Integer
     */
    Integer queryThisMonthUploadCount();
}
