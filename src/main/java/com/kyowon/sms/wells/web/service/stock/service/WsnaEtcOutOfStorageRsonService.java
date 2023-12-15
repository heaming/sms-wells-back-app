package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEtcOutOfStorageRsonMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songtaesung
 * @since 2023.01.13
 */
@Service
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageRsonService {

    private final WsnaEtcOutOfStorageRsonMapper mapper;

    /**
     * 기타출고 사유내역 - 조회
     *
     * @param dto : {
     *            stOstrDt : 시작출고일자,
     *            edOstrDt : 종료출고일자,
     *            bilRsonCd : 청구사유코드,
     *            pdGdCd : 등급코드,
     *            itmKndCd : 품목구분코드,
     *            startItemCd : 시작품목코드,
     *            endItemCd : 종료품목코드,
     *            ostrWareNo : 서비스센터 }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getEtcOutOfStorageRsons(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectEtcOutOfStorageRsons(dto, pageInfo);
    }

    /**
     * 기타출고 사유내역 - 엑셀다운로드
     *
     * @param dto : {
     *            stOstrDt : 시작출고일자,
     *            edOstrDt : 종료출고일자,
     *            bilRsonCd : 청구사유코드,
     *            pdGdCd : 등급코드,
     *            itmKndCd : 품목구분코드,
     *            startItemCd : 시작품목코드,
     *            endItemCd : 종료품목코드,
     *            ostrWareNo : 서비스센터 }
     * @return 조회결과
     */
    public List<SearchRes> getEtcOutOfStorageRsonsForExcelDownload(SearchReq dto) {
        return this.mapper.selectEtcOutOfStorageRsons(dto);
    }

}
