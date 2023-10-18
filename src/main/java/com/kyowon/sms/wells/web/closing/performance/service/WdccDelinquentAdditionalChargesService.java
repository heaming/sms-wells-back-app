package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccDelinquentAdditionalChargesConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccDelinquentAdditionalChargesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 매출채권/선수금 현황 - 연체가산금 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-08-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdccDelinquentAdditionalChargesService {
    private final WdccDelinquentAdditionalChargesConverter converter;
    private final WdccDelinquentAdditionalChargesMapper mapper;

    /**
     * 매출채권/선수금 현황 - 연체가산금 조회
     * @param req 검색조건
     * @return 검색목록
     */
    public List<SearchRes> getDelinquentAdditionalCharges(
        SearchReq req
    ) {
        Map<String, Object> param = new HashMap<>();
        param.put("slClYm", req.slClYm());
        param.put("agrgDv", req.agrgDv());
        param.put("sellTpCd", req.sellTpCd());
        param.put("sellTpDtlCd", req.sellTpDtlCd());
        param.put("sapPdDvCd", req.sapPdDvCd());
        param.put("rentalYn", "Y");
        if (StringUtils.equals(req.sellTpCd(), "10")) {
            param.put("sellTpCd", "2"); // 리스/할부의 경우 렌탈로 조회
            param.put("rentalYn", "N"); // 리스/할부의 경우 ('22', '24', '25', '26')
        }
        return converter
            .mapAllWdccDelinquentAdditionalChargesDvoToSearchRes(mapper.selectDelinquentAdditionalCharges(param));
    }
}
