package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.domain.GqzTencentWsData;
import me.gqz.mapper.GqzTencentWsDataMapper;
import me.gqz.model.dto.req.InsertGqzWsDataReqDTO;
import me.gqz.model.dto.req.OperateGqzWsDataReqDTO;
import me.gqz.service.GqzTencentWsDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: GqzTencentWsDataServiceImpl. </p>
 * <p>Description 高秋梓数据组-微视数据分析Service </p>
 * @author dragon
 * @date 2018/8/2 下午9:47
 */
@Slf4j
@Service
public class GqzTencentWsDataServiceImpl extends BaseService<GqzTencentWsData> implements GqzTencentWsDataService {

    @Resource
    private GqzTencentWsDataMapper wsDataMapper;

    /**
     * <p>Title: addGqzWsData. </p>
     * <p>数据站添加微视数据分析 </p>
     * @param insertGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/2 下午9:02
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGqzWsData(InsertGqzWsDataReqDTO insertGqzWsDataReqDTO, AuthUserDTO authUser) {
        log.info("资源站添加视频 = {}", insertGqzWsDataReqDTO);
        // 用户信息
        String userId = authUser.getUserId();
        String nickName = authUser.getNickName();
        // tencent-ws
        String wsVideoId = insertGqzWsDataReqDTO.getWsVideoId();
        String wsVideoTitle = insertGqzWsDataReqDTO.getWsVideoTitle();
        String wsVideoUrl = insertGqzWsDataReqDTO.getWsVideoUrl();
        // 数据
        GqzTencentWsData wsData = new GqzTencentWsData();
        wsData.setCreateUserId(userId);
        wsData.setCreateUserName(nickName);
        wsData.setCreateTime(new Date());
        wsData.setWorkerListenerStatus(Integer.parseInt(SystemBaseConstants.N));
        wsData.setWsVideoId(wsVideoId);
        wsData.setWsVideoTitle(wsVideoTitle);
        wsData.setWsVideoUrl(wsVideoUrl);
        wsData.setVersion(SystemBaseConstants.VERSION_INIT);
        Integer count = wsDataMapper.insert(wsData);
        if (count == 1) {
            return true;
        } else {
            throw new BusinessException("添加微视数据分析信息出错");
        }
    }

    /**
     * <p>Title: queryWsDataList. </p>
     * <p>查询分析列表 </p>
     * @author dragon
     * @date 2018/8/2 下午10:08
     * @return List<GqzTencentWsData>
     */
    @Override
    public List<GqzTencentWsData> queryWsDataList() {
        return wsDataMapper.selectAll();
    }

    /**
     * <p>Title: deleteWsDataById. </p>
     * <p>删除微视数据分析 </p>
     * @param operateGqzWsDataReqDTO
     * @author dragon
     * @date 2018/8/2 下午10:54
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWsDataById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO) {
        GqzTencentWsData wsData = new GqzTencentWsData();
        wsData.setId(operateGqzWsDataReqDTO.getId());
        wsData.setVersion(operateGqzWsDataReqDTO.getVersion());
        int count = wsDataMapper.delete(wsData);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("删除微视数据分析失败");
        }
    }

    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>开启数据统计 </p>
     * @param operateGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Boolean
     */
    @Override
    public Boolean upWsDataWorkerListenerById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO, AuthUserDTO authUser) {
        GqzTencentWsData wsData = new GqzTencentWsData();
        wsData.setId(operateGqzWsDataReqDTO.getId());
        wsData.setVersion(operateGqzWsDataReqDTO.getVersion());
        wsData.setWorkerListenerStatus(Integer.parseInt(SystemBaseConstants.Y));
        Integer count = wsDataMapper.upWsDataWorkerListenerById(wsData);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("开启数据统计失败");
        }
    }

    /**
     * <p>Title: upWsDataWorkerListenerById. </p>
     * <p>关闭数据统计 </p>
     * @param operateGqzWsDataReqDTO
     * @param authUser
     * @author dragon
     * @date 2018/8/3 下午5:57
     * @return Boolean
     */
    @Override
    public Boolean dropWsDataWorkerListenerById(OperateGqzWsDataReqDTO operateGqzWsDataReqDTO, AuthUserDTO authUser) {
        GqzTencentWsData wsData = new GqzTencentWsData();
        wsData.setId(operateGqzWsDataReqDTO.getId());
        wsData.setVersion(operateGqzWsDataReqDTO.getVersion());
        wsData.setWorkerListenerStatus(Integer.parseInt(SystemBaseConstants.N));
        Integer count = wsDataMapper.dropWsDataWorkerListenerById(wsData);
        if (1 == count) {
            return true;
        } else {
            throw new BusinessException("关闭数据统计失败");
        }
    }

    /**
     * <p>Title: getStartWsData. </p>
     * <p>查询开启数据统计的微视短视频集合 </p>
     * @author dragon
     * @date 2018/8/3 下午10:22
     * @return List<GqzTencentWsData>
     */
    @Override
    public List<GqzTencentWsData> getStartWsData() {
        return wsDataMapper.getStartWsData();
    }
}
