package com.kyowon.sms.wells.web.bond.transfer.mapper;

import java.util.List;

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

    List<SearchDetailRes> selectPartTransferDetailPages(
        SearchDetailReq dto
    );

    Integer selectHasPartTransfer(
        String baseYm
    );

    Integer selectHasPartTransferDetail(
        SearchDetailReq dto
    );
}
