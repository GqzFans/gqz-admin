package me.gqz.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import me.gqz.config.OssConfig;
import me.gqz.constant.HttpSchema;
import me.gqz.constant.SystemBaseConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.utils.CommUsualUtils;
import me.gqz.core.wrap.WrapMapper;
import me.gqz.core.wrap.Wrapper;
import me.gqz.enums.OssFileUrlEnum;
import me.gqz.enums.SerialNoEnum;
import me.gqz.model.dto.res.AttachmentResDTO;
import me.gqz.service.OssService;
import me.gqz.utils.ContextTypeUtils;
import me.gqz.utils.RedisUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * <p>Title: OssServiceImpl. </p>
 * <p>Description AliYun-OSS-服务 </p>
 * @author dragon
 * @date 2018/7/13 下午2:50
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Resource
    private OssConfig ossConfig;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 图片业务序列号Redis锁
     */
    private static final String ENV_IMG_LOCK = "GQZ_IMAGE";
    /**
     * 表情包业务序列号Redis锁
     */
    private static final String ENV_EMOTICON_LOCK = "GQZ_EMOTICON";
    /**
     * 图片是否需要授权
     */
    private static final Boolean EXPIRATION = false;

    /**
     * <p>Title: uploadImage. </p>
     * <p>上传附件图片 </p>
     * @param inputStream
     * @param fileName
     * @exception Exception
     * @author dragon
     * @date 2018/7/13 下午2:50
     * @return Wrapper<AttachmentResDTO>
     */
    @Override
    public Wrapper<AttachmentResDTO> uploadImage(InputStream inputStream, String fileName) throws Exception {
        AttachmentResDTO attachment = new AttachmentResDTO();
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            String ossFilePath = OssFileUrlEnum.IMAGE_URL.getUrl();
            log.debug("==> 开始上传文件...");
            log.debug("==> 上传文件 fileName = {}", fileName);
            log.debug("==> 上传文件 ossFilePath = {}", ossFilePath);
            if (CommUsualUtils.isNull(ossFilePath, fileName)) {
                log.error("服务器路路径没有配置");
                throw new BusinessException("服务器路路径没有配置");
            }
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (CommUsualUtils.isSEmptyOrNull(fileType)) {
                throw new BusinessException("上传文件类型错误");
            }
            String contentType = ContextTypeUtils.contentType(fileType);
            if (CommUsualUtils.isSEmptyOrNull(contentType)) {
                throw new BusinessException("上传文件格式错误");
            }
            // 图片业务处理
            ossFilePath = ossFilePath.substring(0, ossFilePath.length()).replaceAll("\\\\", "/") + "/";
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(contentType);
            String serialNo = redisUtils.getNowCode(ENV_IMG_LOCK, SerialNoEnum.GQZ_IMAGE.getType(), SerialNoEnum.GQZ_IMAGE.getLength());
            fileName = serialNo + SystemBaseConstants.UNDER_LINE + fileName;
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String filePath = ossFilePath + fileName;
            // 上传文件
            log.info("上传文件 - 上传文件 - 上传文件 - 图片");
            log.info("ossFilePath = {}", ossFilePath);
            log.info("fileName = {}", fileName);
            ossClient.putObject(ossConfig.getBucketName(), ossFilePath + fileName, inputStream, objectMetadata);
            // 上传真实地址
            String netUrl = "";
            // OSS授权访问
            if (EXPIRATION) {
                Date expiration = DateUtils.addHours(new Date(), ossConfig.getDelayHour());
                GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), filePath, HttpMethod.GET);
                // 设置访问时效 -> 此处有疑问，Bucket公共读是否需要设置时效？
                request.setExpiration(expiration);
                netUrl = ossClient.generatePresignedUrl(request).toString();
            }
            // OSS非授权时，拼接地址
            netUrl = HttpSchema.HTTPS + ossConfig.getBucket() + SystemBaseConstants.FORWARD_SLASH + ossFilePath + fileName;
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentCode(serialNo);
            attachment.setAttachmentUrl(netUrl);
            attachment.setType(fileType);
            log.info("==> 上传文件成功 netUrl={}", netUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("==> 上传文件失败={}", e.getMessage(), e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "上传文件失败");
        } finally {
            ossClient.shutdown();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, attachment);
    }

    /**
     * <p>Title: uploadEmoticon. </p>
     * <p>上传附件表情包 </p>
     * @param  inputStream
     * @param  fileName
     * @throws IOException
     * @author dragon
     * @date 2018/7/16 上午10:25
     * @return Wrapper<AttachmentResDTO>
     */
    @Override
    public Wrapper<AttachmentResDTO> uploadEmoticon(InputStream inputStream, String fileName) throws IOException {
        AttachmentResDTO attachment = new AttachmentResDTO();
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            String ossFilePath = OssFileUrlEnum.EMOTICON_URL.getUrl();
            log.debug("==> 开始上传文件...");
            log.debug("==> 上传文件 fileName = {}", fileName);
            log.debug("==> 上传文件 ossFilePath = {}", ossFilePath);
            if (CommUsualUtils.isNull(ossFilePath, fileName)) {
                log.error("服务器路路径没有配置");
                throw new BusinessException("服务器路路径没有配置");
            }
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (CommUsualUtils.isSEmptyOrNull(fileType)) {
                throw new BusinessException("上传文件类型错误");
            }
            String contentType = ContextTypeUtils.contentType(fileType);
            if (CommUsualUtils.isSEmptyOrNull(contentType)) {
                throw new BusinessException("上传文件格式错误");
            }
            // 图片业务处理
            ossFilePath = ossFilePath.substring(0, ossFilePath.length()).replaceAll("\\\\", "/") + "/";
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(contentType);
            String serialNo = redisUtils.getNowCode(ENV_EMOTICON_LOCK, SerialNoEnum.GQZ_EMOTICON.getType(), SerialNoEnum.GQZ_EMOTICON.getLength());
            fileName = serialNo + SystemBaseConstants.UNDER_LINE + fileName;
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String filePath = ossFilePath + fileName;
            // 上传文件
            log.info("上传文件 - 上传文件 - 上传文件 - 表情包");
            log.info("ossFilePath = {}", ossFilePath);
            log.info("fileName = {}", fileName);
            ossClient.putObject(ossConfig.getBucketName(), ossFilePath + fileName, inputStream, objectMetadata);
            // 上传真实地址
            String netUrl = "";
            // OSS授权访问
            if (EXPIRATION) {
                Date expiration = DateUtils.addHours(new Date(), ossConfig.getDelayHour());
                GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), filePath, HttpMethod.GET);
                // 设置访问时效 -> 此处有疑问，Bucket公共读是否需要设置时效？
                request.setExpiration(expiration);
                netUrl = ossClient.generatePresignedUrl(request).toString();
            }
            // OSS非授权时，拼接地址
            netUrl = HttpSchema.HTTPS + ossConfig.getBucket() + SystemBaseConstants.FORWARD_SLASH + ossFilePath + fileName;
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentCode(serialNo);
            attachment.setAttachmentUrl(netUrl);
            attachment.setType(fileType);
            log.info("==> 上传文件成功 netUrl={}", netUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("==> 上传文件失败={}", e.getMessage(), e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "上传文件失败");
        } finally {
            ossClient.shutdown();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, attachment);
    }
}
