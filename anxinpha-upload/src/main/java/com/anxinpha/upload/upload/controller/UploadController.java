package com.anxinpha.upload.upload.controller;

import com.anxinpha.common.pojo.WangEditorResult;
import com.anxinpha.upload.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 尹硕硕
 * @description 文件上传启动类
 **/
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("imageUpload")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        String url = this.uploadService.uploadImage(file);
        if(StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @PostMapping("/wangeditor/imageUpload")
    public ResponseEntity<WangEditorResult> wangEditorUploadImage(@RequestParam("file") MultipartFile[] file){
        WangEditorResult wangEditorResult = this.uploadService.wangEditorUploadImage(file);
        if (wangEditorResult.getErrno()!=0){
            return ResponseEntity.badRequest().body(wangEditorResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(wangEditorResult);
    }
}
