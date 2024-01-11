package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyPaidAsCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyPaidAsCostMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

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
            if (!orgApyStrtdt.equals(apyStrtdt)) {
                String useMatPdCd = dvo.getUseMatPdCd();
                // 적용일자 유효성 체크
                String validDt = this.mapper.selectMatPdCdValidCheck(dvo);
                // 해당 적용일자에 등록된 데이터가 있습니다. [품목코드 : {0}]
                BizAssert.isNull(validDt, "MSG_ALT_APY_DT_IS_EXISTS", new String[] {useMatPdCd});

                proccessCount += mapper.insertPaidAsCostMgts(dvo);
                // 이전 데이터 적용종료일자 변경
                this.mapper.updateApyStrtdt(dvo);
            } else {
                proccessCount += mapper.updatePaidAsCostMgts(dvo);
            }

        }
        return proccessCount;
    }

}
