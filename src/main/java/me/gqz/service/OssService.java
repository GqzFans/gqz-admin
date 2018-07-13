package me.gqz.service;

import me.gqz.core.wrap.Wrapper;
import me.gqz.model.dto.res.AttachmentReqDTO;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Title: OssService. </p>
 * <p>Description AliYun-OSS-服务 </p>
 * <p>Company: http://www.hnxianyi.com </p>
 * @author 袁宝龙
 * @date 2018/3/10 下午2:37
 */
public interface OssService {

    /**
     * <p>Title: uploadImage. </p>
     * <p>上传附件图片 </p>
     * @param  inputStream
     * @param  fileName
     * @throws IOException
     * @author dragon
     * @date 2018/7/13 下午2:42
     * @return Wrapper<AttachmentReqDTO>
     */
    Wrapper<AttachmentReqDTO> uploadImage(InputStream inputStream, String fileName) throws Exception;
}
