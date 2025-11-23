package com.briup.product_source.service;

import com.briup.product_source.pojo.QuarantineRegistration;
import com.github.pagehelper.PageInfo;

public interface QuarantineRegistrationService {

    PageInfo<QuarantineRegistration> findByPage(Integer pageNum, Integer pageSize, String grMechanism, String bQualified);

    void saveOrUpdate(QuarantineRegistration recode);
}
