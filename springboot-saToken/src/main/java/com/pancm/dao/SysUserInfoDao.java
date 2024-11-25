package com.pancm.dao;

import com.pancm.model.SysUserInfo;
import com.pancm.vo.SysUserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
* @Title: 系统用户表(SysUserInfo)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Mapper
public interface SysUserInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserInfoVO queryById(Integer id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param sysUserInfoVO 实例对象
     * @return 对象列表
     */
    SysUserInfoVO findOne(SysUserInfoVO sysUserInfoVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserInfoVO 实例对象
     * @return 对象列表
     */
    List<SysUserInfoVO> queryAll(SysUserInfoVO sysUserInfoVO);

    /**
     * 新增数据
     *
     * @param sysUserInfo 实例对象
     * @return 影响行数
     */
    int insert(SysUserInfo sysUserInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserInfo> entities);

    /**
     * 修改数据
     *
     * @param sysUserInfo 实例对象
     * @return 影响行数
     */
    int update(SysUserInfo sysUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

