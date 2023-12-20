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
            int adsbChk = 0;

            // TODO: 수수로 측 서비스 개발 전, 재지급 중복 체크용 SELECT. 추후 로직 확인 후, 삭제 예정
            //            int dupCheck = mapper.selectAdsbDupCheck(dto);
            // TODO: 수수로 측 서비스 개발 전, 재지급 중복 체크용 SELECT. 추후 로직 확인 후, 삭제 예정
            //            BizAssert.isTrue(dupCheck == 0, "MSG_TXT_BF_CNFM_CONF_ADSB"); // 이미 확정되어 재지급 생성이 불가합니다.

            // 확정된 데이터가 있으면, return
            BizAssert.isTrue(result == 0, "MSG_TXT_BF_CNFM_CONF_ADSB"); // 이미 확정되어 재지급 생성이 불가합니다.

            adsbChk += mapper.selectAdsbObjectCreates(req);

            // 대상이 있으면 삭제 후 insert, 없으면 return
            /* 재지급 대상 생성 */
            /* 임시저장 데이터 삭제 */
            if (adsbChk > 0) {
                processCount += mapper.deleteAdsbObjectTemp(req);
                processCount += mapper.insertAdsbObjectCreates(req); // 재지급 대상 생성

                // TODO: 수수로 측 서비스 개발 전, 테스트용 UPDATE. 추후 개발 시, 삭제 예정
                //                mapper.updateAdsbObjectTemp(req);
            }

            if (adsbChk > 0) {
                // TODO: 수수료 측 서비스 call 예정 ( 금액 생성 )
                if ("W01".equals(req.getOgTpCd())) {
                    req.setCntrPerfCrtDvCd("08"); // P추진단 재지급
                } else if ("W02".equals(req.getOgTpCd())) {
                    req.setCntrPerfCrtDvCd("09"); // M추진단 재지급
                } else if ("W03".equals(req.getOgTpCd())) {
                    req.setCntrPerfCrtDvCd("10"); // 홈마스터 재지급
                } else if ("W04".equals(req.getOgTpCd())) {
                    req.setCntrPerfCrtDvCd("12"); // B2B 재지급
                } else if ("W05".equals(req.getOgTpCd())) {
                    req.setCntrPerfCrtDvCd("11"); // 총판 재지급
                }
                feeService.saveAgainDisbursementOfFees(req.getBaseYm(), req.getCntrPerfCrtDvCd());
            }

        }
        return processCount;
    }
}
