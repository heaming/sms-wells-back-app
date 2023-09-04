package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMonthlyOutOfStorageAgrgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMonthlyOutOfStorageAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaMonthlyOutOfStorageAgrgService {

    private final WsnaMonthlyOutOfStorageAgrgMapper mapper;

    private final WsnaMonthlyOutOfStorageAgrgConverter converter;

    public List<HashMap<String, String>> getMonthlyOutOfStorageAgrgs(SearchReq dto) {
        WsnaMonthlyOutOfStorageAgrgDvo dvo = convertPivotMonthlyOutOfStorageAgrgDvo(
            converter.mapSearchReqToWsnaMonthlyOutOfStorageAgrgDvo(dto)
        );
        return mapper.selectMonthlyOutOfStorageAgrgs(dvo);
    }

    public WsnaMonthlyOutOfStorageAgrgDvo convertPivotMonthlyOutOfStorageAgrgDvo(WsnaMonthlyOutOfStorageAgrgDvo dvo) {

        List<String> monthlys = mapper.selectMonthlys(dvo);

        String fnlVstFshYmStr = monthlys.stream()
            .map(
                item -> "'" + item + "' AS MONTHLY_"
                    + ("1".equals(item.substring(4, 5)) ? item.substring(4, 6) : item.substring(5, 6))
            )
            .collect(Collectors.joining(","));
        dvo.setFnlVstFshYmStr(fnlVstFshYmStr);

        return dvo;

    }
}
