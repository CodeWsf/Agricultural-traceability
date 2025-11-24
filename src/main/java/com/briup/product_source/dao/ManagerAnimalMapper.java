package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerAnimal;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ManagerAnimalMapper {

    ManagerAnimal selectByPrimaryKey(String animalId);

    int updateHealthyByAnimalId(String healthy, String animalId);

    int updateByPrimaryKey(ManagerAnimal animal);

    int insert(ManagerAnimal animal);

    Map<String, Integer> countWeight();
}