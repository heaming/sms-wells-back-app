package com.kyowon.sms.wells.web.bond.transfer.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondPartTransferDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.SearchDetailReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 파트이관 맵퍼
 * wbna-bond-part-transfer.xml
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-17
 */
@Mapper
public interface WbnaBondPartTransferMapper {
    List<SearchRes> selectPartTransferAggregation(
        SearchReq dto
    );

    PagingResult<SearchDetailRes> selectPartTransferDetailPages(
        SearchDetailReq dto, PageInfo pageInfo
    );

    SearchDetailSummaryRes selectPartTransferDetailSummary(SearchDetailReq dto);

    List<SearchDetailRes> selectPartTransferDetailPages(
        SearchDetailReq dto
    );

    int insertPartTransfers(WbnaBondPartTransferDvo dvo);

    int deletePartTransfers(WbnaBondPartTransferDvo dvo);

    int updateCollectionPartForBndCntrBas(WbnaBondPartTransferDvo dvo);

    int updateBondContractBase(WbnaBondPartTransferDvo dvo);

    int updateBondAssignItemization(WbnaBondPartTransferDvo dvo);

}
