package com.anxinpha.upload.upload.service;

import com.anxinpha.common.pojo.WangEditorResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 尹硕硕
 * @description 文件上传服务
 **/
public interface UploadService {

    /**
     * 图片上传服务
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);

    /**
     * kindEditor图片上传
     * @param file
     * @return
     */
    WangEditorResult wangEditorUploadImage(MultipartFile[] file);
}
