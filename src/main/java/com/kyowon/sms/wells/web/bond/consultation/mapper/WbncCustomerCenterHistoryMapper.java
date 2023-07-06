package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import com.sds.sflex.system.config.annotation.CustsvcMapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerCenterHistoryDto.*;

@CustsvcMapper
public interface WbncCustomerCenterHistoryMapper {
    List<FindRes> selectCustomerCenterHistories(String cstNo);
}
