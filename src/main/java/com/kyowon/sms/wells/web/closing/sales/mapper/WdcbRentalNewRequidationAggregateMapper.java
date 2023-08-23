package com.kyowon.sms.wells.web.closing.sales.mapper;

import static com.kyowon.sms.wells.web.closing.sales.dto.WdcbRentalNewRequidationAggregateDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WdcbRentalNewRequidationAggregateMapper {
    List<SearchRes> selectAggregates(
        SearchReq dto
    );

    List<SearchDetailRes> selectFreeExperiences(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectRentalNews(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectProductChanges(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectRentalWithdrawalFreeExperiences(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectRentalWithdrawals(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectRGradeCancels(
        SearchDetailReq dto
    );

    List<SearchDetailRes> selectAuthorityAuthorityResigns(
        SearchDetailReq dto
    );
}
