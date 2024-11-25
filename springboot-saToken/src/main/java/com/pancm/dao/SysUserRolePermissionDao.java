package com.pancm.dao;

import com.pancm.model.SysUserRolePermission;
import com.pancm.vo.SysUserRolePermissionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
* @Title: 用户角色权限表(SysUserRolePermission)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Mapper
public interface SysUserRolePermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRolePermissionVO queryById(Integer id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 对象列表
     */
    SysUserRolePermissionVO findOne(SysUserRolePermissionVO sysUserRolePermissionVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 对象列表
     */
    List<SysUserRolePermissionVO> queryAll(SysUserRolePermissionVO sysUserRolePermissionVO);

    /**
     * 新增数据
     *
     * @param sysUserRolePermission 实例对象
     * @return 影响行数
     */
    int insert(SysUserRolePermission sysUserRolePermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRolePermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserRolePermission> entities);

    /**
     * 修改数据
     *
     * @param sysUserRolePermission 实例对象
     * @return 影响行数
     */
    int update(SysUserRolePermission sysUserRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

