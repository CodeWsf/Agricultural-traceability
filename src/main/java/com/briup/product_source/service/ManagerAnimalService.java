package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.github.pagehelper.PageInfo;

public interface ManagerAnimalService {

    PageInfo<ManagerAnimalExt> findByPage(Integer pageNum, Integer pageSize, String aHeathy, String aGender);

    void saveOrUpdate(ManagerAnimal animal);
}
