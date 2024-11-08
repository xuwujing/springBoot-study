package com.pancm.dao;

import com.pancm.model.User;
import com.pancm.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

 /**
* @Title: 用户表(User)表数据库访问层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:04
*/
@Mapper
public interface UserDao  {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserVO queryById(Long id);

   
    /**
     * 通过实体查询一条数据
     *
     * @param userVO 实例对象
     * @return 对象列表
     */
    UserVO findOne(UserVO userVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userVO 实例对象
     * @return 对象列表
     */
    List<UserVO> queryAll(UserVO userVO);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

