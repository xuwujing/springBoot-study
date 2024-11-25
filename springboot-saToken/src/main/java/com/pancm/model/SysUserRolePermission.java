package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 用户角色权限表(SysUserRolePermission)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Data
@Table(name = "sys_user_role_permission")
public class SysUserRolePermission  implements Serializable  {
    private static final long serialVersionUID = -16015545300523438L;
    /**
    * 主键ID
    */
    @Column(name = "id")
    private Integer id;
    /**
    * 角色ID
    */
    @Column(name = "role_id")
    private Integer roleId;
    /**
    * 权限ID
    */
    @Column(name = "permission_id")
    private Integer permissionId;
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
