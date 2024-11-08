package com.pancm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 10000001L;

    @JSONField(name = "total")
    private long totalNum;

    private List<T> list;

    private int pageSize;

    @JSONField(name = "currentPage")
    private int pageNum;

    public PageResult(long totalNum, List<T> list, int pageSize, int pageNum) {
        this.totalNum = totalNum;
        this.list = list;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
}
