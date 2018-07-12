package me.gqz.restful;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.gqz.core.base.Page;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.model.dto.req.DropUserReqDTO;
import me.gqz.model.vo.UserListVO;
import me.gqz.service.UserManagementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: UserManagementCtl. </p>
 * <p>Description 系统管理-用户管理模块接口 </p>
 * @author dragon
 * @date 2018/7/12 下午2:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gqz/system/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UserManagementCtl", tags = "系统管理-用户管理模块接口", description = "系统管理-用户管理模块接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserManagementCtl extends BaseController {

    @Resource
    private UserManagementService userManagementService;

    /**
     * <p>Title: queryUserList. </p>
     * <p>用户管理分页列表查询 </p>
     * @param page
     * @author dragon
     * @date 2018/7/12 下午3:02
     * @return Wrapper<PageInfo<UserListVO>>
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserList", method = {RequestMethod.POST})
    @ApiOperation(value = "用户管理分页列表查询", httpMethod = "POST", notes = "返回用户管理分页列表")
    public Wrapper<PageInfo<UserListVO>> queryUserList(@ApiParam(name = "page", value = "用户管理分页参数") @RequestBody Page<Object> page) {
        try {
            log.info("用户管理分页列表查询 ==> {}", page);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<UserListVO> list = userManagementService.queryUserList();
            PageInfo<UserListVO> pageInfo = new PageInfo<>(list);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            log.error("用户管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("用户管理分页列表查询出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * <p>Title: dropUserById. </p>
     * <p>停用用户 </p>
     * @param
     * @author dragon
     * @date 2018/7/12 下午4:22
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dropUserById", method = {RequestMethod.POST})
    @ApiOperation(value = "停用用户", httpMethod = "POST", notes = "返回停用用户结果")
    public Wrapper<?> dropUserById(@ApiParam(name = "id", value = "停用用户参数") @RequestBody DropUserReqDTO dropUserReqDTO) {
        try {
            if (CommUsualUtils.isSEmptyOrNull(dropUserReqDTO.getId()) || CommUsualUtils.isSEmptyOrNull(dropUserReqDTO.getVersion())) {
                throw new BusinessException("参数不能为空");
            }
            log.warn("停用用户：ID = {}", dropUserReqDTO.getId());
            Boolean result = userManagementService.dropUserById(dropUserReqDTO);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            log.error("停用用户出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            log.error("停用用户出错：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }
}
