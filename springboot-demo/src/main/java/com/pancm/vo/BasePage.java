package com.pancm.vo;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;


import java.util.List;

@ApiModel
@Data
public class BasePage {

    /**
     * 页码
     * 必须定义为 protected or public，否则子类不可见
     */
    @ApiModelProperty(name = "page_num",value = "分页 - 第几页")
    protected Integer pageNum=1;

    /**
     * 页大小
     */
    @ApiModelProperty(name = "page_num",value = "分页 - 每页记录数")
    protected Integer pageSize=10;

    /**
     * 排序字段名称
     */
    @ApiModelProperty(name = "sort_name",value = "分页 - 排序列名")
    protected String sortName;

    /**
     * 排序方式
     */
    @ApiModelProperty(name = "sort_order",value = "分页 - 排序方式，asc/desc")
    protected String sortOrder;

    @ApiModelProperty(name = "date", value = "起止日期")
    @JSONField(name = "date")
    protected List<String> dateRange;

    protected String startDate;

    protected String endDate;

    /**
     * 默认排序
     */
    protected String defaultOrder;

    /**
     * 排序字符串
     */
    protected String orderString;

    public String getOrderBy() {
        if (!StringUtils.isEmpty(this.sortName) && !StringUtils.isEmpty(this.sortOrder)) {
            String orderBy = this.sortName + " " + this.sortOrder;
            if (!StringUtils.isEmpty(this.defaultOrder) && !orderBy.equals(defaultOrder)) {
                orderBy += ", " + defaultOrder;
            }
            return orderBy.replaceAll(".*([';]+|(--)+).*", " ");
        }
        if (!StringUtils.isEmpty(this.defaultOrder)) {
            return defaultOrder.replaceAll(".*([';]+|(--)+).*", " ");
        }
        return null;
    }

}
