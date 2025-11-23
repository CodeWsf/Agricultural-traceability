package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.HurdlesService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "栏圈管理模块")
@RestController
@RequestMapping("/hurdle")
public class HurdleController {
    @Autowired
    private HurdlesService hurdlesService;
    @ApiOperation("查询所有的栏圈容量")
    @GetMapping("/queryAllMax")
    public Result queryAllMax(){
        List< Integer> list= hurdlesService.queryAllMax();

        return Result.success(list);
    }

    @ApiOperation("实现分页+条件查询栏圈信息（含所属栏舍），注意查询结果按创建时间降序")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum",value = "当前页码",defaultValue = "1",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",defaultValue = "10",required = true),
            @ApiImplicitParam(name = "fhName",value = "栏舍名称"),
           @ApiImplicitParam(name = "hName",value = "栏圈名称"),
            @ApiImplicitParam(name = "hMax",value = "栏圈容量"),
            @ApiImplicitParam(name = "fhName",value = "所属栏舍"),
            @ApiImplicitParam(name = "hEnable",value = "是否可用")
    }
    )
    @GetMapping
    public Result queryByConditionsAndPage(Integer pageNum,Integer pageSize,String hName,Integer hMax  , String fhName , String hEnable){
        PageInfo<ManagerHurdlesExt> pageInfo = hurdlesService.findByPage(pageNum, pageSize, hName, hMax, fhName, hEnable);
        return Result.success(pageInfo);
    }
    @ApiOperation("用于修改栏圈状态：禁用/启用，根据栏圈id操作")
    @PutMapping("/{hId}/{hEnable}")
    public Result changeStatus(@PathVariable String hId,@PathVariable String hEnable){
        hurdlesService.modifyStatus(hId,hEnable);
        return Result.success();
    }
    @ApiOperation("用于批量修改栏圈的启用状态")
    @PutMapping
    public Result changeStatusBatch(List<Map<String,String>> list){
        hurdlesService.modifyStatusBatch(list);
        return Result.success();
    }

    @ApiOperation("查询所有可用栏圈")
    @GetMapping("/queryAllEnable")
    public Result getAllEnableHurdles(){
        List<ManagerHurdles> list=hurdlesService.findAllEnable();
        return Result.success(list);
    }
}
