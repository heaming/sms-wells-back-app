package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaOutOfStorageAgrgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.FindItemRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaOutOfStorageAgrgService {
    private final WsnaOutOfStorageAgrgMapper mapper;

    private final WsnaOutOfStorageAgrgConverter converter;

    public List<FindItemRes> getItemProductCodes(
        SearchReq dto
    ) {
        return this.mapper.selectItemProductCodes(dto);
    }

    /**
    * 창고 조회 (PIVOT Header)
    */
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses() {
        return mapper.selectMcByWares();
    }

    public List<HashMap<String, String>> getOutOfStorageAgrgs(SearchReq dto) {

        WsnaOutOfStorageAgrgDvo dvo = convertPivotOutOfStorageAgrgDvo(
            converter.mapSearchReqToWsnaOutOfStorageAgrgDvo(dto)
        );
        return mapper.selectOutOfStorageAgrgs(dvo);
    }

    public List<HashMap<String, String>> getOutOfStorageAgrgExcelDownload(SearchReq dto) {
        WsnaOutOfStorageAgrgDvo dvo = convertPivotOutOfStorageAgrgDvo(
            converter.mapSearchReqToWsnaOutOfStorageAgrgDvo(dto)
        );
        return mapper.selectOutOfStorageAgrgs(dvo);
    }

    /**
    * 창고 조회 (PIVOT Field)
    */
    public WsnaOutOfStorageAgrgDvo convertPivotOutOfStorageAgrgDvo(WsnaOutOfStorageAgrgDvo dvo) {
        // 창고 리스트 조회
        List<WsnzWellsCodeWareHouseDvo> wares = this.mapper.selectMcByWares();

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));
        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "T1.WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);
        return dvo;

    }
}
