package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WwfeaAccountNetIncreaseDto;

@Mapper
public interface WwfeaAccountNetIncreaseMapper {

    List<WwfeaAccountNetIncreaseDto.SearchRes> selectLstmmCancels(
        WwfeaAccountNetIncreaseDto.SearchReq req
    );

    List<WwfeaAccountNetIncreaseDto.SearchRes> selectNewSells(
        WwfeaAccountNetIncreaseDto.SearchReq req
    );

    List<WwfeaAccountNetIncreaseDto.SearchRes> selectAggregateChecks(
        WwfeaAccountNetIncreaseDto.SearchReq req
    );

}
