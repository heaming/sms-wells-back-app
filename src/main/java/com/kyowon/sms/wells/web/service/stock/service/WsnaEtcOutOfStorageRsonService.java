package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEtcOutOfStorageRsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

/**
 *
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
     * @param dto : {
     * stOstrDt : 시작출고일자,
     * edOstrDt : 종료출고일자,
     * bilRsonCd : 청구사유코드,
     * pdGdCd : 등급코드,
     * itmKndCd : 품목구분코드,
     * startItemCd : 시작품목코드,
     * endItemCd : 종료품목코드,
     * ostrWareNo : 서비스센터 }
     * @return 조회결과
     */
    public List<SearchRes> getEtcOutOfStorageRsons(SearchReq dto) {
        return this.mapper.selectEtcOutOfStorageRsons(dto);
    }

    /**
     * 기타출고 사유내역 - 조회
     * @param dto : {stOstrDt : 시작출고일자}
     * @return 조회결과
     */
    public List<CenterRes> getServiceCenters(SearchReq dto) {
        return this.mapper.selectServiceCenters(dto);
    }

    /**
     * 기타출고 사유내역 - 조회
     * @param dto : {}
     * @return 조회결과
     */
    public List<BusinessRes> getBusinessCenter(SearchReq dto) {
        return this.mapper.selectBusinessCenter(dto);
    }

    /**
     * 기타출고 사유내역 (영업센터)- 조회
     * @param dto : {
     * stOstrDt : 시작출고일자,
     * edOstrDt : 종료출고일자,
     * bilRsonCd : 청구사유코드,
     * pdGdCd : 등급코드,
     * itmKndCd : 품목구분코드,
     * startItemCd : 시작품목코드,
     * endItemCd : 종료품목코드,
     * ostrWareNo : 서비스센터 }
     * @return 조회결과
     */
    public List<SearchRes> getEtcOutOfStorageRsonBusiness(SearchReq dto) {
        return this.mapper.selectEtcOutOfStorageRsonBusiness(dto);
    }
}
