package com.briup.product_source.controller;

import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.DiseaseRecordService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "病症记录模块")
@RestController
@RequestMapping("/diseaseRecord")
public class DiseaseController {
    @Autowired
    private DiseaseRecordService diseaseService;
    @ApiOperation("查询所有病症信息")
    @GetMapping("/queryAllDisease")
    public Result getAnimalRelated(){
        List<DiseaseRecord> list=diseaseService.findAllDiseases();
        return Result.success(list);
    }

    @ApiOperation("分页+条件查询病症记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",defaultValue = "1",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",defaultValue = "10",required = true),
            @ApiImplicitParam(name = "drStatus",value = "未治疗|治疗中|已治疗"),
            @ApiImplicitParam(name = "drDId",value = "病症id")
    })
    @GetMapping
    public Result getAnimalRelated(Integer pageNum, Integer pageSize, String drStatus, Integer drDId){
        PageInfo<DiseaseRecordExt> pageInfo = diseaseService.findByPage(pageNum, pageSize, drStatus, drDId);
        return Result.success(pageInfo);
    }

    @ApiOperation("添加/修改病症记录信息")
    @PostMapping("/saveOrUpdate")
    public Result reviseDiseaseRecord(@RequestBody DiseaseRecord record){
        diseaseService.saveOrUpdate(record);
        return Result.success("操作成功");
    }
}
