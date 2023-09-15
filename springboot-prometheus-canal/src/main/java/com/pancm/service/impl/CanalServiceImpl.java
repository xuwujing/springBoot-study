package com.pancm.service.impl;

import com.pancm.dao.CanalRepository;
import com.pancm.domain.CanalDO;
import com.pancm.service.ICanalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pancm
 * @Title: springBoot-study
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2023/9/15
 */
@Service
public class CanalServiceImpl implements ICanalService {

    @Resource
    private CanalRepository canalRepository;

    @Override
    public CanalDO save(CanalDO canalDO) {
        return canalRepository.save(canalDO);
    }

    @Override
    public List<CanalDO> query(CanalDO canalDO) {
        return  canalRepository.findAll();
    }
}
