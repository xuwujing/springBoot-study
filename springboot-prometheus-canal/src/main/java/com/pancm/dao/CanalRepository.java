package com.pancm.dao;

import com.pancm.domain.CanalDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanalRepository extends JpaRepository<CanalDO, Long> {
}