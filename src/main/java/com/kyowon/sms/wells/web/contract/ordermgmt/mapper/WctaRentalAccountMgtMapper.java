package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaRentalAccountMgtMapper {
    PagingResult<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> selectBpdRentalAccount(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto,
        PageInfo pageInfo
    );

    List<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> selectBpdRentalAccount(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto
    );

    PagingResult<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> selectByoRentalAccount(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto,
        PageInfo pageInfo
    );

    List<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> selectByoRentalAccount(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto
    );
}
