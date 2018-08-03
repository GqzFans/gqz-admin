package me.gqz.service;

import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzTencentWsData;
import me.gqz.model.dto.req.InsertGqzWsDataReqDTO;
import me.gqz.model.dto.req.OperateGqzWsDataReqDTO;

import java.util.List;

/**
 * <p>Title: GqzTencentWsDataService. </p>
 * <p>Description 高秋梓数据组-微视数据分析Service </p>
 * @author dragon
 * @date 2018/8/2 下午6:00
 */
public interface GqzTencentWsDataService extends IService<GqzTencentWsData>  {
    /**
     * <p>Title: addGqzWsData. </p>
     * <p>数据站添加微视数据分析 </p>
     * @param insertGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/2 下午9:02
     * @return Boolean
     */
    Boolean addGqzWsData(InsertGqzWsDataReqDTO insertGqzWsDataReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: queryWsDataList. </p>
     * <p>查询分析列表 </p>
     * @author dragon
     * @date 2018/8/2 下午10:08
     * @return List<GqzTencentWsData>
     */
    List<GqzTencentWsData> queryWsDataList();

    /**
     * <p>Title: deleteWsDataById. </p>
     * <p>删除微视数据分析 </p>
     * @param operateGqzWsDataReqDTO
     * @author dragon
     * @date 2018/8/2 下午10:54
     * @return Boolean
     */
    Boolean deleteWsDataById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO);

    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>开启数据统计 </p>
     * @param operateGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Boolean
     */
    Boolean upWsDataWorkerListenerById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>关闭数据统计 </p>
     * @param operateGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Boolean
     */
    Boolean dropWsDataWorkerListenerById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO, AuthUserDTO authUser);

    /**
     * <p>Title: getStartWsData. </p>
     * <p>查询开启数据统计的微视短视频集合 </p>
     * @author dragon
     * @date 2018/8/3 下午10:22
     * @return List<GqzTencentWsData>
     */
    List<GqzTencentWsData> getStartWsData();
}
