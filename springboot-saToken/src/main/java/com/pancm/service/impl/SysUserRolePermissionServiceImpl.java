package com.pancm.service.impl;


import com.pancm.vo.SysUserRolePermissionVO;
import com.pancm.model.SysUserRolePermission;
import com.pancm.dao.SysUserRolePermissionDao;
import com.pancm.service.ISysUserRolePermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;



/**
* @Title: 用户角色权限表(SysUserRolePermission)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Service("sysUserRolePermissionService")
public class SysUserRolePermissionServiceImpl implements ISysUserRolePermissionService {
    @Resource
    private SysUserRolePermissionDao sysUserRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRolePermissionVO queryById(Integer id) {
        return this.sysUserRolePermissionDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(SysUserRolePermissionVO sysUserRolePermission) {
       int pageNum = sysUserRolePermission.getPageNum();
       int pageSize = sysUserRolePermission.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<SysUserRolePermissionVO> result =  sysUserRolePermissionDao.queryAll(sysUserRolePermission);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUserRolePermissionVO sysUserRolePermissionVO) {
        SysUserRolePermission  sysUserRolePermission = new SysUserRolePermission();
        BeanUtils.copyProperties(sysUserRolePermissionVO,sysUserRolePermission);
        return sysUserRolePermissionDao.insert(sysUserRolePermission);
    }

    /**
     * 修改数据
     *
     * @param sysUserRolePermissionVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysUserRolePermissionVO sysUserRolePermissionVO) {
        SysUserRolePermission  sysUserRolePermission = new SysUserRolePermission();
        BeanUtils.copyProperties(sysUserRolePermissionVO,sysUserRolePermission);
        return sysUserRolePermissionDao.update(sysUserRolePermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserRolePermissionDao.deleteById(id) > 0;
    }
}
