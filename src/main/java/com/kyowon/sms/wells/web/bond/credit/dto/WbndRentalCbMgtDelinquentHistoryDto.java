package com.kyowon.sms.wells.web.bond.credit.dto;

import javax.validation.constraints.NotBlank;

import org.eclipse.jetty.util.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbndRentalCbMgtDelinquentHistoryDto {
    @ApiModel("WbndRentalCbMgtDelinquentHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /*기준년월*/
        String cstNo, /*고객번호*/
        String cralLocaraTno, /*휴대지역전화번호*/
        String mexnoEncr, /*휴대전화국번호암호화*/
        String cralIdvTno/*휴대개별전화번호*/
    ) {}
    @Builder
    @ApiModel("WbndRentalCbMgtDelinquentHistoryDto-SearchRes")
    public record SearchRes(
        String cstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String copnDvCd, /*법인격구분코드*/
        String copnDvNm, /*법인격구분명*/
        String cralLocaraTno, /*휴대폰번호1*/
        String mexnoEncr, /*휴대폰번호2*/
        String cralIdvTno, /*휴대폰번호3*/
        String cralTno, /*휴대폰번호 통합*/
        Long dlqAmt, /*연체금액*/
        Long dlqBlam, /*연체잔액*/
        String clctamPrtnrNo, /*집금담당자번호*/
        String prtnrKnm, /*집금담당자명*/
        String dsphTno, /*발신번호*/
        String rgstSchDt, /*등록예정일자-알림톡발송일자+4일*/
        String niceFwExcdYn, /*NICE 제외여부*/
        String fntStplDt, /*납입기한일자*/
        String msgFwDt, /*알림톡 발송일자*/
        String resultYn, /*성공여부*/
        String niceFwDt /*NICE 발송일자*/
    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(mexnoEncr)) {
                cralTno = cralLocaraTno + "-" + mexnoEncr
                    + (StringUtil.isNotBlank(cralIdvTno) ? ("-" + cralIdvTno) : "");
            } else if (StringUtil.isBlank(mexnoEncr) && StringUtil.isNotBlank(cralLocaraTno)
                && cralLocaraTno.startsWith("1")) {
                cralTno = cralLocaraTno + "-" + cralIdvTno;
            }
        }
    }

    @ApiModel("WbndRentalCbMgtDelinquentHistoryDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm, /*기준년월*/
        @NotBlank
        String cstNo, /*고객번호*/
        @NotBlank
        String niceFwExcdYn /*나이스발송제외여부*/
    ) {}

    @ApiModel("WbndRentalCbMgtDelinquentHistoryDto-SendReq")
    public record SendReq(
        @NotBlank
        String baseYm, /*기준년월*/
        @NotBlank
        String cstNo, /*고객번호*/
        @NotBlank
        String cstKnm, /*고객명*/
        @NotBlank
        String cralTno, /*휴대폰번호 통합*/
        @NotBlank
        String dlqBlam, /*연체잔액*/
        @NotBlank
        String fwbooDate, /*발송예약일*/
        @NotBlank
        String fwbooTime/*발송예약시간*/
    ) {}
}
