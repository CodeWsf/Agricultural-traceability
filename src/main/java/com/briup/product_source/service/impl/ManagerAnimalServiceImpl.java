package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ext.ManagerAnimalExtMapper;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.briup.product_source.service.ManagerAnimalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerAnimalServiceImpl implements ManagerAnimalService {
    @Autowired
    private ManagerAnimalExtMapper animalExtMapper;
    @Override
    public PageInfo<ManagerAnimalExt> findByPage(Integer pageNum, Integer pageSize, String aHeathy, String aGender){
        PageHelper.startPage(pageNum,pageSize);
        List<ManagerAnimalExt> list=animalExtMapper.selectAnimalRelated(aHeathy,aGender);
        PageInfo<ManagerAnimalExt> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

}
