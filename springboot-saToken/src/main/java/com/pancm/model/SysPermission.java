package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 权限表(SysPermission)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Data
@Table(name = "sys_permission")
public class SysPermission  implements Serializable  {
    private static final long serialVersionUID = -66914156987259247L;
    /**
    * 主键ID
    */
    @Column(name = "id")
    private Integer id;
    /**
    * 父ID
    */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
    * 权限编码
    */
    @Column(name = "permission_code")
    private String permissionCode;
    /**
    * 权限名称
    */
    @Column(name = "permission_name")
    private String permissionName;
    /**
    * 权限路由
    */
    @Column(name = "permission_route")
    private String permissionRoute;
    /**
    * 创建时间
    */
    @Column(name = "create_time")
    private Date createTime;
    /**
    * 创建人
    */
    @Column(name = "creator_user")
    private String creatorUser;
    /**
    * 更新时间
    */
    @Column(name = "modify_time")
    private Date modifyTime;
    /**
    * 更新人
    */
    @Column(name = "modifier_user")
    private String modifierUser;
    /**
    * 删除状态
    */
    @Column(name = "deleted")
    private Integer deleted;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
