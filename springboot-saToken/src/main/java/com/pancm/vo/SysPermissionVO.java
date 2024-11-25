package com.pancm.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import com.pancm.vo.BasePage;

/**
* @Title: 权限表(SysPermission)请求响应对象
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@ApiModel(value = "SysPermission", description = "权限表")
@Data
public class  SysPermissionVO extends BasePage implements Serializable  {
    private static final long serialVersionUID = 424918937095450507L;
    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    /**
    * 父ID
    */
    @ApiModelProperty(value = "父ID")
    private Integer parentId;
    /**
    * 权限编码
    */
    @ApiModelProperty(value = "权限编码")
    private String permissionCode;
    /**
    * 权限名称
    */
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    /**
    * 权限路由
    */
    @ApiModelProperty(value = "权限路由")
    private String permissionRoute;
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
