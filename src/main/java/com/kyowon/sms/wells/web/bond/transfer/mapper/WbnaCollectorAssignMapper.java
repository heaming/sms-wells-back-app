package com.kyowon.sms.wells.web.bond.transfer.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaCollectorAssignDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 집금자배정 맵퍼
 * wbna-collector-assign.xml
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-21
 */
@Mapper
public interface WbnaCollectorAssignMapper {
    List<SearchRes> selectCollectorAssigns(
        SearchReq dto
    );

    PagingResult<SearchDetailRes> selectCollectorAssignDetailPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchDetailRes> selectCollectorAssignDetailPages(
        SearchReq dto
    );

    SearchSummaryRes selectCollectorAssignDetailsSummary(
        SearchReq dto
    );

    int updateCollectorAssingsConfirm(WbnaCollectorAssignDvo dvo);

    int updateCollectorAssing(WbnaCollectorAssignDvo dvo);

    int updateCollectorAssingForBondAssignItemization(WbnaCollectorAssignDvo dvo);
}
