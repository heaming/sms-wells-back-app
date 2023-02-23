package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaRentalAccountMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaRentalAccountMgtService {
    private final WctaRentalAccountMgtMapper mapper;

    public PagingResult<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> getBpdRentalAccount(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBpdRentalAccount(dto, pageInfo);
    }

    public List<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> getBpdRentalAccountExcelDownload(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto
    ) {
        return mapper.selectBpdRentalAccount(dto);
    }

    public PagingResult<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> getByoRentalAccount(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectByoRentalAccount(dto, pageInfo);
    }

    public List<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> getByoRentalAccountExcelDownload(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto
    ) {
        return mapper.selectByoRentalAccount(dto);
    }
}
