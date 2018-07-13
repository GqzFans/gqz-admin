package me.gqz.restful;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.constant.UacTokenConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.enums.UacExceptionEnums;
import me.gqz.service.UacTokenService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: UacTokenCtl. </p>
 * <p>Description 用户账户中心Token </p>
 * @author dragon
 * @date 2018/3/30 下午6:49
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/uac/auth/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UacTokenCtl", tags = "用户账户中心TOKEN接口", description = "用户账户中心TOKEN接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class UacTokenCtl extends BaseController {

    @Resource
    private StringRedisTemplate rt;
    @Resource
    private UacTokenService uacTokenService;

    /**
     * <p>Title: setKey. </p>
     * <p>Description 存储redis键值对</p>
     * @param key 秘钥
     * @param value 值
     * @author dragon
     * @date 2018年03月30日18:52:11
     */
    private void setKey(String key, String value) throws IOException {
        ValueOperations<String,String> ops = rt.opsForValue();
        ops.set(key, value);
        rt.expire(key,1L, TimeUnit.DAYS);
    }

    /**
     * <p>Title: getKey. </p>
     * <p>Description 根据key获取redis值</p>
     * @param key 秘钥
     * @author dragon
     * @date 2018年03月30日18:52:11
     * @return java.lang.String
     */
    private String getKey(String key) throws IOException {
        ValueOperations<String,String> ops = rt.opsForValue();
        if(rt.hasKey(key)){
            return ops.get(key);
        }
        return null;
    }

    @BusinessLog(logInfo = "加密TOKEN")
    @ResponseBody
    @RequestMapping(value = "/getSecretToken")
    @ApiOperation(notes = "加密TOKEN", httpMethod = "POST", value = "获取加密TOKEN接口")
    public Wrapper<String> getSecretToken() {
        Wrapper<String> result;
        String secretToken;
        try {
            secretToken = this.getKey(UacTokenConstants.SECRET_TOKEN);
            if (!CommUsualUtils.isSEmptyOrNull(secretToken)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, secretToken);
            }
            // 获取加密TOKEN
            result = uacTokenService.getSecretToken();
            if (CommUsualUtils.isNull(result)) {
                throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10001.getMsg());
            }
            if (Wrapper.SUCCESS_CODE != result.getCode()) {
                throw new BusinessException("获取加密TOKEN失败");
            }
            this.setKey(UacTokenConstants.SECRET_TOKEN, result.getResult());
        } catch (BusinessException ex) {
            log.error("获取加密TOKEN接口, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("获取加密TOKEN接口, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.error();
        }
        return result;
    }

    /**
     * <p>Title: getTokenAgain. </p>
     * <p>重新生成TOKEN </p>
     * @author dragon
     * @date 2018/7/11 下午3:37
     * @return token
     */
    @BusinessLog(logInfo = "重新生成TOKEN")
    @ResponseBody
    @RequestMapping(value = "/getTokenAgain", method = RequestMethod.POST)
    @ApiOperation(notes = "重新生成TOKEN", httpMethod = "POST", value = "重新生成TOKEN返回")
    public Wrapper<String> getTokenAgain() {
        String token = null;
        try {
            AuthUserDTO authUserDTO = getAuthUserByToken();
            token = uacTokenService.encodeToken(authUserDTO);
            log.info("重新生成TOKEN -> token={}", token);
        } catch (BusinessException e) {
            log.error("重新生成TOKEN失败，强制用户退出：{}", e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, token);
    }

}
