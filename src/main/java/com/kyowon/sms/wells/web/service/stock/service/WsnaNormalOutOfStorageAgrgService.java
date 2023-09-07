package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNormalOutOfStorageAgrgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.FindWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNormalOutOfStorageAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaNormalOutOfStorageAgrgService {
    private final WsnaNormalOutOfStorageAgrgMapper mapper;

    private final WsnaNormalOutOfStorageAgrgConverter converter;

    public List<FindWarehouseRes> getLoginWarehouses(SearchReq dto) {
        return mapper.selectLoginWarehouses(dto);
    }

    public List<WsnaOutOfStorageAgrgWareDvo> getWareHouses(SearchReq dto) {
        return mapper.selectWareHouses(dto);
    }

    public List<HashMap<String, String>> getNormalOutOfStorageAgrgs(SearchReq dto) {
        return convertPivotOutOfStorageAgrgMap(dto);
    }

    public List<HashMap<String, String>> convertPivotOutOfStorageAgrgMap(SearchReq dto) {
        // 창고 리스트 조회
        List<WsnaOutOfStorageAgrgWareDvo> wares = this.mapper.selectWareHouses(dto);

        WsnaNormalOutOfStorageAgrgDvo dvo = converter.mapSearchReqToWsnaNormalOutOfStorageAgrgDvo(dto);
        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "WARE_" + item.getWareNo())
            .collect(Collectors.joining(","));

        //피벗된 창고 (합계)
        String wareNoFieldsSumStr = wares.stream().map(item -> " NVL(WARE_" + item.getWareNo() + ",0) ")
            .collect(Collectors.joining("+"));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);
        dvo.setWareNoFieldsSumStr(wareNoFieldsSumStr);

        return mapper.selectNormalOutOfStorageAgrgs(dvo);
    }
}
