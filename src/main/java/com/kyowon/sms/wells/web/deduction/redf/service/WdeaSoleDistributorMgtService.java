package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.deduction.redf.converter.WdeaSoleDistributorMgtConverter;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.*;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaSoleDistributorMgtDvo;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaSoleDistributorMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaSoleDistributorMgtService {

    private final WdeaSoleDistributorMgtMapper mapper;
    private final WdeaSoleDistributorMgtConverter converter;

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 1번
     * @param dto
     * @return
     */
    public PagingResult<SearchSoleDistributorPrtnrRes> getSoleDistributorPrtnrPages(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    ) {
        return mapper.selectSoleDistributorPrtnrs(dto, pageInfo);
    }

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 1번 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchSoleDistributorPrtnrRes> getSoleDistributorPrtnrForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return mapper.selectSoleDistributorPrtnrs(dto);
    }

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 2번
     * @param dto
     * @return
     */
    public PagingResult<SearchSoleDistributorContractRes> getSoleDistributorContractPages(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    ) {
        return mapper.selectSoleDistributorContracts(dto, pageInfo);
    }

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 2번 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchSoleDistributorContractRes> getSoleDistributorContractForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return mapper.selectSoleDistributorContracts(dto);
    }

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 3번
     * @param dto
     * @return
     */
    public PagingResult<SearchBusinessToBusinessPrtnrRes> getBusinessToBusinessPrtnrPages(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBusinessToBusinessPrtnrs(dto, pageInfo);
    }

    /**
     * 총판/B2B 되물림 생성 목록조회 - 그리드 3번 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchBusinessToBusinessPrtnrRes> getBusinessToBusinessPrtnrForExcelDownload(
        SearchSoleDistributorCreateReq dto
    ) {
        return mapper.selectBusinessToBusinessPrtnrs(dto);
    }

    /**
     * 총판/B2B 되물림 관리 목록조회 - 2번탭
     * @param dto
     * @return
     */
    public PagingResult<SearchSoleDistributorMgtRes> getSoleDistributorB2bPages(
        SearchSoleDistributorMgtReq dto, PageInfo pageInfo
    ) {
        return mapper.selectSoleDistributorB2bs(dto, pageInfo);
    }

    /**
     * 총판/B2B 되물림 관리 목록조회 - 2번탭
     * @param dto
     * @return
     */
    public List<SearchSoleDistributorMgtRes> getSoleDistributorB2bForExcelDownload(
        SearchSoleDistributorMgtReq dto
    ) {
        return mapper.selectSoleDistributorB2bs(dto);
    }

    /**
     * 총판/B2B 되물림 관리 - 수정
     * @param dto
     * @return
     */
    @Transactional
    public int editSoleDistributorB2b(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WdeaSoleDistributorMgtDvo dvo = converter.mapSaveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);

            processCount += mapper.updateRedfDdtnAmt(dvo);
        }

        return processCount;
    }

    /**
     * 총판/B2B 되물림 금액 생성 후 프로세스(총판 가지급금 생성)
     *
     * @param redfAdsbOcYm 발생년월
     */
    @Transactional
    public void saveSoleDistributorB2bRedfAmt(String redfAdsbOcYm) {

        mapper.insertSoleDistributorB2bRedfAmt(redfAdsbOcYm);

    }

}
