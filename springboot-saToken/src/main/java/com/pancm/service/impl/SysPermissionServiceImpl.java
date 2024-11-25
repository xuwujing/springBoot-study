package com.pancm.service.impl;


import com.pancm.vo.SysPermissionVO;
import com.pancm.model.SysPermission;
import com.pancm.dao.SysPermissionDao;
import com.pancm.service.ISysPermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;



/**
* @Title: 权限表(SysPermission)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Resource
    private SysPermissionDao sysPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermissionVO queryById(Integer id) {
        return this.sysPermissionDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(SysPermissionVO sysPermission) {
       int pageNum = sysPermission.getPageNum();
       int pageSize = sysPermission.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<SysPermissionVO> result =  sysPermissionDao.queryAll(sysPermission);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param sysPermissionVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysPermissionVO sysPermissionVO) {
        SysPermission  sysPermission = new SysPermission();
        BeanUtils.copyProperties(sysPermissionVO,sysPermission);
        return sysPermissionDao.insert(sysPermission);
    }

    /**
     * 修改数据
     *
     * @param sysPermissionVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysPermissionVO sysPermissionVO) {
        SysPermission  sysPermission = new SysPermission();
        BeanUtils.copyProperties(sysPermissionVO,sysPermission);
        return sysPermissionDao.update(sysPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysPermissionDao.deleteById(id) > 0;
    }

    /**
     * @param roleId
     * @return
     */
    @Override
    public List<SysPermissionVO> queryRolePermissionByRoleId(Integer roleId) {
        return sysPermissionDao.queryRolePermissionByRoleId(roleId);
    }
}
