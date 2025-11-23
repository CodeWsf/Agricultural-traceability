package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerBatch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerBatchMapper {

    List<ManagerBatch> selectAllUnquarantined();

    ManagerBatch selectByPrimaryKey(String grBatchId);

    int updateQualifiedById(String bQualified, String grBatchId);

    List<ManagerBatch> selectAllBatches();
}