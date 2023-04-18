package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdbCorporationDepositSspMgtDto {
    @Builder
    @ApiModel(value = "WwdbCorporationDepositSspMgtDto-SearchReq")
    public record SearchReq(
        String perfDt, // 실적일자
        String rveDt, // 수납일자
        //        String crpAcno, // 계좌번호
        //        String alncmpDprNm, // 입금자명
        String rveCd // 수납코드
    ) {}

    @ApiModel(value = "WwdbCorporationDepositSspMgtDto-SearchRes")
    public record SearchRes(
        String itgDpNo, // 통합입금번호
        String alncmpSuscOrdNo, // 구독주문번호
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String sellTpCd, // 판매유형
        //        String crpAcno, // 계좌번호
        //        String alncmpDprNm, // 입금자명
        String alncmpDpAmt, // 기본금액
        String alncmpFee, // 수수료
        String sumAmt // 합계금액
    ) {}

    @ApiModel(value = "WwdbCorporationDepositSspMgtDto-SearchReq")
    public record CreateReq(
        String itgDpNo, // 통합입금번호
        String alncmpSuscOrdNo, // 구독주문번호
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String sellTpCd, // 판매유형
        //        String crpAcno, // 계좌번호
        //        String alncmpDprNm, // 입금자명
        String alncmpDpAmt, // 기본금액
        String alncmpFee // 수수료
        //        String sumAmt // 합계금액
    ) {}
}
