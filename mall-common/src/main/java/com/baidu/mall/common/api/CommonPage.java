package com.baidu.mall.common.api;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 封装分页信息数据
 */
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;
    /**
      * @Description: 将PageHelper分页后的list转换为分页信息
      * @Date: 2020/3/12
      * @params [list]
      * @return com.baidu.mall.common.api.CommonPage<T>
    **/
    
    public static <T> CommonPage<T> resultPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    /**
      * @Description: 将SpringData分页后的list转换为分页信息
      * @Date: 2020/3/12
      * @params [page]
      * @return com.baidu.mall.common.api.CommonPage<T>
    **/
    public static <T>CommonPage<T> resultPage(Page<T> page){
        CommonPage<T> result=new CommonPage<>();
        result.setTotalPage(page.getTotalPages());
        result.setPageNum(page.getNumber());
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotalElements());
        result.setList(page.getContent());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
