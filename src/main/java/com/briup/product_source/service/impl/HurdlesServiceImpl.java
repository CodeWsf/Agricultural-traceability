package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerHurdlesMapper;
import com.briup.product_source.dao.ext.ManagerHurdlesExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.HurdlesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HurdlesServiceImpl implements HurdlesService {
    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;
    @Override
    public List<Integer> queryAllMax() {
        return managerHurdlesMapper.selectAllMax();
    }
    @Autowired
    private ManagerHurdlesExtMapper managerHurdlesExtMapper;
    @Override
    public PageInfo<ManagerHurdlesExt> findByPage(Integer pageNum, Integer pageSize, String hName, Integer hMax, String fhName, String hEnable) {
        PageHelper.startPage(pageNum, pageSize);
        List<ManagerHurdlesExt> list = managerHurdlesExtMapper.findHurdlesWithHouseByConditions(hName, hMax, fhName, hEnable);
        PageInfo<ManagerHurdlesExt> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void modifyStatus(String hId, String hEnable){
        ManagerHurdles managerHurdles= managerHurdlesMapper.selectByPrimaryKey(hId);
        if(managerHurdles==null)
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        //2.调整状态值【解决传参bug】
//System.out.println("in service, hEnable: " + hEnable);
        if ("可用".equals(hEnable)) {
            hEnable = "禁用";
        }else {
            hEnable = "可用";
        }
        if (managerHurdlesMapper.updateEnableById(hId, hEnable) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void modifyStatusBatch(List<Map<String, String>> list){
        int sum = 0;
        for (Map<String, String> map : list) {
            String hId = map.get("hId");
            if (managerHurdlesMapper.selectByPrimaryKey(hId) == null) {
                continue;
            }
            String hEnable = map.get("hEnable");
            String status = "可用";
            if (status.equals(hEnable)) {
                status = "禁用";
            }
            sum += managerHurdlesMapper.updateEnableById(hId, status);
        }
        if (sum == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public List<ManagerHurdles> findAllEnable(){
        List<ManagerHurdles> list = managerHurdlesMapper.selectAllEnable();
        return list;
    }
}
