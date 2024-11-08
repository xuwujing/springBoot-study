package com.pancm.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

 /**
* @Title: 用户表(User)实体类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:05
*/
@Data
@Table(name = "user")
public class User  implements Serializable  {
    private static final long serialVersionUID = -59671741667391987L;
    /**
    * ID
    */
    @Column(name = "id")
    private Long id;
    /**
    * 姓名
    */
    @Column(name = "name")
    private String name;
    /**
    * 性别
    */
    @Column(name = "sex")
    private Integer sex;
    /**
    * 年龄
    */
    @Column(name = "age")
    private Integer age;
    /**
    * 创建时间
    */
    @Column(name = "create_time")
    private Date createTime;
    /**
    * 创建人
    */
    @Column(name = "create_by")
    private String createBy;
    /**
    * 更新时间
    */
    @Column(name = "update_time")
    private Date updateTime;
    /**
    * 更新人
    */
    @Column(name = "update_by")
    private String updateBy;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
