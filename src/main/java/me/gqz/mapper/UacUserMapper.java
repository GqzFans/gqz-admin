package me.gqz.mapper;


import me.gqz.config.MyMapper;
import me.gqz.domain.UacUser;
import me.gqz.model.dto.req.DropUserReqDTO;
import me.gqz.model.vo.UserListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: UacUserMapper. </p>
 * <p>Description 用户账户信息Mapper </p>
 * @author dragon
 * @date 2018/4/4 下午7:02
 */
@Repository
public interface UacUserMapper extends MyMapper<UacUser> {
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
     * @return Integer
     */
    Integer dropUserById(DropUserReqDTO dropUserReqDTO);
}