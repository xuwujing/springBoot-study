package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 用户角色表(SysUserRole)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Data
@Table(name = "sys_user_role")
public class SysUserRole  implements Serializable  {
    private static final long serialVersionUID = 497044291679355487L;
    /**
    * 主键ID
    */
    @Column(name = "id")
    private Integer id;
    /**
    * 角色名
    */
    @Column(name = "role_name")
    private String roleName;
    /**
    * 启用状态 0 否 1是
    */
    @Column(name = "user_enable")
    private Integer userEnable;
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
