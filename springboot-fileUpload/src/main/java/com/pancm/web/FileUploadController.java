package com.pancm.web;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
* @Title: FileUpload
* @Description:
* 文件上传 
* 访问 http://localhost:8182/upload 即可实现文件上传
* @Version:1.0.0  
* @author pancm
* @date 2018年5月11日
 */
@Controller
public class FileUploadController {
	
	@Value("${filePath}")
	private String filePath;
	
    @GetMapping("/upload")
    public String uploading() {
        //跳转到 templates 目录下的 uploading.html
        return "uploading";
    }

    //处理文件上传
    @PostMapping("/uploading")
    public @ResponseBody String uploading(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        try {
            uploadFile(file.getBytes(), filePath, file.getOriginalFilename());
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("文件上传失败!");
        	return "uploading failure";
        }
        System.out.println("文件上传成功!");
        return "uploading success";
    }
    
    
    
    public void  uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    
}
