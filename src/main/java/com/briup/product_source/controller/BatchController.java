package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.ManagerBatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "批次记录模块")
@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    private ManagerBatchService batchService;
    @ApiOperation("2.查询未检疫的批次")
    @GetMapping("/queryAllUnquarantined")
    public Result queryAllUnquarantined(){
        List<ManagerBatch> list=batchService.findAllUnquarantined();
        return Result.success(list);
    }

    @ApiOperation("查询所有批次信息")
    @GetMapping("/queryAll")
    public Result queryAllBatches(){
        List<ManagerBatch> list=batchService.findAll();
        return Result.success(list);
    }
}