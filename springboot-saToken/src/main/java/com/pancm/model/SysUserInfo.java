package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 系统用户表(SysUserInfo)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Data
@Table(name = "sys_user_info")
public class SysUserInfo  implements Serializable  {
    private static final long serialVersionUID = 542441098648270310L;
    /**
    * 主键ID
    */
    @Column(name = "id")
    private Integer id;
    /**
    * 用户名
    */
    @Column(name = "user_name")
    private String userName;
    /**
    * 昵称
    */
    @Column(name = "nick_name")
    private String nickName;
    /**
    * 密码
    */
    @Column(name = "password")
    private String password;
    /**
    * 启用状态 0 否 1是
    */
    @Column(name = "user_enable")
    private Integer userEnable;
    /**
    * 角色ID 1 管理员 2 普通用户
    */
    @Column(name = "role_id")
    private Integer roleId;
    /**
    * 工号
    */
    @Column(name = "job_no")
    private String jobNo;
    /**
    * 员工联系方式
    */
    @Column(name = "mobile")
    private String mobile;
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
