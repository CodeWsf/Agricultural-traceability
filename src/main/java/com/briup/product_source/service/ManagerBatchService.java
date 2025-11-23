package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerBatch;

import java.util.List;

public interface ManagerBatchService {

    List<ManagerBatch> findAllUnquarantined();

    List<ManagerBatch> findAll();
}
