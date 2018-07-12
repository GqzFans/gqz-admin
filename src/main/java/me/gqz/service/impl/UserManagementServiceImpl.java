package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.domain.UacUser;
import me.gqz.mapper.UacUserMapper;
import me.gqz.model.dto.req.DropUserReqDTO;
import me.gqz.model.vo.UserListVO;
import me.gqz.service.UserManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserManagementServiceImpl extends BaseService<UacUser> implements UserManagementService {

    @Resource
    private UacUserMapper uacUserMapper;

    /**
     * <p>Title: queryUserList. </p>
     * <p>用户管理分页列表查询 </p>
     * @author dragon
     * @date 2018/7/12 下午3:06
     * @return List<UserListVO>
     */
    @Override
    public List<UserListVO> queryUserList() {
        return uacUserMapper.queryUserList();
    }

    /**
     * <p>Title: dropUserById. </p>
     * <p>停用用户 </p>
     * @param dropUserReqDTO
     * @author dragon
     * @date 2018/7/12 下午4:29
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean dropUserById(DropUserReqDTO dropUserReqDTO) {
        Integer count = uacUserMapper.dropUserById(dropUserReqDTO);
        if (1 == count) {
            return true;
        }
        return false;
    }
}
