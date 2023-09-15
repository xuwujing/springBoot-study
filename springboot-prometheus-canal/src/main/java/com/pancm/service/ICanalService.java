package com.pancm.service;

import com.pancm.domain.CanalDO;

import java.util.List;

/**
 * @author pancm
 * @Title: springBoot-study
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2023/8/25
 */
public interface ICanalService {

    CanalDO  save(CanalDO canalDO);

    List<CanalDO> query(CanalDO canalDO);

}
