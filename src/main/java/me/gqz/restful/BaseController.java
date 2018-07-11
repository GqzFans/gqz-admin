package me.gqz.restful;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.UacTokenConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.model.dto.AuthUserDTO;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.utils.ThreadLocalMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.List;

/**
 * <p>Title: BaseController. </p>
 * @author dragon
 * @date 2018/3/29 下午10:42
 */
@Slf4j
public class BaseController {

    /**
     * <p>Title: handleBindingResult. </p>
     * <p>Hibernate Validator校验结果处理 </p>
     * @param bindingResult 校验
     * @author dragon
     * @date 2018/3/29 下午10:42
     */
    protected void handleBindingResult(BindingResult bindingResult) throws Exception {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors.isEmpty()) {
            return;
        }
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        throw new BusinessException(defaultMessage);
    }

    /**
     * <p>Title: getAuthUserByToken. </p>
     * <p>通过token获取用户信息 </p>
     * @author dragon
     * @date 2018/7/11 下午3:43
     * @return authUserDTO
     */
    protected AuthUserDTO getAuthUserByToken() {
        AuthUserDTO authUserDTO = (AuthUserDTO) ThreadLocalMap.get(UacTokenConstants.AUTH_USER_DTO);
        if(CommUsualUtils.isOEmptyOrNull(authUserDTO)){
            throw new BusinessException("验证token失败");
        }
        return authUserDTO;
    }

}
