package com.pancm.service.impl;


import cn.idev.excel.EasyExcel;
import cn.idev.excel.ExcelWriter;
import cn.idev.excel.FastExcel;
import cn.idev.excel.support.ExcelTypeEnum;
import cn.idev.excel.util.DateUtils;
import cn.idev.excel.write.metadata.WriteSheet;
import cn.idev.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSONObject;
import com.pancm.excel.UserDataListener;
import com.pancm.vo.UserVO;
import com.pancm.model.User;
import com.pancm.dao.UserDao;
import com.pancm.service.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.vo.ApiResult;
import com.pancm.vo.PageResult;
import com.pancm.vo.excel.UserExcelVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @author pancm
 * @Title: 用户表(User)表服务实现类
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2024-01-15 15:27:04
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    @Resource(name = "pcmTaskExecutor")
    private ThreadPoolTaskExecutor pcmTaskExecutor;

    @Resource
    private UserDataListener userDataListener;


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
        List<UserVO> result = userDao.queryAll(user);
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
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
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
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
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

    /**
     * 导出数据
     * 注:单线程导出,如果数据量过大，会耗时较长
     *
     * @param userVO
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows
    @Override
    public void export(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
        userVO.setPageSize(10000);
        List<UserVO> result = userDao.queryAll(userVO);
        if (result.isEmpty()) {
            return;
        }

        String fileName = "用户表_" + DateUtils.DATE_FORMAT_14 + ".xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        try (OutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = FastExcel.write(outputStream, UserExcelVO.class)
                     .excelType(ExcelTypeEnum.XLSX)
                     .build()) {
            String sheetName = "用户表";
            WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .build();

            List<UserExcelVO> excelData = getExcelData(result);
            excelWriter.write(excelData, writeSheet);
        } catch (Exception e) {
            log.error("导出失败", e);
        }


    }

    private List<UserExcelVO> getExcelData(List<UserVO> userVOList) {
        return userVOList.stream()
                .map(userVO -> {
                    UserExcelVO userExcelVO = new UserExcelVO();
                    BeanUtils.copyProperties(userVO, userExcelVO);
                    userExcelVO.setSex(Objects.equals(userVO.getSex(), 1) ? "男" : "女");
                    return userExcelVO;
                })
                .collect(Collectors.toList());
    }


    /**
     * 导出数据
     * 注:使用多线程，可以提高导出效率,此方案是导出到一个sheet，如果有多个sheet，按需进行处理即可
     *
     * @param userVO
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows
    @Override
    public void exportBatch(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
        userVO.setPageNum(null);
        userVO.setPageSize(null);
        long startTime = System.currentTimeMillis();
        int listCount = userDao.queryAllCount(userVO);
        if (listCount == 0) {
            return;
        }
        String fileName = "用户表大量数据_" + DateUtils.DATE_FORMAT_14 + ".xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        try (OutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = FastExcel.write(outputStream, UserExcelVO.class)
                     .excelType(ExcelTypeEnum.XLSX)
                     .build()) {
            String sheetName = "用户表";
            WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .build();
            int pageSize = getPageSize(listCount);
            int k = ceilDivision(listCount, pageSize);
            CountDownLatch sheetThreadSignal = new CountDownLatch(k);
            AtomicInteger count = new AtomicInteger(0);
            log.info("开始导出！总数:{}，创建线程数量:{}", listCount, k);
            // 为每个处理页创建一个异步任务
            for (int pageNum = 1; pageNum <= k; pageNum++) {
                int finalPageNum = pageNum;
                int priority = Math.max(Thread.MAX_PRIORITY - finalPageNum + 1, Thread.NORM_PRIORITY);
                if (pageNum == k) {
                    priority = Thread.MIN_PRIORITY;
                }
                pcmTaskExecutor.setThreadPriority(priority);
                pcmTaskExecutor.submit(() -> {
                    try {
                        handlerExport(finalPageNum, pageSize, userVO, excelWriter, writeSheet, count);
                    } catch (Exception e) {
                        log.error("导出失败！ 请求参数:{}", userVO, e);
                    } finally {
                        sheetThreadSignal.countDown();
                    }
                });
            }

            try {
                // 等待所有异步任务完成，
                int timeout = 600;
                boolean completed = sheetThreadSignal.await(timeout, TimeUnit.SECONDS);
                if (!completed) {
                    log.error("导出任务未能在超时时间内完成!超过了:{}秒!", timeout);
                }
            } catch (InterruptedException e) {
                log.error("导出等待失败！ 请求参数:{}", userVO, e);
            }
            log.info("导出完成！总数:{},耗时：{}ms", count.get(), (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            log.error("导出失败", e);
        }
    }

    /**
     * 生成数据
     *
     * @param size
     * @return
     */
    @Override
    public ApiResult generatedData(Integer size) {
        int pageSize = 2000;
        int k = ceilDivision(size, pageSize);
        CountDownLatch sheetThreadSignal = new CountDownLatch(k);
        log.info("开始写入！总数:{}，创建线程数量:{}", size, k);
        // 为每个处理页创建一个异步任务
        for (int pageNum = 1; pageNum <= k; pageNum++) {
            int finalPageNum = pageNum;
            pcmTaskExecutor.submit(() -> {
                try {
                    generatedData(finalPageNum,pageSize);
                } catch (Exception e) {
                    log.error("写入失败！ 请求参数:{}", size, e);
                } finally {
                    sheetThreadSignal.countDown();
                }
            });
        }
        try {
            // 等待所有异步任务完成，
            int timeout = 600;
            boolean completed = sheetThreadSignal.await(timeout, TimeUnit.SECONDS);
            if (!completed) {
                log.error("写入任务未能在超时时间内完成!超过了:{}秒!", timeout);
            }
        } catch (InterruptedException e) {
            log.error("写入等待失败！ 请求参数:{}", size, e);
        }

        return ApiResult.success();
    }

    /**
     * 导入数据
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public ApiResult importExcel(MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return ApiResult.error("上传文件不能为空");
        }

        try (InputStream inputStream = file.getInputStream()) {
            FastExcel.read(inputStream, UserExcelVO.class, userDataListener)
                    .sheet().doRead();
        } catch (IOException e) {
            log.error("文件处理失败", e);
            return ApiResult.error("文件处理失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("发生未知错误", e);
            return ApiResult.error("发生未知错误: " + e.getMessage());
        }

        return ApiResult.success();
    }


    private void generatedData(int pageNum,int finalPageSize) {
        List<User> entities = new ArrayList<>();
        for (int i = 0; i < finalPageSize; i++) {
            User user = new User();
            user.setAge(i);
            user.setName("张三_"+pageNum+"_" + i);
            user.setSex(i % 2 == 0 ? 1 : 2);
            entities.add(user);
        }
        userDao.insertBatch(entities);
    }


    private void handlerExport(int finalPageNum, int finalPageSize, UserVO userVO, ExcelWriter excelWriter, WriteSheet writeSheet, AtomicInteger count) {
//        PageHelper.startPage(finalPageNum, finalPageSize);
        userVO.setPageNum(finalPageNum);
        userVO.setPageSize(finalPageSize);
        List<UserVO> result = userDao.queryAll(userVO);
        if (result.isEmpty()) {
            log.info("第{}页没有数据", finalPageNum);
            return;
        }
        List<UserExcelVO> excelData = getExcelData(result);
        synchronized (excelWriter) {
            excelWriter.write(excelData, writeSheet);
            count.addAndGet(excelData.size());
            log.info("第{}页写入完成，共写入{}条数据!当前已写入数量:{}", finalPageNum, excelData.size(), count.get());
        }

    }


    private int getPageSize(int listCount) {
        int pageSize = 10000;
        if (listCount > 10000) {
            pageSize = 2000;
        }
        if (listCount > 100000) {
            pageSize = 5000;
        }
        return pageSize;
    }

    private int ceilDivision(int totalItems, int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("Batch size must be greater than 0");
        }
        return (int) Math.ceil((double) totalItems / batchSize);
    }
}
