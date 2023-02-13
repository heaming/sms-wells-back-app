package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceDetailDto.SearchRes;

@Mapper
public interface WctiTaxInvoiceDetailMapper {
    List<SearchRes> selectTaxInvoiceDetails(@Param("TXINV_ID")
    String txinvId);
}
