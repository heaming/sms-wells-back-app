package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaOutOfStorageAgrgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.FindItemRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgWareDvo;
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
    * 창고 조회 (화면 Header 설정)
    */
    public List<WsnaOutOfStorageAgrgWareDvo> getWareHouses() {
        WsnaOutOfStorageAgrgWareDvo wareDvo = new WsnaOutOfStorageAgrgWareDvo();
        wareDvo.setSumFields(true); //합계필드 포함
        return mapper.selectMcByWares(wareDvo);
    }

    public List<HashMap<String, String>> getOutOfStorageAgrgs(SearchReq dto) {
        WsnaOutOfStorageAgrgDvo dvo = convertPivotOutOfStorageAgrgDvo(
            converter.mapSearchReqToWsnaOutOfStorageAgrgDvo(dto)
        );
        return mapper.selectOutOfStorageAgrgs(dvo);
    }

    /**
    * 창고 조회
    */
    public WsnaOutOfStorageAgrgDvo convertPivotOutOfStorageAgrgDvo(WsnaOutOfStorageAgrgDvo dvo) {
        // 창고 리스트 조회
        WsnaOutOfStorageAgrgWareDvo wareDvo = new WsnaOutOfStorageAgrgWareDvo();
        wareDvo.setSumFields(false); //합계필드 제외
        List<WsnaOutOfStorageAgrgWareDvo> wares = this.mapper.selectMcByWares(wareDvo);

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "T1.WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        // PIVOT 창고 합계 필드
        String wareLogisticsFieldsSumStr = wares.stream().filter(item -> StringUtils.startsWith(item.getWareNo(), "1"))
            .map(item -> " NVL(WARE_" + item.getWareNo() + ",0) ")
            .collect(Collectors.joining("+")); //물류센터 합계

        String wareServiceFieldsSumStr = wares.stream().filter(item -> StringUtils.startsWith(item.getWareNo(), "2"))
            .map(item -> " NVL(WARE_" + item.getWareNo() + ",0) ")
            .collect(Collectors.joining("+")); //서비스센터 합계

        String wareBusinessFieldsSumStr = wares.stream().filter(item -> StringUtils.startsWith(item.getWareNo(), "3"))
            .map(item -> " NVL(WARE_" + item.getWareNo() + ",0) ")
            .collect(Collectors.joining("+")); //영업센터 합계

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);
        dvo.setWareLogisticsFieldsSumStr(wareLogisticsFieldsSumStr);
        dvo.setWareServiceFieldsSumStr(wareServiceFieldsSumStr);
        dvo.setWareBusinessFieldsSumStr(wareBusinessFieldsSumStr);
        return dvo;

    }
}
