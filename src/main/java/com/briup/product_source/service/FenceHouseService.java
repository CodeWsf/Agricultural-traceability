package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ext.ManagerFenceHouseExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FenceHouseService {
    PageInfo<ManagerFenceHouse> findByPage(int pageNum, int pageSize, String fhName);
    ManagerFenceHouseExt findById(String id);
    void saveOrUpdate(ManagerFenceHouse house);
    //删除指定id的栏舍信息
    void removeById(String fhId);
    //批量删除栏舍
    void removeBatch(List<String> ids);
    List<ManagerFenceHouse> queryAll();
}
