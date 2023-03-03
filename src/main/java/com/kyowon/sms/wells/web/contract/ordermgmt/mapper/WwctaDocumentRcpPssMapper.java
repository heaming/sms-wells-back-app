package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentRcpPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentRcpPssDto.SearchRes;

@Mapper
public interface WwctaDocumentRcpPssMapper {

    List<SearchRes> selectDocumentRcpPss(SearchReq dto);

}
