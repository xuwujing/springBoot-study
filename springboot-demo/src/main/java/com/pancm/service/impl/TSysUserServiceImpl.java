package com.pancm.service.impl;


import com.pancm.vo.TSysUserVO;
import com.pancm.model.TSysUser;
import com.pancm.dao.TSysUserDao;
import com.pancm.service.ITSysUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



/**
* @Title: 用户表(TSysUser)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:54
*/
@Service("tSysUserService")
public class TSysUserServiceImpl extends ServiceImpl<TSysUserDao, TSysUser>  implements ITSysUserService {
    @Resource
    private TSysUserDao tSysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TSysUserVO queryById(String id) {
        return this.tSysUserDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(TSysUserVO tSysUser) {
       int pageNum = tSysUser.getPageNum();
       int pageSize = tSysUser.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<TSysUserVO> result =  tSysUserDao.queryAll(tSysUser);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param tSysUserVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysUserVO tSysUserVO) {
        TSysUser  tSysUser = new TSysUser();
        BeanUtils.copyProperties(tSysUserVO,tSysUser);
        return tSysUserDao.insert(tSysUser);
    }

    /**
     * 修改数据
     *
     * @param tSysUserVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(TSysUserVO tSysUserVO) {
        TSysUser  tSysUser = new TSysUser();
        BeanUtils.copyProperties(tSysUserVO,tSysUser);
        return tSysUserDao.update(tSysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tSysUserDao.deleteById(id) > 0;
    }
}
