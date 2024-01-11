package com.kyowon.sms.wells.web.service.common.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyPaidAsCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyPaidAsCostMgtMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0159M01 유상A/S 서비스비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-03-08
 */
@Service
@RequiredArgsConstructor
public class WsnyPaidAsCostMgtService {
    private final WsnyPaidAsCostMgtMapper mapper;
    private final WsnyPaidAsCostMgtConverter converter;

    /**
     * 유상 A/S 서비스 비용 조회
     */
    public List<SearchRes> getPaidAsCostMgts(SearchReq dto) {
        return mapper.selectPaidAsCostMgts(dto);
    }

    /**
     * 유상 A/S 서비스 비용 수정
     */
    @Transactional
    public int savePaidAsCostMgts(List<SaveReq> dtos) {
        int proccessCount = 0;
        for (SaveReq dto : dtos) {
            WsnyPaidAsCostMgtDvo dvo = converter.mapSaveReqToWsnyPaidAsCostMgtDvo(dto);

            // 변경 전 적용시작일자
            String orgApyStrtdt = dvo.getOrgApyStrtdt();
            // 적용시작일자
            String apyStrtdt = dvo.getApyStrtdt();

            // 신규 데이터 생성
            if (StringUtils.isEmpty(orgApyStrtdt) || !orgApyStrtdt.equals(apyStrtdt)) {
                proccessCount += mapper.insertPaidAsCostMgts(dvo);
                BigDecimal izSn = dvo.getIzSn();
                if (izSn != null) {
                    // 이전 데이터 적용종료일자 변경
                    this.mapper.updateApyStrtdt(dvo);
                }
            } else {
                proccessCount += mapper.updatePaidAsCostMgts(dvo);
            }

        }
        return proccessCount;
    }

}
