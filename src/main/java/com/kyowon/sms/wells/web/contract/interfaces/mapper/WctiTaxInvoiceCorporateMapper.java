package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WctiTaxInvoiceCorporateMapper {
    List<SearchRes> selectTaxInvoiceCorporates(@Param("DLPNR_NM")
    String dlpnrNm);
}
