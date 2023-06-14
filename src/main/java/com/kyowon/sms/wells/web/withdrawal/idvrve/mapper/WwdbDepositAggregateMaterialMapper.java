package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialTotalRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbDepositAggregateMaterialMapper {

    PagingResult<SearchDepositAggregateMaterialRes> selectDepositAggregateMaterial(
        @Valid
        SearchDepositAggregateMaterialReq req,
        @Valid
        PageInfo pageInfo
    );

    SearchDepositAggregateMaterialTotalRes selectDepositAggregateMaterialTotal(
        SearchDepositAggregateMaterialReq req
    );

    List<SearchDepositAggregateMaterialRes> selectDepositAggregateMaterial(
        SearchDepositAggregateMaterialReq req
    );

}
