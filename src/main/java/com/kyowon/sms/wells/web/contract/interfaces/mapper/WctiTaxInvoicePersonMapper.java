package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoicePersonDto.SearchRes;

@Mapper
public interface WctiTaxInvoicePersonMapper {
    List<SearchRes> selectTaxInvoicePersons(@Param("PSIC_NM")
    String psicNm);
}
