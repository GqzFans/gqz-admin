package me.gqz.service;


import me.gqz.model.dto.req.UacChangePasswordReqDTO;
import me.gqz.model.dto.req.UacRegisterReqDTO;

/**
 * <p>Title: UacRegisterService. </p>
 * <p>Description 用户账户中心注册Service </p>
 * @author dragon
 * @date 2018/4/8 下午3:48
 */
public interface UacRegisterService {

    /**
     * <p>Title: doRegister. </p>
     * <p>用户注册Service </p>
     * @param uacRegisterReqDTO
     * @author dragon
     * @date 2018/4/8 下午3:48
     * @return java.lang.Integer
     */
    Integer doRegister(UacRegisterReqDTO uacRegisterReqDTO);

    /**
     * <p>Title: changePassword. </p>
     * <p>用户修改密码 </p>
     * @param changePasswordReqDTO
     * @author dragon
     * @date 2018/7/24 上午10:31
     * @return Boolean
     */
    Boolean changePassword(UacChangePasswordReqDTO changePasswordReqDTO);
}
