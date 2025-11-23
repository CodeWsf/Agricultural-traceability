package com.briup.product_source.service.impl;

import com.briup.product_source.dao.DiseaseRecordMapper;
import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ext.DiseaseRecordExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.DiseaseRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DiseaseRecordServiceImpl implements DiseaseRecordService {
    @Autowired
    private DiseaseRecordMapper diseaseMapper;
    @Override
    public List<DiseaseRecord> findAllDiseases(){
        List<DiseaseRecord> list=diseaseMapper.selectAll();
        return list;
    }
    @Autowired
    private DiseaseRecordExtMapper dRecordExtMapper;
    @Override
    public PageInfo<DiseaseRecordExt> findByPage(Integer pageNum, Integer pageSize, String drStatus, Integer drDId){
        PageHelper.startPage(pageNum,pageSize);
        List<DiseaseRecordExt> list=dRecordExtMapper.selectDiseasedAnimal(drStatus,drDId);
        PageInfo<DiseaseRecordExt> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    @Autowired
    private ManagerAnimalMapper animalMapper;
    @Override
    public void saveOrUpdate(DiseaseRecord record){
        String animalId = record.getDrAnimalId();
        if (!StringUtils.hasText(animalId) ||
                !StringUtils.hasText(record.getDrDesc())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        ManagerAnimal animalFromDB = animalMapper.selectByPrimaryKey(animalId);
        if (animalFromDB == null)
            throw new ServiceException(ResultCode.FAIL);
        if(!"养殖中".equals(animalFromDB.getAStatus())) {
            throw new ServiceException(ResultCode.ANIMAL_IS_NOT_IN_BREEDING);
        }
        //3.添加或者修改诊疗记录
// sql语句执行后，返回受影响的行数result
        int result;
        Integer drId = record.getDrId();
        String drStatus = record.getDrStatus();
        if (drId != null) {
//3.1 有id->更新操作
            if (diseaseMapper.selectByPrimaryKey(drId) == null)
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            result = diseaseMapper.updateByPrimaryKey(record);
        } else {
//3.2 无id->新增操作
// 未传诊疗状态，默认为未治疗
            if (!StringUtils.hasText(drStatus)) {
                record.setDrStatus("未治疗");
            }
//新增记录
            result = diseaseMapper.insert(record);
        }
// 添加病症记录失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
        String healthy = "健康";
        if (!"已治疗".equals(drStatus)) {
            healthy = "生病";
        }
        if (animalMapper.updateHealthyByAnimalId(healthy, animalId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
