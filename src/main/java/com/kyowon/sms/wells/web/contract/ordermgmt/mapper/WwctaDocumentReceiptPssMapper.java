package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WwctaDocumentReceiptPssDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WwctaDocumentReceiptPssRequestDvo;

@Mapper
public interface WwctaDocumentReceiptPssMapper {

    List<WwctaDocumentReceiptPssDvo> selectDocumentReceipts(WwctaDocumentReceiptPssRequestDvo dvo);

    int updateDocumentRcpCnfm(SearchReq dto);

}
