package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdaDesignationWithdrawalCustomerMgtDto {

    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SearchAutoFntDsnWdrwCstReq")
    public record SearchAutoFntDsnWdrwCstReq(
        String cntrNo,
        String cntrSn,

        String baseYm, // 기준년월
        String sellTpCd // 판매유형코드
    ) {}

    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SearchAutoFntDsnWdrwCstRes")
    public record SearchAutoFntDsnWdrwCstRes(
        String cntr, // 계약번호
        String cntrNo, // 계약상세번호
        String cntrSn, // 계약상세 일련번호
        String cstKnm, // 고객성명
        String sellTpCd, // 업무유형
        String dsnWdrwAmt, // 지정금액

        String fntYm, // 이체년월
        String dsnWdrwFntD, // 이체일
        String fntYn, // 이체구분
        String dpAmt, // 입금금액
        String ucAmt, // 잔액
        String dsnWdrwFntPrdCd, // 이체주기
        String prtnrKnm, // 등록담당자
        String fstRgstUsrId, // 등록자 사번
        String fstRgstDtm // 등록일시
        //        String bilNo, // 청구번호
        //        String bilDtlSn // 청구상세일련번호
    ) {}

    @Builder
    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SaveReq")
    public record SaveReq(
        String rowState,
        Integer dataRow,
        String cntr, // 계약번호
        String cntrNo, // 계약상세번호
        String cntrSn, // 계약상세 일련번호
        Integer dsnWdrwAmt, // 지정금액
        Integer dpAmt, // 입금금액
        Integer ucAmt, // 잔액
        String fntYn, // 이체구분
        String fntYm, // 이체년월
        String dsnWdrwFntD, // 이체일
        String dsnWdrwFntPrdCd, // 이체주기코드
        //        String bilNo, // 청구번호
        //        String bilDtlSn, // 청구상세일련번호
        String dtaDlYn
    ) {
        public SaveReq {
            dtaDlYn = "N";
        }
    }

    @Builder
    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-RemoveReq")
    public record RemoveReq(
        String cntr,
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String fntYm // 이체일자
    ) {}

    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-checkBillingFundTransferAsk")
    public record CheckBillingFundTransferAsk(
        String bilNo,
        String bilDtlSn
    ) {}

}
