package com.pancm.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import java.util.List;

import com.pancm.vo.BasePage;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @Title: 系统用户表(SysUserInfo)请求响应对象
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@ApiModel(value = "SysUserInfo", description = "系统用户表")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data(staticConstructor = "of")
public class  SysUserInfoVO extends BasePage implements Serializable  {
    private static final long serialVersionUID = 606535805692267915L;
    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
    * 昵称
    */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
    * 启用状态 0 否 1是
    */
    @ApiModelProperty(value = "启用状态 0 否 1是")
    private Integer userEnable;
    /**
    * 角色ID 1 管理员 2 普通用户
    */
    @ApiModelProperty(value = "角色ID 1 管理员 2 普通用户")
    private Integer roleId;
    /**
    * 工号
    */
    @ApiModelProperty(value = "工号")
    private String jobNo;
    /**
    * 员工联系方式
    */
    @ApiModelProperty(value = "员工联系方式")
    private String mobile;
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

    @ApiModelProperty(value = "权限列表")
    private List<SysPermissionVO> permissionDTOLists;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
