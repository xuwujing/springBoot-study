package com.pancm.service;

import com.pancm.vo.SysPermissionVO;
import com.pancm.vo.ApiResult;
import java.util.List;


 /**
* @Title: 权限表(SysPermission)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
public interface ISysPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermissionVO queryById(Integer id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermissionVO 实例对象
     * @return 对象列表
     */
    ApiResult list(SysPermissionVO sysPermissionVO);

    
    /**
     * 新增数据
     *
     * @param sysPermissionVO 实例对象
     * @return 实例对象
     */
    int insert(SysPermissionVO sysPermissionVO);

    /**
     * 修改数据
     *
     * @param sysPermissionVO 实例对象
     * @return 实例对象
     */
    int update(SysPermissionVO sysPermissionVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

     List<SysPermissionVO> queryRolePermissionByRoleId(Integer roleId);
 }
