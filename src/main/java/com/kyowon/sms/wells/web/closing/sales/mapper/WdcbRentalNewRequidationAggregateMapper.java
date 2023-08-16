package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbRentalNewRequidationAggregateDto;

@Mapper
public interface WdcbRentalNewRequidationAggregateMapper {
    List<WdcbRentalNewRequidationAggregateDto.SearchRes> selectAggregates(
        WdcbRentalNewRequidationAggregateDto.SearchReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectFreeExperiences(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectRentalNews(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectProductChanges(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectRentalWithdrawalFreeExperiences(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectRentalWithdrawals(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectRGradeCancels(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );

    List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> selectAuthorityAuthorityResigns(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    );
}
