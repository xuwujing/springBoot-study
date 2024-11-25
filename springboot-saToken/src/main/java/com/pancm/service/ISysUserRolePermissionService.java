package com.pancm.service;

import com.pancm.vo.SysUserRolePermissionVO;
import com.pancm.vo.ApiResult;
import java.util.List;


 /**
* @Title: 用户角色权限表(SysUserRolePermission)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
public interface ISysUserRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRolePermissionVO queryById(Integer id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 对象列表
     */
    ApiResult list(SysUserRolePermissionVO sysUserRolePermissionVO);

    
    /**
     * 新增数据
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 实例对象
     */
    int insert(SysUserRolePermissionVO sysUserRolePermissionVO);

    /**
     * 修改数据
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 实例对象
     */
    int update(SysUserRolePermissionVO sysUserRolePermissionVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
