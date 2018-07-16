package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.constant.UacTokenConstants;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.utils.ThreadLocalMap;
import me.gqz.domain.UacUser;
import me.gqz.domain.UacUserLoginLog;
import me.gqz.enums.UacExceptionEnums;
import me.gqz.mapper.UacUserLoginLogMapper;
import me.gqz.mapper.UacUserMapper;
import me.gqz.model.dto.req.UacLoginReqDTO;
import me.gqz.model.dto.res.UacLoginResDTO;
import me.gqz.service.UacLoginService;
import me.gqz.service.UacTokenService;
import me.gqz.utils.AesUtil;
import me.gqz.utils.CheckArgumentUtil;
import me.gqz.utils.PasswordUtils;
import me.gqz.utils.RequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>Title: UacLoginService. </p>
 * <p>Description 用户账户中心登录Service </p>
 * @author dragon
 * @date 2018/4/4 下午3:40
 */
@Slf4j
@Service
public class UacLoginServiceImpl implements UacLoginService {

    @Resource
    private UacUserMapper uacUserMapper;
    @Resource
    private UacUserLoginLogMapper uacUserLoginLogMapper;
    @Resource
    private UacTokenService uacTokenService;

    /**
     * <p>Title: doLogin. </p>
     * <p>用户登录 </p>
     * @param uacLoginReqDTO
     * @author dragon
     * @date 2018/4/4 下午3:47
     * @return me.uac.model.dto.res.UacLoginResDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UacLoginResDTO doLogin(UacLoginReqDTO uacLoginReqDTO) {
        log.info("用户登录，登录信息为 = {}", uacLoginReqDTO.toString());
        // 通过加密的用户信息进行解密
        String loginName = uacLoginReqDTO.getLoginName();
        String loginPwd = uacLoginReqDTO.getLoginPwd();
        String secretToken = uacLoginReqDTO.getSecretToken();
        loginName = AesUtil.decrypt(loginName, secretToken, false, secretToken);
        loginPwd = AesUtil.decrypt(loginPwd, secretToken, false, secretToken);
        // 将解密后的数据重新加密MD5进行数据库校验
        loginPwd = PasswordUtils.encodeByAES(loginPwd);
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(loginName);
        uacUser.setLoginPwd(loginPwd);
        uacUser.setUserStatus(SystemBaseConstants.Y);
        // 查询UAC数据库是否数据吻合
        uacUser = uacDoLogin(uacUser);
        if (!CommUsualUtils.isOEmptyOrNull(uacUser)) {
            String userId = uacUser.getId();
            String serialNo = uacUser.getSerialNo();
            String nickName = uacUser.getNickName();
            loginName = uacUser.getLoginName();
            // 插入用户日志表
            String systemId = uacLoginReqDTO.getSystemId();
            insertUserLoginLog(userId, loginName, systemId);
            // 返回数据，作为用户base数据
            UacLoginResDTO uacLoginResDTO = new UacLoginResDTO();
            uacLoginResDTO.setId(userId);
            uacLoginResDTO.setSerialNo(serialNo);
            uacLoginResDTO.setLoginName(loginName);
            uacLoginResDTO.setNickName(nickName);
            // 获取TOKEN
            uacLoginResDTO.setToken(getToken(uacUser));
            return uacLoginResDTO;
        }
        return null;
    }

    /**
     * <p>Title: getToken. </p>
     * <p>成功登录后，获取TOKEN </p>
     * @param uacUser
     * @author dragon
     * @date 2018/4/9 下午5:37
     * @return java.lang.String TOKEN
     */
    public String getToken(UacUser uacUser) {
        // TOKEN处理
        log.info("登录成功，获取用户TOKEN。用户 = {}", uacUser.getLoginName());
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserId(uacUser.getSerialNo());
        authUserDTO.setUserName(uacUser.getLoginName());
        authUserDTO.setNickName(uacUser.getNickName());
        String token = uacTokenService.encodeToken(authUserDTO);
        // 将登录信息放进ThreadLocalMap
        ThreadLocalMap.put(UacTokenConstants.JWT_TOKEN, token);
        return token;
    }

    /**
     * <p>Title: insertUserLoginLog. </p>
     * <p>插入用户日志表 </p>
     * @param userId
     * @param loginName
     * @param systemId
     * @author dragon
     * @date 2018/4/8 下午3:01
     */
    private void insertUserLoginLog(String userId, String loginName, String systemId) {
        UacUserLoginLog uacUserLoginLog = new UacUserLoginLog();
        uacUserLoginLog.setUserId(userId);
        uacUserLoginLog.setLoginName(loginName);
        uacUserLoginLog.setLoginIp(RequestUtil.getRequest().getRemoteAddr());
        uacUserLoginLog.setSystemId(systemId);
        uacUserLoginLog.setLoginTime(new Date());
        uacUserLoginLogMapper.insert(uacUserLoginLog);
    }

    /**
     * <p>Title: uacDoLogin. </p>
     * <p>校验登录信息 </p>
     * @param uacUser
     * @author dragon
     * @date 2018/4/4 下午5:56
     * @return
     */
    private UacUser uacDoLogin(UacUser uacUser) {
        log.info("用户登录：登录参数 = {}", uacUser.toString());
        CheckArgumentUtil.checkArgument(!CommUsualUtils.isNull(uacUser.getLoginName()), UacExceptionEnums.UAC_LOGIN_ERROR_10004);
        CheckArgumentUtil.checkArgument(!CommUsualUtils.isNull(uacUser.getLoginPwd()), UacExceptionEnums.UAC_LOGIN_ERROR_10005);
        uacUser = uacUserMapper.selectOne(uacUser);
        if (!CommUsualUtils.isOEmptyOrNull(uacUser)) {
            log.info("用户登录：获取登录信息 = {}", uacUser.toString());
        } else {
            log.error("用户登录失败：无此用户信息或此用户已被停用");
        }
        return uacUser;
    }
}
