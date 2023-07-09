package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.converter.WdeaAwRedfEtAmtConverter;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.*;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAwRedfEtAmtDvo;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaAwRedfEtAmtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaAwRedfEtAmtService {

    private final WdeaAwRedfEtAmtMapper mapper;
    private final WdeaAwRedfEtAmtConverter converter;

    /**
     * 수당되물림 예상액 - 판매리스트 내역 목록 조회
     * @param dto
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRedfIzRes> getAwRedfEtAmtIzPages(SearchRedfIzReq dto, PageInfo pageInfo) {
        return mapper.selectAwRedfIzAmts(dto, pageInfo);
    }

    /**
    * 수당되물림 예상액 - 판매리스트 내역 목록 엑셀다운로드
    * @param dto
    * @return SXSSFWorkbook
    */
    public List<SearchRedfIzRes> getAwRedfEtAmtIzForExcelDownload(SearchRedfIzReq dto) throws Exception {
        return mapper.selectAwRedfIzAmts(dto);
    }

    /**
    * 수당되물림 예상액 - 실적현황 목록 엑셀다운로드
    * @param dto
    * @return 조회결과
    */
    public List<SearchRedfIzPerfPsRes> getAwRedfIzPerfPsAmt(SearchRedfIzReq dto) {
        return mapper.selectAwRedfIzPerfPsAmt(dto);
    }

    /**
    * 수당되물림 예상액 - 판매리스트 내역 목록 엑셀다운로드
    * @param dto
    * @return SXSSFWorkbook
    */
    public List<SearchRedfIzPerfPsRes> getAwRedfIzPerfPsAmtForExcelDownload(SearchRedfIzReq dto) {
        return mapper.selectAwRedfIzPerfPsAmt(dto);
    }

    /**
    * 수당되물림 예상액 - 되물림 예상액 목록 조회 전 삭제 및 저장
    * @param dto
    * @return SaveResponse
    */
    public int saveAwRedfEtAmt(List<SaveReq> dtos) {

        int processCount = 0;

        /* 예상액 조회 전 temp 테이블 데이터 모두 삭제 */
        processCount += mapper.deleteRedfEtAmtIz();
        processCount += mapper.deleteRedfEtCntrBas();

        /* temp 데이터 모두 삭제처리 후, 체크한 데이터로 새로 insert */
        for (SaveReq dto : dtos) {
            WdeaAwRedfEtAmtDvo dvo = converter.mapSaveReq(dto);

            /* 되물림예상금액내역(temp) 등록 */
            processCount += mapper.insertRedfEtAmtIz(dvo);
            /* 되물림예상계약기본(temp) 등록 */
            processCount += mapper.insertRedfEtCntrBas(dvo);

        }

        return processCount;
    }

    /**
    * 수당되물림 예상액 - 되물림 예상액 목록 조회
    * @param dto
    * @return PagingResult<SearchRedfEtRes>
    */
    public List<SearchRedfEtRes> getAwRedfEtAmt() {
        return mapper.selectAwRedfEtAmts();
    }

    /**
    * 수당되물림 예상액 - 되물림 예상액 목록 엑셀다운로드
    * @param dto
    * @return SXSSFWorkbook
    */
    public List<SearchRedfEtRes> getAwRedfEtAmtForExcelDownload() {
        return mapper.selectAwRedfEtAmts();
    }
}
