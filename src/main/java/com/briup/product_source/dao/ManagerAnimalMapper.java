package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerAnimal;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAnimalMapper {

    ManagerAnimal selectByPrimaryKey(String animalId);

    int updateHealthyByAnimalId(String healthy, String animalId);
}