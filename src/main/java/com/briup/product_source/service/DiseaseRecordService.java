package com.briup.product_source.service;

import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DiseaseRecordService {

    List<DiseaseRecord> findAllDiseases();

    PageInfo<DiseaseRecordExt> findByPage(Integer pageNum, Integer pageSize, String drStatus, Integer drDId);

    void saveOrUpdate(DiseaseRecord record);
}
