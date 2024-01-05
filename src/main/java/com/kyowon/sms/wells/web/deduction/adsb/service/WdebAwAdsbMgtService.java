package com.kyowon.sms.wells.web.deduction.adsb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.deduction.adsb.converter.WdebAwAdsbMgtConverter;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAwAdsbMgtDto.CreateReq;
import com.kyowon.sms.wells.web.deduction.adsb.dvo.WdebAwAdsbMgtDvo;
import com.kyowon.sms.wells.web.deduction.adsb.mapper.WdebAwAdsbMgtMapper;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebAgainDisbursementFeeService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdebAwAdsbMgtService {

    private final WdebAwAdsbMgtMapper mapper;
    private final WdebAwAdsbMgtConverter converter;
    private final WfebAgainDisbursementFeeService feeService;

    /**
     * 재지급 대상 생성
     * @param dto
     * @return
     */
    @Transactional
    public int saveAdsbObjectCreates(CreateReq dto) {
        int processCount = 0;

        WdebAwAdsbMgtDvo req = converter.mapCreateReq(dto);

        // 상조인 경우, 인터페이스 테이블 업데이트
        if ("Y".equals(req.getLifeYn())) {
            /*재지급의 경우, WELLS만 존재하는 이유는 EDU는 연체가 없고 취소만 존재하기에
            * 취소는 재지급이 없어서 WELLS만 존재
            * 그러한 이유로 상조 재지급은 WELLS만 처리하기 때문에 EDU, WELLS 분기처리 안함
            * */
            processCount += mapper.updateLifeAdsbObjectWells(req); // WELLS 상조 재지급 대상 생성

        } else {

            /* 재지급 대상 생성 전, 확정여부 확인 validation */
            int result = mapper.selectAdsbObjecConfirmCheck(dto);
            int adsbChkW02 = 0;
            int adsbChkW05 = 0;
            int adsbChkW04 = 0;

            // 확정된 데이터가 있으면, return
            BizAssert.isTrue(result == 0, "MSG_TXT_BF_CNFM_CONF_ADSB"); // 이미 확정되어 재지급 생성이 불가합니다.

            if ("W02".equals(req.getOgTpCd())) {
                adsbChkW02 += mapper.selectAdsbObjectCreates(req);
            }
            if ("W05".equals(req.getOgTpCd())) {
                adsbChkW05 += mapper.selectChongAdsbObjectCreates(req);
            }
            if ("W04".equals(req.getOgTpCd())) {
                adsbChkW04 += mapper.selectB2BAdsbObjectCreates(req);
            }

            // 대상이 있으면 삭제 후 insert, 없으면 return
            /* 재지급 대상 생성 */
            /* 임시저장 데이터 삭제 */
            if (adsbChkW02 > 0) {
                mapper.deleteAdsbObjectTemp(req);
                processCount += mapper.insertAdsbObjectCreates(req); // 재지급 대상 생성 - WELLS-M
            }
            if (adsbChkW05 > 0) {
                mapper.deleteAdsbObjectTemp(req);
                processCount += mapper.insertChongAdsbObjectCreates(req); // 재지급 대상 생성 - WELLS-총판
            }
            if (adsbChkW04 > 0) {
                mapper.deleteAdsbObjectTemp(req);
                processCount += mapper.insertB2BAdsbObjectCreates(req); // 재지급 대상 생성 - WELLS-B2B
            }

            // 대상 생성이 된 경우에만 재지급 금액 생성 서비스 call
            if (processCount != 0) {
                if (adsbChkW02 > 0) {
                    req.setCntrPerfCrtDvCd("09"); // M추진단 재지급
                    feeService.saveAgainDisbursementOfFees(req.getBaseYm(), req.getCntrPerfCrtDvCd());
                }

                if (adsbChkW04 > 0) {
                    req.setCntrPerfCrtDvCd("12"); // B2B 재지급
                    feeService.saveAgainDisbursementOfFees(req.getBaseYm(), req.getCntrPerfCrtDvCd());
                }

                if (adsbChkW05 > 0) {
                    req.setCntrPerfCrtDvCd("11"); // 총판 재지급
                    feeService.saveAgainDisbursementOfFees(req.getBaseYm(), req.getCntrPerfCrtDvCd());
                }
            }
        }
        return processCount;
    }
}
