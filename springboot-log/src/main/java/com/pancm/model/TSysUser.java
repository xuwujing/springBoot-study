package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 用户表(TSysUser)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:55
*/
@Data
@Table(name = "t_sys_user")
public class TSysUser  implements Serializable  {
    private static final long serialVersionUID = -56958960669065369L;
    /**
    * 主键
    */
    @Column(name = "id")
    private String id;
    /**
    * 用户账号
    */
    @Column(name = "username")
    private String username;
    /**
    * 用户密码
    */
    @Column(name = "password")
    private String password;
    /**
    * 昵称
    */
    @Column(name = "nickname")
    private String nickname;
    /**
    * 部门id
    */
    @Column(name = "dep_id")
    private Integer depId;
    /**
    * 岗位id
    */
    @Column(name = "pos_id")
    private String posId;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
