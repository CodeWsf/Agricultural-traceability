package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface HurdlesService {
    List< Integer> queryAllMax();
    PageInfo<ManagerHurdlesExt> findByPage(Integer pageNum, Integer pageSize, String hName, Integer hMax  , String fhName , String hEnable);
    void modifyStatus(String hId, String hEnable);

    void modifyStatusBatch(List<Map<String, String>> list);

    List<ManagerHurdles> findAllEnable();
}
