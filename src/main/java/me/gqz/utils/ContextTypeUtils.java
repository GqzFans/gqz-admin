package me.gqz.utils;

import lombok.extern.slf4j.Slf4j;
import me.gqz.constant.ContextTypeConstants;
import me.gqz.core.exception.BusinessException;
import me.gqz.core.utils.CommUsualUtils;

/**
 * <p>Title: ContextTypeUtils. </p>
 * <p>Description 文件头工具类 </p>
 * @author dragon
 * @date 2018/7/13 下午2:59
 */
@Slf4j
public class ContextTypeUtils {

    public static String contentType(String fileType) {
        if (CommUsualUtils.isNull(fileType)) {
            log.error("判断OSS服务文件上传时文件 fileType is null");
            throw new BusinessException("传入文件类型为空");
        }
        fileType = fileType.toUpperCase();
        String contentType = null;
        if (fileType.equals("BMP")) {
            contentType = ContextTypeConstants.IMAGE_BMP;
        }
        if (fileType.equals("GIF")) {
            contentType = ContextTypeConstants.IMAGE_GIF;
        }
        if (fileType.equals("JPEG") || fileType.equals("JPG") || fileType.equals("PNG")) {
            contentType = ContextTypeConstants.IMAGE_JPEG;
        }
        if (fileType.equals("HTML")) {
            contentType = ContextTypeConstants.TEXT_HTML;
        }
        if (fileType.equals("TXT")) {
            contentType = ContextTypeConstants.TEXT_PLAIN;
        }
        if (fileType.equals("VSD")) {
            contentType = ContextTypeConstants.APPLICATION_VSD;
        }
        if (fileType.equals("PPTX") || fileType.equals("PPT")) {
            contentType = ContextTypeConstants.APPLICATION_PPT;
        }
        if (fileType.equals("DOCX") || fileType.equals("DOC")) {
            contentType = ContextTypeConstants.APPLICATION_DOC;
        }
        if (fileType.equals("XML")) {
            contentType = ContextTypeConstants.TEXT_XML;
        }
        if (fileType.equals("MP4")) {
            contentType = ContextTypeConstants.VIDEO_MP4;
        }
        return contentType;
    }
}
