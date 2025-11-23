package com.briup.product_source.dao;

import com.briup.product_source.pojo.DiseaseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRecordMapper {

    List<DiseaseRecord> selectAll();

    int insert(DiseaseRecord record);

    int updateByPrimaryKey(DiseaseRecord record);

    DiseaseRecord selectByPrimaryKey(Integer drId);
}