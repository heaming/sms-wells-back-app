package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbAdvanceRefundAccountDto {

    @ApiModel(value = "WwdbAdvanceRefundAccountDto-SearchAdvanceRefundAccountReq")
    public record SearchAdvanceRefundAccountReq(
        String stlmTpCd, // 대상구분
        @NotBlank
        String startDay, // 처리시작일자
        @NotBlank
        String endDay, // 처리종료일자
        @NotBlank
        String cshRfndDvCd // 업무구분 : 선환불계좌만 넘어와야함
    ) {

    }

    @ApiModel(value = "WwdbAdvanceRefundAccountDto-SearchAdvanceRefundAccountRes")
    public record SearchAdvanceRefundAccountRes(
        String stlmTpCd, // 대상구분
        String cntrDtlNo, // 계약상세번호
        String cstKnm, // 고객명
        String fnlMdfcDtm, // 처리일자
        String rfndDsbAmt, // 금액(원)
        String cshRfndFnitCd, // 은행명
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String bltfOjCntrSn, // 전금코드
        String fnlMdfcUsrId, // 요청자
        String exRfndRsonCn, // 예외환불사유
        String rfndEvidMtrFileId // 증빙첨부

    ) {
        public SearchAdvanceRefundAccountRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
        }
    }
}
