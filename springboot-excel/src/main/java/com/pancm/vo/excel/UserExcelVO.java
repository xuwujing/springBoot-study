package com.pancm.vo.excel;

import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import com.pancm.vo.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
* @Title: 用户表(User)请求响应对象
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:05
*/
@ApiModel(value = "User", description = "用户表")
@Data
public class UserExcelVO implements Serializable  {
    private static final long serialVersionUID = 887744157417771761L;

    /**
    * 姓名
    */
    @ExcelProperty("姓名")
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 性别
    */
    @ExcelProperty("性别")
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
    * 年龄
    */
    @ExcelProperty("年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
