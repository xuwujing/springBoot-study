package com.pancm.excel;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;

import com.pancm.dao.UserDao;
import com.pancm.model.User;
import com.pancm.vo.excel.UserExcelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @author pancm
 * @Title: Application
 * @Description: 实现 ReadListener 接口，设置读取数据的操作
 * @Version:1.0.0
 * @Since:jdk1.8
 * @Date 2025/01/23
 **/
@Slf4j
@Component
public class UserDataListener implements ReadListener<UserExcelVO> {

    @Resource
    private UserDao userDao;

    @Resource(name = "pcmTaskExecutor")
    private ThreadPoolTaskExecutor pcmTaskExecutor;

    private static final int BATCH_COUNT = 1000;

    /**
     * 缓存的数据
     */
    private List<UserExcelVO> cachedDataList = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger count = new AtomicInteger(0); // 仅用于统计总记录数

    /**
     * 记录开始时间
     */
    private long startTime;

    /**
     * @param userExcelVO
     * @param analysisContext
     */
    @Override
    public void invoke(UserExcelVO userExcelVO, AnalysisContext analysisContext) {
        if (count.get() == 0) {
            // 记录开始时间
            startTime = System.currentTimeMillis();
        }

        cachedDataList.add(userExcelVO);
        if (cachedDataList.size() >= BATCH_COUNT) {
            flushData();
        }
    }

    private synchronized void flushData() {
        if (!cachedDataList.isEmpty()) {
            List<UserExcelVO> dataToFlush = new ArrayList<>(cachedDataList);
            cachedDataList.clear();
            pcmTaskExecutor.submit(() -> {
                try {
                    saveData(dataToFlush);
                } catch (Exception e) {
                    log.error("数据保存出现异常：", e);
                }
            });
        }
    }

    private void saveData(List<UserExcelVO> dataList) {
        log.info("{}条数据，开始存储数据库！", dataList.size());
        List<User> entities = dataList.stream()
                .map(userVO -> {
                    User user = new User();
                    BeanUtils.copyProperties(userVO, user);
                    user.setSex(Objects.equals(userVO.getSex(), "男") ? 1 : 2);
                    return user;
                })
                .collect(Collectors.toList());
        userDao.insertBatch(entities);
        count.addAndGet(dataList.size());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 处理剩余未提交的数据
        flushData();
        long endTime = System.currentTimeMillis();
        log.info("所有数据解析完成！共解析:{}条数据! 耗时:{}毫秒", count.get(), (endTime - startTime));
        count.set(0);
    }
}