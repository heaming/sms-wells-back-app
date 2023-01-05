package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.WarehouseReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAskMngtMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.25
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageAskMngtService {

    private final WsnaOutOfStorageAskMngtMapper mapper;

    /**
     * 출고요청 관리 - 조회
     * @param dto : { strOjWareNo: 출고요청창고, ostrAkTpCd: 출고요청유형코드, startStrHopDt: 입고희망일자 시작일, endStrHopDt: 입고희망일자 종료일, wareDvCd: 출고요청 접수창고, wareLocaraCd: 창고지역코드 }
     * @return 조회결과
     */
    public List<SearchRes> getOutOfStorageAsks(SearchReq dto) {
        return this.mapper.selectOutOfStorageAsks(dto);
    }

    public List<Warehouse> getOutOfStorageAskWares(WarehouseReq dto) {
        return this.mapper.selectWarehouses(dto);
    }

    /**
     * 물류창고 리스트 조회
     * @return 조회결과
     */

}
