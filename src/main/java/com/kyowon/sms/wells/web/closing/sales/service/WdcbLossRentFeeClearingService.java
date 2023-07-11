package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.converter.WdcbLossRentFeeClearingConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbLossRentFeeClearingDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbLossRentFeeClearingDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbLossRentFeeClearingMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 위약금 손료 초기화 서비스(W-CL-S-0012)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-06-23
 */
@Service
@RequiredArgsConstructor
public class WdcbLossRentFeeClearingService {
    private final WdcbLossRentFeeClearingConverter converter;
    private final WdcbLossRentFeeClearingMapper mapper;

    /**
     * WELLS 상품 철거 발생 시, 기 산출된 위약금 중 분실손료 금액을 초기화하고, 위약금 잔액(분실손료만큼 잔액을 수정)을 수정 관리한다.
     * 철거에 대한 처리의 시작은 서비스 영역에서 Event 처리되며, 해당 서비스는 서비스 영역에서 호출된다.                             
     * @param cntrNo        계약번호
     * @param cntrSn        계약일련번호
     * @param reqdDt        철거일자
     * @return WdcbLossRentFeeClearingDto
     * @throws BizException SQL 오류 발생 시 Exception 처리
     */
    @Transactional
    public int editLossRentFeeClearing(SaveReq dto) throws BizException {
        WdcbLossRentFeeClearingDvo inputDvo = converter.mapSaveReqToWdcbLossRentFeeClearingDvo(dto);

        int lsRntf = mapper.selectLossRentFee(inputDvo);

        int processCount = 0;
        int result;
        if (lsRntf > 0) {
            result = mapper.insertLossRentFee(inputDvo); /*WELLS위약금액기본이력 정보 생성*/
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            mapper.updateLossRentFee(inputDvo); /*WELLS위약금액기본 정보 수정*/

            processCount += result;
        }

        return processCount;
    }
}
