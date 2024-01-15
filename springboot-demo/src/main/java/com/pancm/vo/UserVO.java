package com.pancm.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import com.pancm.vo.BasePage;

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
public class  UserVO extends BasePage implements Serializable  {
    private static final long serialVersionUID = 887744157417771761L;
    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 性别
    */
    @ApiModelProperty(value = "性别")
    private Integer sex;
    /**
    * 年龄
    */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String updateBy;



    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
