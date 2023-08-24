package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.OrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSvpdAsnRsTfIzDvo;

@Mapper
public interface WsncManagementCstRglvlMapper {

    List<SearchRes> selectManagementCustomerRglvls(
        SearchReq dto
    );

    int updateClientServiceExchangeInfo(WsncManagementCstRglvlExchangeInfoDvo dvo);

    int updateClientServiceBsAssignInfo(WsncManagementCstRglvlBsAssignInfoDvo dvo);

    OrganizationRes selectOrganizationInfo(String ogId);

    int insertTransfer(WsncSvpdAsnRsTfIzDvo dvo);
}
