package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 선환불현황 DTO
 * </pre>
 *
 * @author jaeha.yeon
 * @since 2023-10-20
 */
public class WwdbAdvanceRefundAccountDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 선환불현황 조회 Request Dto
    @ApiModel(value = "WwdbAdvanceRefundAccountDto-SearchAdvanceRefundAccountReq")
    public record SearchAdvanceRefundAccountReq(
        String sellTpCd, // 판매유형코드
        @NotBlank
        String startDay, // 처리시작일자
        @NotBlank
        String endDay, // 처리종료일자
        @NotBlank
        String cshRfndDvCd // 업무구분 : 선환불계좌만 넘어와야함
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    // 선환불현황조회 Response Dto
    @ApiModel(value = "WwdbAdvanceRefundAccountDto-SearchAdvanceRefundAccountRes")
    public record SearchAdvanceRefundAccountRes(
        String sellTpCd, // 대상구분
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, // 계약상세번호
        String cstKnm, // 고객명
        String rfndRveDt, // 처리일자
        String totRfndEtAmt, // 금액(원)
        String cshRfndFnitNm, // 은행명
        @MaskRequired(type = MaskingType.ACCOUNT)
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String bltfOjCntrDtlNo, // 전금코드
        String rfndRcpPrtnrNm, // 요청자
        String exRfndRsonCn, // 예외환불사유
        String rfndEvidMtrFileYn // 증빙첨부

    ) {
        public SearchAdvanceRefundAccountRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
        }
    }
}
