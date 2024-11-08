package com.pancm.service.impl;


import com.pancm.vo.UserVO;
import com.pancm.model.User;
import com.pancm.dao.UserDao;
import com.pancm.service.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;



/**
* @Title: 用户表(User)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:04
*/
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserVO queryById(Long id) {
        return this.userDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(UserVO user) {
       int pageNum = user.getPageNum();
       int pageSize = user.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<UserVO> result =  userDao.queryAll(user);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(UserVO userVO) {
        User  user = new User();
        BeanUtils.copyProperties(userVO,user);
        return userDao.insert(user);
    }

    /**
     * 修改数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(UserVO userVO) {
        User  user = new User();
        BeanUtils.copyProperties(userVO,user);
        return userDao.update(user);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }
}
