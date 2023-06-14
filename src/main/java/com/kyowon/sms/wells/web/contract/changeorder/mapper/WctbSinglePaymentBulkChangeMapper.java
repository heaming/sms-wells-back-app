package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;

@Mapper
public interface WctbSinglePaymentBulkChangeMapper {
    List<SearchRes> selectSinglePaymentBulkChangs(
        String cntrNo, String cntrSn, String rfDt
    );
}
