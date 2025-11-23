package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerHurdles;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerHurdlesMapper {
    int selectCountByFhId(String fhId);
    List< Integer> selectAllMax();

    ManagerHurdles selectByPrimaryKey(String hId);

    int updateEnableById(String hId, String hEnable);

    List<ManagerHurdles> selectAllEnable();
}
