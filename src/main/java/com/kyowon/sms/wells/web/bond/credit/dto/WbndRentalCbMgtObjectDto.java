package com.kyowon.sms.wells.web.bond.credit.dto;

import javax.validation.constraints.NotBlank;

import org.eclipse.jetty.util.StringUtil;

import io.swagger.annotations.ApiModel;

public class WbndRentalCbMgtObjectDto {
    @ApiModel("WbndRentalCbMgtObjectDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /*기준년월*/
        @NotBlank
        String selGbn, /*조회구분*/
        String cstNo, /*고객번호*/
        String cralLocaraTno, /*휴대지역전화번호*/
        String mexnoEncr, /*휴대전화국번호암호화*/
        String cralIdvTno/*휴대개별전화번호*/
    ) {}

    @ApiModel("WbndRentalCbMgtObjectDto-SearchRes")
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
        String niceFwExcdYn, /*나이스발송제외여부*/
        String col11, /*납입기한일자*/
        String msgFwDt, /*알림톡 발송일자*/
        String resultYn, /*알림톡성공여부*/
        String niceFwDt /* 나이스발송일자 */
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

    @ApiModel("WbndRentalCbMgtObjectDto-SearchPaymentRes")
    public record SearchPaymentRes(
        String cstNo, /*고객번호*/
        String cntrDtlSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String copnDvNm, /*법인격구분명*/
        String sellTpNm, /*상품구분*/
        String pdClsfNm, /*상품명*/
        String slRcogDt, /*매출일자*/
        String cralLocaraTno, /*휴대폰번호1*/
        String mexnoEncr, /*휴대폰번호2*/
        String cralIdvTno, /*휴대폰번호3*/
        String cralTno, /*휴대폰번호 통합*/
        String mpyBsdt, /*자동이체 약정일자*/
        String fntDvNm, /*결제수단*/
        Long dlqAmt, /*연체금액*/
        Long dlqBlam, /*연체잔액*/
        String clctamPrtnrNo, /*집금담당자번호*/
        String prtnrKnm, /*집금담당자명*/
        String dsphTno, /*발신번호*/
        String rgstSchDt, /*등록예정일자-알림톡발송일자+4일*/
        String niceFwExcdYn, /*NICE발송제외여부*/
        String fwbooDtm, /*알림톡발송일자*/
        String resultYn, /*알림톡성공여부*/
        String notyFwOjDt /*알림톡발송제외일자*/
    ) {
        public SearchPaymentRes {
            if (StringUtil.isNotBlank(mexnoEncr)) {
                cralTno = cralLocaraTno + "-" + mexnoEncr
                    + (StringUtil.isNotBlank(cralIdvTno) ? ("-" + cralIdvTno) : "");
            } else if (StringUtil.isBlank(mexnoEncr) && StringUtil.isNotBlank(cralLocaraTno)
                && cralLocaraTno.startsWith("1")) {
                cralTno = cralLocaraTno + "-" + cralIdvTno;
            }
        }
    }

}
