package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.dao.QuarantineRegistrationMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.pojo.QuarantineRegistration;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.QuarantineRegistrationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class QuarantineRegistrationServiceImpl implements QuarantineRegistrationService {
    @Autowired
    QuarantineRegistrationMapper qrMapper;
    @Override
    public PageInfo<QuarantineRegistration> findByPage(Integer pageNum, Integer pageSize, String grMechanism, String bQualified){
        PageHelper.startPage(pageNum, pageSize);
        List<QuarantineRegistration> list = qrMapper.selectAllRecord(grMechanism, bQualified);
        PageInfo<QuarantineRegistration> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Autowired
    private ManagerBatchMapper batchMapper;
    @Override
    public  void saveOrUpdate(QuarantineRegistration qr){
        if (qr == null ||
                !StringUtils.hasText(qr.getGrMechanism()) ||
                !StringUtils.hasText(qr.getGrImg())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        //2.批次校验【保证该批次存在】
        String grBatchId = qr.getGrBatchId();
        ManagerBatch batch = batchMapper.selectByPrimaryKey(grBatchId);
        if(batch == null)
            throw new ServiceException(ResultCode.BATCH_NOT_EXIST);
//3.执行更新或更新，返回受影响的行数result
        int result;
        if (qr.getGrId() != null) {
//3.1 有id->更新操作
            result = qrMapper.updateByPrimaryKey(qr);
        } else {
//3.2 无id->新增操作
            result = qrMapper.insert(qr);
        }
//4.结果判断：如果影响行数为0，则抛出异常
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
//5.修改指定批次检疫状态为"已检疫"及检疫合格状态 bQualified
        String bQualified = qr.getBQualified();
        if (batchMapper.updateQualifiedById(bQualified, grBatchId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
