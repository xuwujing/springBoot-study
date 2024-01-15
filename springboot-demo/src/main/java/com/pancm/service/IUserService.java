package com.pancm.service;

import com.pancm.vo.UserVO;
import com.pancm.vo.ApiResult;
import java.util.List;


 /**
* @Title: 用户表(User)表服务接口
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:03
*/
public interface IUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserVO queryById(Long id);

   
     /**
     * 通过实体作为筛选条件查询
     *
     * @param userVO 实例对象
     * @return 对象列表
     */
    ApiResult list(UserVO userVO);

    
    /**
     * 新增数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    int insert(UserVO userVO);

    /**
     * 修改数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    int update(UserVO userVO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
