package com.pancm.dao;

import com.pancm.model.SysUserRole;
import com.pancm.vo.SysUserRoleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
* @Title: 用户角色表(SysUserRole)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Mapper
public interface SysUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRoleVO queryById(Integer id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param sysUserRoleVO 实例对象
     * @return 对象列表
     */
    SysUserRoleVO findOne(SysUserRoleVO sysUserRoleVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRoleVO 实例对象
     * @return 对象列表
     */
    List<SysUserRoleVO> queryAll(SysUserRoleVO sysUserRoleVO);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

