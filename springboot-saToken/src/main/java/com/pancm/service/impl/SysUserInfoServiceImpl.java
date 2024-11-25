package com.pancm.service.impl;


import com.pancm.enums.PcmYesNoEnum;
import com.pancm.vo.SysUserInfoVO;
import com.pancm.model.SysUserInfo;
import com.pancm.dao.SysUserInfoDao;
import com.pancm.service.ISysUserInfoService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;



/**
* @Title: 系统用户表(SysUserInfo)表服务实现类
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements ISysUserInfoService {
    @Resource
    private SysUserInfoDao sysUserInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserInfoVO queryById(Integer id) {
        return this.sysUserInfoDao.queryById(id);
    }

    
      /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public ApiResult list(SysUserInfoVO sysUserInfo) {
       int pageNum = sysUserInfo.getPageNum();
       int pageSize = sysUserInfo.getPageSize();
       Page page = PageHelper.startPage(pageNum, pageSize);
       List<SysUserInfoVO> result =  sysUserInfoDao.queryAll(sysUserInfo);
       return ApiResult.success(new PageResult<>(page.getTotal(), result, pageSize, pageNum));
         
    }
    
    /**
     * 新增数据
     *
     * @param sysUserInfoVO 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUserInfoVO sysUserInfoVO) {
        SysUserInfo  sysUserInfo = new SysUserInfo();
        BeanUtils.copyProperties(sysUserInfoVO,sysUserInfo);
        return sysUserInfoDao.insert(sysUserInfo);
    }

    /**
     * 修改数据
     *
     * @param sysUserInfoVO 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysUserInfoVO sysUserInfoVO) {
        SysUserInfo  sysUserInfo = new SysUserInfo();
        BeanUtils.copyProperties(sysUserInfoVO,sysUserInfo);
        return sysUserInfoDao.update(sysUserInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserInfoDao.deleteById(id) > 0;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public SysUserInfoVO queryByUserName(String username) {
        return  sysUserInfoDao.findOne(SysUserInfoVO.of().setUserName(username).setDeleted(PcmYesNoEnum.NO.getIndex()));
    }
}
