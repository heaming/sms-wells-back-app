package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInAggregateDto.SearchReq;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaInAggregateConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaInAggregateMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
@Service
@RequiredArgsConstructor
public class WsnaInAggregateService {

    private final WsnaInAggregateMapper mapper;
    private final WsnaInAggregateConverter converter;

    public List<WsnaInAggregateWareDvo> getWareHouses(String baseDateFrom, String wareDvCd) {
        WsnaInAggregateWareDvo wareDvo = new WsnaInAggregateWareDvo();
        wareDvo.setBaseDateFrom(baseDateFrom);
        wareDvo.setWareDvCd(wareDvCd);
        wareDvo.setSumFields(true); //합계필드 포함
        return mapper.selectMcByWares(wareDvo);
    }

    public List<HashMap<String, String>> getInAggregate(SearchReq dto) {
        WsnaInAggregateDvo dvo = convertPivotInOfStorageAgrgDvo(
            converter.mapSearchResDvo(dto)
        );
        return mapper.selectInAggregateList(dvo);
    }

    public WsnaInAggregateDvo convertPivotInOfStorageAgrgDvo(WsnaInAggregateDvo dvo) {
        // 창고 리스트 조회
        WsnaInAggregateWareDvo wareDvo = new WsnaInAggregateWareDvo();
        wareDvo.setBaseDateFrom(dvo.getBaseDateFrom());
        wareDvo.setWareDvCd(dvo.getWareDvCd());
        wareDvo.setSumFields(false); //합계필드 제외
        List<WsnaInAggregateWareDvo> wares = this.mapper.selectMcByWares(wareDvo);

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
