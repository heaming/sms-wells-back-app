package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMovementStoreMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto.*;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0109M01 이동입고현황
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.13
 */
@Service
@RequiredArgsConstructor
public class WsnaMovementStoreService {

    private final WsnaMovementStoreMapper mapper;

    /**
     * 이동입고현황 조회
     *
     * @param dto : {
     *            stStrDt : 입고시작일자
     *            edStrDt : 입고종료일자
     *            strTpCd : 입고유형코드
     *            ostrWareDvCd : 출고창고구분코드
     *            ostrWareNoD : 출고창고디테일번호
     *            ostrWareNoM : 출고창고마스터번호
     *            }
     *
     * @return 조회결과
     */
    public List<SearchRes> getMovementStores(SearchReq dto) {
        return mapper.selectMovementStores(dto);
    }

    /**
     * 이동입고현황 엑셀다운로드
     *
     * @param dto : {
     *            stStrDt : 입고시작일자
     *            edStrDt : 입고종료일자
     *            strTpCd : 입고유형코드
     *            ostrWareDvCd : 출고창고구분코드
     *            ostrWareNoD : 출고창고디테일번호
     *            ostrWareNoM : 출고창고마스터번호
     *            }
     *
     * @return 조회결과
     */
    public List<SearchRes> getMovementStoresExcelDownload(SearchReq dto) {
        return mapper.selectMovementStores(dto);
    }
}
