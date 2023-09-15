package com.pancm.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author pancm
 * @Title: springBoot-study
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2023/9/15
 */
@Data
@Entity
@Table(name = "business_monitoring_original_data")
public class CanalDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String binlogName;
    private String dbName;
    private String tableName;
    private String event_type;
    private String keyName;
    private String keyValue;
    private String timeIndex;
    private String gatherTime;



}
