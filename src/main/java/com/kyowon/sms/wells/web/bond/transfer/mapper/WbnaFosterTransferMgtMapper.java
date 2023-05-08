package com.kyowon.sms.wells.web.bond.transfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondContractBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WbnaFosterTransferMgtMapper {

    List<SearchRes> selectFosterTransfers(WbnaBondContractBaseDvo dvo);

    PagingResult<SearchDetailRes> selectFosterTransferDetails(SearchReq dto, PageInfo pageInfo);

    SearchDetailSummaryRes selectPartTransferDetailsSummary(SearchReq dto);

    List<SearchDetailRes> selectFosterTransferDetails(SearchReq dto);

}
