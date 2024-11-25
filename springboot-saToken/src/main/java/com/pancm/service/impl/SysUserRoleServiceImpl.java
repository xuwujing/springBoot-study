package com.pancm.service.impl;


import com.pancm.vo.SysUserRoleVO;
import com.pancm.model.SysUserRole;
import com.pancm.dao.SysUserRoleDao;
import com.pancm.service.ISysUserRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;



/**
* @Title: 用户角色表(SysUserRole)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements ISysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRoleVO queryById(Integer id) {
        return this.sysUserRoleDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(SysUserRoleVO sysUserRole) {
       int pageNum = sysUserRole.getPageNum();
       int pageSize = sysUserRole.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<SysUserRoleVO> result =  sysUserRoleDao.queryAll(sysUserRole);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param sysUserRoleVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUserRoleVO sysUserRoleVO) {
        SysUserRole  sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
        return sysUserRoleDao.insert(sysUserRole);
    }

    /**
     * 修改数据
     *
     * @param sysUserRoleVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysUserRoleVO sysUserRoleVO) {
        SysUserRole  sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
        return sysUserRoleDao.update(sysUserRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserRoleDao.deleteById(id) > 0;
    }
}
