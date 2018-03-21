package com.pancm.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancm.springboot.dao.cluster.CityDao;
import com.pancm.springboot.dao.master.UserDao;
import com.pancm.springboot.domain.City;
import com.pancm.springboot.domain.User;
import com.pancm.springboot.service.UserService;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("深圳");
        user.setCity(city);
        return user;
    }
}
