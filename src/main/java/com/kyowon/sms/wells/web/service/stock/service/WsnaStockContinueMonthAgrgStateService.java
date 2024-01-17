package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockContinueMonthAgrgStateConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthAgrgStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockContinueMonthAgrgStateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockContinueMonthAgrgStateWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockContinueMonthAgrgStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaStockContinueMonthAgrgStateService {
    private final WsnaStockContinueMonthAgrgStateMapper mapper;
    private final WsnaStockContinueMonthAgrgStateConverter converter;

    public List<HashMap<String, String>> getStockContinueMonthAgrgState(SearchReq dto) {

        WsnaStockContinueMonthAgrgStateDvo dvo = convertPivotStockContinueMonthAgrgStateDvo(dto);

        return mapper.selectStockContinueMonthAgrgState(dvo);
    }

    // Header용 창고 조회
    public List<WsnaStockContinueMonthAgrgStateWareDvo> getWareHouses(String baseYm) {

        return mapper.selectMcByWares(baseYm);
    }

    // 창고 조회
    public WsnaStockContinueMonthAgrgStateDvo convertPivotStockContinueMonthAgrgStateDvo(
        SearchReq dto
    ) {
        String baseYm = dto.baseYm();
        List<WsnaStockContinueMonthAgrgStateWareDvo> wares = getWareHouses(baseYm);

        WsnaStockContinueMonthAgrgStateDvo dvo = converter.mapSearchReqToWsnaStockContinueMonthAgrgStateDvo(dto);
        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        String wareNoPitmFields = wares.stream()
            .map(
                item -> "NVL(T1.WARE_" + item.getWareNo() + "_PITM,0) AS WARE_" + item.getWareNo() + "_PITM_STOC_GD_QTY"
            )
            .collect(Collectors.joining(","));

        // PIVOT 재고지속월
        String wareNoKeppMmFields = wares.stream()
            .map(item -> "NVL(T1.WARE_" + item.getWareNo() + "_KEPP,0) AS WARE_" + item.getWareNo() + "_KEPP_MM")
            .collect(Collectors.joining(","));
        String wareNoPitmSumFields = wares.stream()
            .map(item -> " NVL(T1.WARE_" + item.getWareNo() + "_PITM,0) ")
            .collect(Collectors.joining("+")); //영업센터 합계

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoPitmFields(wareNoPitmFields);
        dvo.setWareNoKeppMmFields(wareNoKeppMmFields);
        dvo.setWareNoPitmSumFields(wareNoPitmSumFields);

        dvo.setItmPdCd(dto.itmPdCd());
        dvo.setStrtSapCd(dto.strtSapCd());
        dvo.setEndSapCd(dto.endSapCd());

        return dvo;
    }

}
