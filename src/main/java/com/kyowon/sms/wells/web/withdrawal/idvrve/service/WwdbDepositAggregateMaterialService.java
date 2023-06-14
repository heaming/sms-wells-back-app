package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialTotalRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositAggregateMaterialMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbDepositAggregateMaterialService {

    private final WwdbDepositAggregateMaterialMapper mapper;

    /**
     * 입금집계 목록
     * @param pageInfo 
     * @param SearchDepositAggregateMaterialReq
     * @return PagingResult<SearchDepositAggregateMaterialRes>
     */
    public PagingResult<SearchDepositAggregateMaterialRes> getDepositAggregateMaterialPages(
        @Valid
        SearchDepositAggregateMaterialReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return mapper.selectDepositAggregateMaterial(req, pageInfo);
    }

    /**
     * 입금집계 합계 및 비율 서비스
     * @param req 
     * @return SearchDepositAggregateMaterialTotalRes
     */
    public SearchDepositAggregateMaterialTotalRes getDepositAggregateMaterialTotal(
        SearchDepositAggregateMaterialReq req
    ) {
        /* 검색결과 합계 및 비율 구하는 서비스 */
        return mapper.selectDepositAggregateMaterialTotal(req);
    }

    /**
     * 입금집계조회 엑셀다운로드
     * @param List 
     * @param SearchDepositAggregateMaterialReq
     * @return List<SearchDepositAggregateMaterialRes>
     */
    public List<SearchDepositAggregateMaterialRes> getDepositAggregateMaterialExcels(
        SearchDepositAggregateMaterialReq req
    ) {
        return mapper.selectDepositAggregateMaterial(req);
    }

}
