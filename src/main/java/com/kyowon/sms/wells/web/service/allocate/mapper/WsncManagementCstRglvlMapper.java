package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.OrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import java.util.List;

@Mapper
public interface WsncManagementCstRglvlMapper {
    PagingResult<SearchRes> selectManagementCustomerRglvls(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectManagementCustomerRglvls(
        SearchReq dto
    );

    int updateClientServiceExchangeInfo(WsncManagementCstRglvlExchangeInfoDvo dvo);

    int updateClientServiceBsAssignInfo(WsncManagementCstRglvlBsAssignInfoDvo dvo);

    OrganizationRes selectOrganizationInfo(String ogId);
}
