package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccSalesBondConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.*;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesBondDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesBondMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 매출채권/선수금 현황 - 연체가산금 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-08-23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdccSalesBondService {

    private final WdccSalesBondMapper mapper;
    private final WdccSalesBondConverter converter;
    private final DateTimeFormatter formatterType = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * 매출채권/선수금 현황 매출채권 조회
     * @param req 검색조건
     * @return 검색결과
     */
    public List<SearchRes> getSalesBondAggregate(
        SearchReq req
    ) {
        Map<String, Object> param = new HashMap<>();
        param.put("slClYm", req.slClYm());
        param.put("slClEYm", req.slClYm());
        param.put("agrgDv", req.agrgDv());
        param.put("sellTpCd", req.sellTpCd());
        param.put("sellTpDtlCd", req.sellTpDtlCd());
        param.put("sellChnlDtl", req.sellChnlDtl());
        param.put("cntrNo", req.cntrNo());
        param.put("cntrSn", req.cntrSn());
        param.put("sapPdDvCd", req.sapPdDvCd());

        LocalDate slClEYmDate = getSlClYmForLocalDate(req.slClYm());

        List<WdccSalesBondDvo> dvos;
        if (StringUtils.equals(req.agrgDv(), "2")) { // 일자별인 경우 데이터를 조합 해야함
            // 화면에 선택한 월의 -2개월
            LocalDate slClSYmDate = slClEYmDate.minusMonths(2);
            param.put("slClSYm", slClSYmDate.format(formatterType));
            param.put("agrgDv", "1"); // 일자별의 경우 조회 시에는 1(집계)로 변경
            dvos = mapper.selectAccountsReceivableByDate(param); // 일자별 쿼리 조회 결과
        } else {
            // 화면에 선택한 월의 -1개월
            LocalDate slClSYmDate = slClEYmDate.minusMonths(1);
            param.put("slClSYm", slClSYmDate.format(formatterType));
            dvos = getAccountsReceivable(param);
        }

        return converter.mapAllWdccSalesBondDvoToSearchRes(dvos);
    }

    /**
     * 날짜 변경을 위한 LocalDate 변환 메소드
     * @param slClYm 기준년월
     * @return LocalDate
     */
    public LocalDate getSlClYmForLocalDate(String slClYm) {
        Matcher matcher = Pattern.compile("^(\\d{4})(\\d{2})").matcher(slClYm);
        if (matcher.matches()) {
            return LocalDate
                .of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 1);
        }
        return null;
    }

    /**
     * 매출채권/선수금 현황 매출채권 조회(일자별이외)
     * @param param 검색조건
     * @return 검색결과
     */
    public List<WdccSalesBondDvo> getAccountsReceivable(Map<String, Object> param) {
        // selectAccountsReceivableRental 렌탈
        // selectAccountsReceivableLease 리스
        // selectAccountsReceivableMembership 맴버십
        // selectLumpSumOfAccountsReceivable 일시불
        // selectTradeReceivablesPeriodicDelivery 정기배송
        if (StringUtils.equals(param.get("sellTpCd").toString(), "1")) { //일시불
            return mapper.selectLumpSumOfAccountsReceivable(param);
        } else if (StringUtils.equals(param.get("sellTpCd").toString(), "2")) { //렌탈
            return mapper.selectAccountsReceivableRental(param);
        } else if (StringUtils.equals(param.get("sellTpCd").toString(), "3")) { //멤버십
            return mapper.selectAccountsReceivableMembership(param);
        } else if (StringUtils.equals(param.get("sellTpCd").toString(), "6")) { //정기배송
            return mapper.selectTradeReceivablesPeriodicDelivery(param);
        } else if (StringUtils.equals(param.get("sellTpCd").toString(), "10")) { //리스/할부
            return mapper.selectAccountsReceivableLease(param);
        }
        return new ArrayList<>();
    }
}
