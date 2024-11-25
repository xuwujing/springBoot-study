package com.pancm.service;

import com.pancm.vo.SysUserInfoVO;
import com.pancm.vo.ApiResult;
import java.util.List;


 /**
* @Title: 系统用户表(SysUserInfo)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
public interface ISysUserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserInfoVO queryById(Integer id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserInfoVO 实例对象
     * @return 对象列表
     */
    ApiResult list(SysUserInfoVO sysUserInfoVO);

    
    /**
     * 新增数据
     *
     * @param sysUserInfoVO 实例对象
     * @return 实例对象
     */
    int insert(SysUserInfoVO sysUserInfoVO);

    /**
     * 修改数据
     *
     * @param sysUserInfoVO 实例对象
     * @return 实例对象
     */
    int update(SysUserInfoVO sysUserInfoVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

     SysUserInfoVO queryByUserName(String username);
 }
