package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockStateIndividualWareConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStateIndividualWareDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockStateIndividualWareMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaStockStateIndividualWareService {
    private final WsnaStockStateIndividualWareMapper mapper;
    private final WsnaStockStateIndividualWareConverter converter;

    public List<HashMap<String, String>> getStockStateIndividualWare(SearchReq dto) {

        WsnaStockStateIndividualWareDvo dvo = convertPivotWsnaStockStateIndividualWareDvo(dto);

        return mapper.selectStockStateIndividualWare(dvo);
    }

    // Header용 창고 조회
    public List<WsnaStockStateIndividualWareWareDvo> getWareHouses(SearchReq dto) {

        return mapper.selectMcByWares(dto);
    }

    public List<HashMap<String, String>> getServiceCenter(String baseYm) {
        return mapper.selectServiceCenter(baseYm);
    }

    public String getMyServiceCenter(String baseYm) {
        return mapper.selectMyServiceCenter(baseYm);
    }

    // PITVOT 조회
    public WsnaStockStateIndividualWareDvo convertPivotWsnaStockStateIndividualWareDvo(
        SearchReq dto
    ) {
        List<WsnaStockStateIndividualWareWareDvo> wares = getWareHouses(dto);

        WsnaStockStateIndividualWareDvo dvo = converter.mapSearchReqToWsnaStockStateIndividualWareDvo(dto);
        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        String wareNoAgrgQtyFields = wares.stream()
            .map(
                item -> "NVL(WARE_" + item.getWareNo() + "_AGRG_QTY,0) AS WARE_" + item.getWareNo()
                    + "_AGRG_QTY"
            )
            .collect(Collectors.joining(","));

        // 서비스센터 합계
        String wareNoAgrgQtySumFields = wares.stream()
            .map(item -> " NVL(WARE_" + item.getWareNo() + "_AGRG_QTY,0) ")
            .collect(Collectors.joining("+"));

        // 서비스센터 개인조직창고 NULL 예외 처리
        if (StringUtils.isEmpty(wareNoInStr)) {
            dvo.setWareNoInStr("''");
        } else {
            dvo.setWareNoInStr(wareNoInStr);
        }
        if (StringUtils.isEmpty(wareNoInStr)) {
            dvo.setWareNoAgrgQtyFields("''");
        } else {
            dvo.setWareNoAgrgQtyFields(wareNoAgrgQtyFields);
        }
        if (StringUtils.isEmpty(wareNoInStr)) {
            dvo.setWareNoAgrgQtySumFields("''");
        } else {
            dvo.setWareNoAgrgQtySumFields(wareNoAgrgQtySumFields);
        }
        dvo.setStockDt(dto.stockDt());
        dvo.setBaseYm(dto.baseYm());
        dvo.setWareNo(dto.wareNo());
        dvo.setWareUseYn(dto.wareUseYn());
        dvo.setItmKndCd(dto.itmKndCd());
        dvo.setItmGdCd(dto.itmGdCd());
        dvo.setUseYn(dto.useYn());
        dvo.setStndUnuseYn(dto.stndUnuseYn());

        return dvo;
    }

}
