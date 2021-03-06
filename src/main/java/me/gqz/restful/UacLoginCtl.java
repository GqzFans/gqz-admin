package me.gqz.restful;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.annotation.PrintParam;
import me.gqz.constant.UacTokenConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.enums.UacExceptionEnums;
import me.gqz.model.dto.req.UacLoginReqDTO;
import me.gqz.model.dto.res.UacLoginResDTO;
import me.gqz.service.UacLoginService;
import me.gqz.utils.PasswordUtils;
import me.gqz.utils.RedisOperationUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: UacLoginCtl. </p>
 * <p>Description 用户账户中心登录 </p>
 * @author dragon
 * @date 2018/3/30 下午5:35
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/uac/auth/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UacLoginCtl", tags = "用户账户中心登录接口", description = "用户账户中心登录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacLoginCtl extends BaseController {

    @Resource
    private RedisOperationUtils redisOperationUtils;
    @Resource
    private UacLoginService uacLoginService;

    @PrintParam
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(notes = "登录结果json", httpMethod = "POST", value = "用户登录接口")
    @BusinessLog(logInfo = "用户登录接口")
    public Wrapper<?> doLogin(@ApiParam(name = "uacLoginReqDTO", value = "用户登录请求参数")
                                  @RequestBody UacLoginReqDTO uacLoginReqDTO) {
        UacLoginResDTO uacLoginResDTO;
        String loginName = uacLoginReqDTO.getLoginName();
        String loginPwd = uacLoginReqDTO.getLoginPwd();
        String systemId = uacLoginReqDTO.getSystemId();
        try {
            if (CommUsualUtils.isSEmptyOrNull(loginName)) {
                throw new BusinessException("用户名不能为空");
            }
            if (CommUsualUtils.isSEmptyOrNull(loginPwd)) {
                throw new BusinessException("用户密码不能为空");
            }
            if (CommUsualUtils.isSEmptyOrNull(systemId)) {
                throw new BusinessException("系统标识不能为空");
            }
            String secretToken = redisOperationUtils.getKey(UacTokenConstants.SECRET_TOKEN);
            if (CommUsualUtils.isSEmptyOrNull(secretToken)) {
                throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10002.code(), UacExceptionEnums.UAC_TOKEN_ERROR_10002.errorMsg());
            }
            uacLoginReqDTO.setSecretToken(secretToken);
            uacLoginReqDTO.setTokenKey(getPrivateTokenKey());
            uacLoginResDTO = uacLoginService.doLogin(uacLoginReqDTO);
            if (CommUsualUtils.isOEmptyOrNull(uacLoginResDTO)) {
                throw new BusinessException(UacExceptionEnums.UAC_LOGIN_ERROR_10003.code(), UacExceptionEnums.UAC_LOGIN_ERROR_10003.errorMsg());
            }
        } catch (BusinessException e) {
            log.error("用户账户中心登录接口, 出现异常={}", e.getMessage(), e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception ex) {
            log.error("用户账户中心登录接口, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, uacLoginResDTO);
    }


    /**
     * <p>Title: getPrivateTokenKey. </p>
     * <p>获取私有KEY </p>
     * @author dragon
     * @date 2018/4/4 上午11:40
     */
    private String getPrivateTokenKey() throws Exception {
        String decodeKey = redisOperationUtils.getKey(UacTokenConstants.TOKEN_KEY);
        if (CommUsualUtils.isNull(decodeKey)) {
            decodeKey = CommUsualUtils.getUUID();
            setTokenKey(decodeKey);
        }
        return decodeKey;
    }

    /**
     * <p>Title: setTokenKey. </p>
     * <p>设置TOKEN_KEY </p>
     * @param tokenKey
     * @author dragon
     * @date 2018/4/4 上午11:41
     */
    private void setTokenKey(String tokenKey) throws Exception {
        if (CommUsualUtils.isNull(tokenKey)) {
            log.error("TokenKey为空");
            throw new BusinessException("TokenKey为空");
        }
        redisOperationUtils.set(UacTokenConstants.TOKEN_KEY, PasswordUtils.encodeByAES(tokenKey));
    }

    /**
     * <p>Title: logout. </p>
     * <p>用户登出接口 </p>
     * @param request
     * @author dragon
     * @date 2018/7/11 下午5:37
     * @return boolean
     */
    @BusinessLog(logInfo = "用户登出接口")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(notes = "用户登出接口", httpMethod = "POST", value = "登出接口")
    public Wrapper<Boolean> logout(HttpServletRequest request) throws Exception {
        try {
            AuthUserDTO authUserDTO = getAuthUserByToken();
            String userId = authUserDTO.getUserName();
            log.warn("登出成功！用户ID=[{}]", userId);
            // TODO 登出记录日志?目前互联网环境是否需要记录
        } catch (Exception ex) {
            log.error("用户登录, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, true);
    }
}
