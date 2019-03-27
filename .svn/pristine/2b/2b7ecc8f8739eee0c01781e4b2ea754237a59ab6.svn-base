package com.lamp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/1.
 */
public class Pagination {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer totalCount = 0;

    /**
     * 构造函数
     *
     * @param pageNo
     * @param pageSize
     * @param totalCount
     */
    public Pagination(Integer pageNo, Integer pageSize, Integer totalCount) {
        setPageNo(pageNo);
        setPageSize(pageSize);
        setTotalCount(totalCount);
    }

    /**
     * 返回分页Map
     *
     * @return
     */
    public Map<String, Object> getPage() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("totalCount", totalCount);
        return map;
    }

    /**
     * 获取页码
     *
     * @return
     */
    public Integer getStart() {
        return (pageNo - 1) * pageSize;
    }

    public Integer getLimit() {
        return pageSize;
    }

    /**
     * if pageNo < 1 then pageNo=1
     *
     * @param pageNo
     */
    private void setPageNo(Integer pageNo) {
        if (pageNo != null && pageNo > 0) {
            this.pageNo = pageNo;
        }
    }

    /**
     * if pageSize< 1 then pageSize=DEF_COUNT
     *
     * @param pageSize
     */
    private void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    /**
     * if totalCount<0 then totalCount=0
     *
     * @param totalCount
     */
    private void setTotalCount(Integer totalCount) {
        if (totalCount != null && totalCount > 0) {
            this.totalCount = totalCount;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
}
