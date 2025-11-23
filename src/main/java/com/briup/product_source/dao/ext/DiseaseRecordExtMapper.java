package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRecordExtMapper {

    List<DiseaseRecordExt> selectDiseasedAnimal(String drStatus, Integer drDId);
}