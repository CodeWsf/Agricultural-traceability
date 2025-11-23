package com.briup.product_source.dao;

import com.briup.product_source.pojo.QuarantineRegistration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarantineRegistrationMapper {

    List<QuarantineRegistration> selectAllRecord(String grMechanism, String bQualified);

    int insert(QuarantineRegistration qr);

    int updateByPrimaryKey(QuarantineRegistration qr);
}