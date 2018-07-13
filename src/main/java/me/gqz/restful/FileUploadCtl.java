package me.gqz.restful;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.gqz.annotation.BusinessLog;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.model.dto.res.AttachmentReqDTO;
import me.gqz.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: FileUploadCtl. </p>
 * <p>Description 文件上传Ctl </p>
 * @author dragon
 * @date 2018/7/13 下午2:27
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/common/file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UacLoginCtl", tags = "用户账户中心登录接口", description = "用户账户中心登录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileUploadCtl extends BaseController {

    @Resource
    private OssService ossService;

    public static final String FILE_NAME = "image";

    /**
     * <p>Title: uploadImg. </p>
     * <p>上传附件接口 </p>
     * @param request
     * @author dragon
     * @date 2018/7/13 下午2:47
     * @return Wrapper<List<AttachmentReqDTO>>
     */
    @BusinessLog(logInfo = "上传附件接口")
    @RequestMapping(value = "/uploadImage")
    @ApiOperation(notes = "返回上传后图片通用信息", httpMethod = "POST", value = "上传附件图片")
    public Wrapper<List<AttachmentReqDTO>> uploadImg(HttpServletRequest request) {
        log.info("上传附件图片开始: 操作用户 => {}", getAuthUserByToken());
        List<AttachmentReqDTO> attachmentReqDTOS = new ArrayList<>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获取图片信息
            List<MultipartFile> imgFiles = multipartRequest.getFiles(FILE_NAME);
            for (MultipartFile file : imgFiles){
                String imgFileName = file.getOriginalFilename();
                String fileName = imgFileName;
                // 获取图片流
                InputStream inputStream = file.getInputStream();
                Wrapper<AttachmentReqDTO> wrapper = ossService.uploadImage(inputStream, fileName);
                if (Wrapper.SUCCESS_CODE == wrapper.getCode() && !CommUsualUtils.isOEmptyOrNull(wrapper.getResult())) {
                    attachmentReqDTOS.add(wrapper.getResult());
                    log.info("上传附件图片成功！附件 = {}", wrapper.getResult().toString());
                } else {
                    log.error("上传附件图片, 上传附件图片失败");
                }
            }
            log.info("上传附件成功！！！！！上传数量 = {}", imgFiles.size());
            log.info("上传附件成功！！！！！上传参数 = {}", attachmentReqDTOS.toString());
        } catch (Exception e) {
            log.error("上传附件图片, 出现异常={}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"上传附件图片出现异常");
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, attachmentReqDTOS);
    }
}
