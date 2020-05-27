package com.anxinpha.upload.upload.service.impl;

import com.anxinpha.common.pojo.WangEditorResult;
import com.anxinpha.upload.upload.service.UploadService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class UploadServiceImpl implements UploadService {

    private static final List<String> TYPES = Arrays.asList("jpg","gif","png");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 图片上传服务
     * @param file
     * @return
     */
    @Override
    public String uploadImage(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        String type = StringUtils.substringAfterLast(originalFilename, ".");
        if(!TYPES.contains(type)){
            LOGGER.info("文件类型不合法：{}",originalFilename);
            return null;
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法：{}",originalFilename);
                return null;
            }

            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), type, null);
            return "http://image.anxinpha.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * kindEditor图片上传
     *
     * @param files
     * @return
     */
    @Override
    public WangEditorResult wangEditorUploadImage(MultipartFile[] files) {

        WangEditorResult wangEditorResult = new WangEditorResult();
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {

            String originalFilename = file.getOriginalFilename();

            String type = StringUtils.substringAfterLast(originalFilename, ".");
            if (!TYPES.contains(type)) {
                wangEditorResult.setErrno(1);
                LOGGER.info("文件类型不合法：{}", originalFilename);
                return wangEditorResult;
            }
            try {
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                if (bufferedImage == null) {
                    wangEditorResult.setErrno(2);
                    LOGGER.info("文件内容不合法：{}", originalFilename);
                    return wangEditorResult;
                }

                StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), type, null);
                data.add("http://image.anxinpha.com/" + storePath.getFullPath());
            } catch (IOException e) {
                wangEditorResult.setErrno(1);
                LOGGER.info("服务器内部错误：{}", originalFilename);
                e.printStackTrace();
            }
        }
        wangEditorResult.setErrno(0);
        wangEditorResult.setData(data);
        return wangEditorResult;
    }
}
