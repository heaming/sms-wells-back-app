package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncManagementCstRglvlMapper {
    PagingResult<WsncManagementCstRglvlDto.SearchRes> selectManagementCustomerRglvlPages(
        WsncManagementCstRglvlDto.SearchReq dto,
        PageInfo pageInfo
    );

    int updateClientServiceExchangeInfo(WsncManagementCstRglvlExchangeInfoDvo dvo);

    int updateClientServiceBsAssignInfo(WsncManagementCstRglvlBsAssignInfoDvo dvo);

    WsncManagementCstRglvlDto.OrganizationRes selectOrganizationInfo(String ogId);
}
