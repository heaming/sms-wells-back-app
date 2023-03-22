package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractDtlStatCdChMapper;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbContractDtlStatCdChService {

    private final CodeService codeService;
    private final WctzHistoryService historyService;
    private final WctbContractDtlStatCdChMapper mapper;

    /**
     * 계약상세 상태코드 및 종료일자 변경
     * - 계약상세상태변경 이력 및 계약상세변경이력 생성 포함
     * @programId W-SS-S-0083
     * @param   dvo cntrNo 계약번호(필수)
     *          dvo.cntrSn 계약일련번호(필수)
     *          dvo.cntrDtlStatCd 변경할 계약상세상태코드(필수)
     *          dvo.cntrPdEnddt 계약상품종료일자
     * @return  Y/N 성공여부
     */

    public String editContractDtlStatCdCh(WctbContractDtlStatCdChDvo dvo) throws Exception {
        // 0. check validation
        ValidAssert.hasText(dvo.getCntrNo());
        ValidAssert.hasText(dvo.getCntrSn());
        ValidAssert.hasText(dvo.getCntrDtlStatCd());

        if(!codeService.isExistCodeDetail("CNTR_DTL_STAT_CD", dvo.getCntrDtlStatCd())) {
            throw new BizException("계약상세상태코드가 존재하지 않는 값입니다.");
        }

        if(StringUtil.isNotEmpty(dvo.getCntrPdEnddt()) && !DateUtil.isValid(dvo.getCntrPdEnddt(), "yyyyMMdd")){
            throw new BizException("계약상품종료일자가 날짜 포맷이 아닙니다.");
        }

        // 1. 계약상세상태코드 update
        int result = mapper.updateContractDtlStatCd(dvo);
        if(result <= 0){
            return "N";
        }

        // 2. 계약상세상태변경이력 insert
        String now = DateUtil.todayNnow();
        historyService.createContractDetailStatChangeHistory(
            WctzCntrDtlStatChangeHistDvo.builder()
                .cntrNo(dvo.getCntrNo())
                .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                .histStrtDtm(now)
                .build()
        );

        // 3. 계약상세변경이력 insert
        historyService.createContractDetailChangeHistory(
            WctzCntrDetailChangeHistDvo.builder()
                .cntrNo(dvo.getCntrNo())
                .histStrtDtm(now)
                .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                .build()
        );

        return "Y";
    }
}
