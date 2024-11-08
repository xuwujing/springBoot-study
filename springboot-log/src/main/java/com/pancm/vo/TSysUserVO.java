package com.pancm.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import com.pancm.vo.BasePage;

/**
* @Title: 用户表(TSysUser)请求响应对象
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:54
*/
@ApiModel(value = "TSysUser", description = "用户表")
@Data
public class  TSysUserVO extends BasePage implements Serializable  {
    private static final long serialVersionUID = 855666123415935768L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
    * 用户账号
    */
    @ApiModelProperty(value = "用户账号")
    private String username;
    /**
    * 用户密码
    */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
    * 昵称
    */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
    * 部门id
    */
    @ApiModelProperty(value = "部门id")
    private Integer depId;
    /**
    * 岗位id
    */
    @ApiModelProperty(value = "岗位id")
    private String posId;



    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
