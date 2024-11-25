package com.pancm.service;

import com.pancm.vo.SysUserRoleVO;
import com.pancm.vo.ApiResult;
import java.util.List;


 /**
* @Title: 用户角色表(SysUserRole)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
public interface ISysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRoleVO queryById(Integer id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRoleVO 实例对象
     * @return 对象列表
     */
    ApiResult list(SysUserRoleVO sysUserRoleVO);

    
    /**
     * 新增数据
     *
     * @param sysUserRoleVO 实例对象
     * @return 实例对象
     */
    int insert(SysUserRoleVO sysUserRoleVO);

    /**
     * 修改数据
     *
     * @param sysUserRoleVO 实例对象
     * @return 实例对象
     */
    int update(SysUserRoleVO sysUserRoleVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
