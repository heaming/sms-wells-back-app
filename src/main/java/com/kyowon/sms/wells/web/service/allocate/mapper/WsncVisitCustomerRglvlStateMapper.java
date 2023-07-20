package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.FindOrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncVisitCustomerRglvlStateMapper {

    PagingResult<WsncVisitCustomerRglvlStateDto.SearchRes> selectVisitCustomerRglvlState(
        WsncVisitCustomerRglvlStateDto.SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectVisitCustomerRglvlState(
        SearchReq dto
    );

    FindOrganizationRes selectOrganizationInfo(String ogId);
}
