package com.pancm.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import com.pancm.vo.BasePage;

/**
* @Title: 用户角色表(SysUserRole)请求响应对象
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@ApiModel(value = "SysUserRole", description = "用户角色表")
@Data
public class  SysUserRoleVO extends BasePage implements Serializable  {
    private static final long serialVersionUID = 226114050325384071L;
    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    /**
    * 角色名
    */
    @ApiModelProperty(value = "角色名")
    private String roleName;
    /**
    * 启用状态 0 否 1是
    */
    @ApiModelProperty(value = "启用状态 0 否 1是")
    private Integer userEnable;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String creatorUser;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date modifyTime;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String modifierUser;
    /**
    * 删除状态
    */
    @ApiModelProperty(value = "删除状态")
    private Integer deleted;



    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
