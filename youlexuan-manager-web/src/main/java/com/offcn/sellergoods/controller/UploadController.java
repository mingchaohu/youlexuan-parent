package com.offcn.sellergoods.controller;

import com.offcn.entity.Result;
import com.offcn.util.FastDFSClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*
文件上传控制层
 */
@RestController
public class UploadController {

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            String path = fastDFSClient.uploadFile(file.getBytes(), suffix);
            String url = "http://192.168.188.146/" + path;
            return new Result(true, url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "上传失败");
        }
    }


}
