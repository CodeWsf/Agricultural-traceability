package com.briup.product_source.dao;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManagerDiseaseMapper {
    @MapKey("dName")
    List<Map<String, Object>> countDisease();
}