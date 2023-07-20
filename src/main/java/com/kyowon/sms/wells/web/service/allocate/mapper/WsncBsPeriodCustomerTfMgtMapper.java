package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.BranchsAndServiceCentersRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.ManagersAndEngineersRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodCustomerTfCreateDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}
