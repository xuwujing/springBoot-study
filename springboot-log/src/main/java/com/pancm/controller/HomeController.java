package com.pancm.controller;

import com.alibaba.fastjson.JSONObject;
import com.pancm.util.FileHelper;
import com.pancm.util.GetProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author pancm
 * @Description
 * @Date  2019/2/17
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/home")
@Slf4j
@Api(tags = "首页相关接口")
public class HomeController  {


    @Value("${spring.profiles.active}")
    String active;

    private final static String  VERSION_NAME = "version.txt";

    @ApiOperation(value = "/version", notes = "版本信息")
    @RequestMapping(value = "/version", method = {RequestMethod.GET})
    public JSONObject version(HttpServletRequest request){
        Map<String, String> map = GetProperties.getAppSettings();
        JSONObject result = new JSONObject();
        result.put("git_branch", map.get("git_branch"));
        result.put("build_time", map.get("build_time"));
        result.put("git_commit", map.get("git_commit"));
        result.put("app_name", map.get("build_app"));
        result.put("profile", active);
        result.put("version", FileHelper.readResourcesFile(VERSION_NAME));
        return result;
    }




}
