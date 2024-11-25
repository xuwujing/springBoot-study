package com.pancm.dao;

import com.pancm.model.SysPermission;
import com.pancm.vo.SysPermissionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
* @Title: 权限表(SysPermission)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Mapper
public interface SysPermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermissionVO queryById(Integer id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param sysPermissionVO 实例对象
     * @return 对象列表
     */
    SysPermissionVO findOne(SysPermissionVO sysPermissionVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermissionVO 实例对象
     * @return 对象列表
     */
    List<SysPermissionVO> queryAll(SysPermissionVO sysPermissionVO);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int insert(SysPermission sysPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysPermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysPermission> entities);

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

     List<SysPermissionVO> queryRolePermissionByRoleId(Integer roleId);
 }

