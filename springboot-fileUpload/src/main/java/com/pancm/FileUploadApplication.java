package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
* @Title: FileUploadApplication
* @Description: 文件上传
* @Version:1.0.0  
* @author pancm
* @date 2018年5月11日
 */
@SpringBootApplication
public class FileUploadApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FileUploadApplication.class, args);
        System.out.println("FileUploadApplication 程序启动成功!");
    }
}