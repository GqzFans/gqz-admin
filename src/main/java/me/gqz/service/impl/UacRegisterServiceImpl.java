package me.gqz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.domain.UacUser;
import me.gqz.domain.UacUserRegisterLog;
import me.gqz.enums.UacExceptionEnums;
import me.gqz.mapper.UacUserMapper;
import me.gqz.mapper.UacUserRegisterLogMapper;
import me.gqz.model.dto.req.UacRegisterReqDTO;
import me.gqz.service.UacRegisterService;
import me.gqz.utils.PasswordUtils;
import me.gqz.utils.RedisUtils;
import me.gqz.utils.RequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>Title: UacRegisterServiceImpl. </p>
 * <p>Description 用户账户中心注册Service </p>
 * @author dragon
 * @date 2018/4/8 下午4:03
 */
@Slf4j
@Service
public class UacRegisterServiceImpl implements UacRegisterService {

    @Resource
    private UacUserMapper uacUserMapper;
    @Resource
    private UacUserRegisterLogMapper uacUserRegisterLogMapper;
    @Resource
    private RedisUtils redisUtils;

    /**
     * <p>Title: doRegister. </p>
     * <p>Description 用户注册 </p>
     * @author dragon
     * @date 2018/4/8 下午4:35
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer doRegister(UacRegisterReqDTO uacRegisterReqDTO) {
        if (!uacRegisterReqDTO.getLoginPwd().equals(uacRegisterReqDTO.getConfirmPwd())) {
            throw new BusinessException(UacExceptionEnums.UAC_REGISTER_ERROR_10108.code(), UacExceptionEnums.UAC_REGISTER_ERROR_10108.errorMsg());
        }
        validateRegisterInfo(uacRegisterReqDTO);
        // Redis生成唯一用户ID
        String userId = redisUtils.getUserSerialNo();
        // 当前时间
        Date nowDate = new Date();
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(uacRegisterReqDTO.getLoginName());
        uacUser.setLoginPwd(PasswordUtils.encodeByAES(uacRegisterReqDTO.getLoginPwd()));
        uacUser.setNickName(uacRegisterReqDTO.getNickName());
        uacUser.setSerialNo(userId);
        uacUser.setUserStatus(SystemBaseConstants.Y);
        uacUser.setCreatedTime(nowDate);
        uacUser.setVersion(SystemBaseConstants.VERSION_INIT);
        int insertUacUserCount = uacUserMapper.insert(uacUser);
        // 存入用户注册表，可作为数据分析使用
        UacUserRegisterLog uacUserRegisterLog = new UacUserRegisterLog();
        uacUserRegisterLog.setUserId(userId);
        uacUserRegisterLog.setSystemId(uacRegisterReqDTO.getSystemId());
        uacUserRegisterLog.setRegisterTime(nowDate);
        uacUserRegisterLog.setRegisterIp(RequestUtil.getRequest().getRemoteAddr());
        uacUserRegisterLog.setMail(uacRegisterReqDTO.getMail());
        uacUserRegisterLogMapper.insert(uacUserRegisterLog);
        return insertUacUserCount;
    }

    /**
     * <p>Title: UacRegisterServiceImpl. </p>
     * <p>Description 检验用户是否存在 </p>
     * @author dragon
     * @date 2018/4/8 下午4:35
     */
    private void validateRegisterInfo(UacRegisterReqDTO uacRegisterReqDTO) {
        String loginName = uacRegisterReqDTO.getLoginName();
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(loginName);
        int userCount = uacUserMapper.selectCount(uacUser);
        if (userCount > 0) {
            throw new BusinessException(UacExceptionEnums.UAC_REGISTER_ERROR_10007.code(), UacExceptionEnums.UAC_REGISTER_ERROR_10007.errorMsg());
        }
    }
}
