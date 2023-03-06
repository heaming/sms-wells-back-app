package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchRes;

@Mapper
public interface WwctaDocumentReceiptPssMapper {

    List<SearchRes> selectDocumentReceipts(SearchReq dto);

}
