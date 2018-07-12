package me.gqz.service;

import me.gqz.domain.UacUser;
import me.gqz.model.dto.req.DropUserReqDTO;
import me.gqz.model.vo.UserListVO;

import java.util.List;

/**
 * <p>Title: UserManagementService. </p>
 * <p>Description 用户管理-用户信息Service </p>
 * @author dragon
 * @date 2018/7/12 下午3:00
 */
public interface UserManagementService extends IService<UacUser> {
    /**
     * <p>Title: queryUserList. </p>
     * <p>用户管理分页列表查询 </p>
     * @author dragon
     * @date 2018/7/12 下午3:06
     * @return List<UserListVO>
     */
    List<UserListVO> queryUserList();

    /**
     * <p>Title: dropUserById. </p>
     * <p>停用用户 </p>
     * @param dropUserReqDTO
     * @author dragon
     * @date 2018/7/12 下午4:29
     * @return Boolean
     */
    Boolean dropUserById(DropUserReqDTO dropUserReqDTO);
}
