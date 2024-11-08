package com.pancm.service;

import com.pancm.vo.TSysUserVO;
import com.pancm.model.TSysUser;
import com.pancm.vo.ApiResult;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;


 /**
* @Title: 用户表(TSysUser)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:53
*/
public interface ITSysUserService extends IService<TSysUser>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSysUserVO queryById(String id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param tSysUserVO 实例对象
     * @return 对象列表
     */
    ApiResult list(TSysUserVO tSysUserVO);

    
    /**
     * 新增数据
     *
     * @param tSysUserVO 实例对象
     * @return 实例对象
     */
    int insert(TSysUserVO tSysUserVO);

    /**
     * 修改数据
     *
     * @param tSysUserVO 实例对象
     * @return 实例对象
     */
    int update(TSysUserVO tSysUserVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
