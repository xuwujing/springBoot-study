package com.pancm.dao;

import com.pancm.model.TSysUser;
import com.pancm.vo.TSysUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

 /**
* @Title: 用户表(TSysUser)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:54
*/
@Mapper
public interface TSysUserDao extends BaseMapper<TSysUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSysUserVO queryById(String id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param tSysUserVO 实例对象
     * @return 对象列表
     */
    TSysUserVO findOne(TSysUserVO tSysUserVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tSysUserVO 实例对象
     * @return 对象列表
     */
    List<TSysUserVO> queryAll(TSysUserVO tSysUserVO);

    /**
     * 新增数据
     *
     * @param tSysUser 实例对象
     * @return 影响行数
     */
    int insert(TSysUser tSysUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TSysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TSysUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TSysUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TSysUser> entities);

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 影响行数
     */
    int update(TSysUser tSysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

