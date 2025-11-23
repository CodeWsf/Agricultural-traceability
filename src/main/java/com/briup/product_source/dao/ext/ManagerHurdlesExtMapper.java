package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerHurdlesExtMapper {
    List<ManagerHurdlesExt> findHurdlesWithHouseByConditions(String hName, Integer hMax, String fhName, String hEnable);
}
