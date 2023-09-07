package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodCustomerTfCreateDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncBsPeriodCustomerTfMgtMapper {

    PagingResult<SearchRes> selectBsPeriodCustomers(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectBsPeriodCustomers(
        SearchReq dto
    );

    List<BranchsAndServiceCentersRes> selectBranchsAndServiceCenters(
        String ogTpCd, String ogId, String dgr1LevlOgId, String dgr2LevlOgId
    );

    List<ManagersAndEngineersRes> selectManagersAndEngineers(String ogId);

    String selectAsnTfDvCd(String baseYm, String bfchIchrBrchOgId, String afchIchrBrchOgId);

    int insertTransfer(WsncBsPeriodCustomerTfCreateDvo dvo);

    int mergeTransferConfirm(WsncBsPeriodCustomerTfCreateDvo dvo);

    int updateCstSvBfsvcAsn(WsncBsPeriodCustomerTfCreateDvo dvo);

    SearchAuthRes selectBsPeriodCustomersManagerAuthYn();
}
