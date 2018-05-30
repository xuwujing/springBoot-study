package com.pancm.web;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

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
* @Version:1.0.0  
* @author pancm
* @date 2018年5月11日
 */
@Controller
public class FileUploadController {
	
	
    @GetMapping("/gouploadimg")
    public String goUploadImg() {
    	System.out.println("------------");
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @PostMapping("/testuploadimg")
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String filePath = "e:\\test\\";
        System.out.println("path:"+filePath);
        try {
            uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
        //返回json
        return "uploadimg success";
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
