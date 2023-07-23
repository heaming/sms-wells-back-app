package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WwdbGiroDepositMgtDto {
    public record SearchReq(
        String rveDt,
        String fntDt,
        String giroOcrBndlYm,
        String errorChk,

        String dgCntrNo,
        String dgCntrSn
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchRes")
    public record SearchRes(
        String kwGrpCoCd,
        String cntrNo,
        String cntrSn,
        String cntr,
        String cstKnm,
        String rveDt,
        String perfDt,
        String rveAmt,
        String giroFee,
        String sellTpCd,
        String dpMesCd,
        String procsErrTpCd,
        String dgCntr,
        String dgCntrNo,
        String dgCntrSn,
        String giroRveDvCd,
        String giroNo /*지로번호*/,
        String giroDpMtrDvCd /*지로입금자료구분코드*/,
        String giroDpSn, /*일련번호*/
        String itgDpProcsYn,

        String itgDpNo

        //        String kwGrpCoCd,
        //        String cntrNo, //--계약번호
        //        String cstKnm, // --고객명
        //        String itgDpNo, //--통합입금번호
        //        String fntDt, //--입금일자
        //        String rveDt, //--실적일자
        //        String pyAmt, //--입금금액
        //        String giroFee, // --수수료금액
        //        String dpMesCd, //--입금유형
        //        String sellTpCd, //--판매유형
        //        String procsErrTpCd, // --처리오류유형코드
        //        String dgCntrNo //--대표번호
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchRes")
    public record SearchSumRes(
        String rveAmtSum,
        String giroFeeSum
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SaveReq")
    public record SaveReq(
        String giroDpMtrDvCd, // --구분코드
        String dpSn, // --일련번호
        String rveDt, //--수납일자
        String fntDt, //--이체일자
        String giroDpBnkCd, //--은행코드
        String bnkBrncCd, //--은행점포코드
        String giroIndxNo, //--지로색인번호
        String giroInqNo, //--지로조회번호
        String pyAmt, //--납입금액
        String giroRveDvCd, //--지로수납구분코드
        String giroFeeDvCd, //--지로수수료구분코드
        String rmkCn //--비고
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDepositRes")
    public record SearchDepositRes(
        String itgDpNo,
        String giroNo,
        String giroDpMtrDvCd,
        String giroDpSn,
        String fntDt,
        String rveDt,
        String giroDpBnkCd,
        String giroDpNo,
        String cntrNo,
        String cntrSn,
        String pyTn,
        String pyYm,
        String pyAmt,
        String giroFee,
        String giroRveDvCd,
        String giroPrtsDvCd,
        String procsErrTpCd,
        String dtaDlYn
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDepositReq")
    public record SearchDepositReq(

    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDepositCountReq")
    public record SearchDepositCountReq(
        String giroOcrBndlYm,
        String dgCntrNo,
        String dgCntrSn
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SaveReq")
    public record SaveBillingReq(

    ) {

    }

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDepositCountReq")
    public record SearchContractDetailRes(
        String cntrNo,
        String cntrSn,
        String basePdCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String cntrCstNo,
        String cntrCanDtm,
        String sellTpCd,
        String sellTpDtlCd,
        String cntrDtlStatCd
    ) {}

    /* 지로 입금 설정 금액 및 고객 조회*/
    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDepositSettingRes")
    public record SearchDepositSettingRes(
        String dpSeAmt,
        String dgCntrNo,
        String dgCntrSn,
        String dpseAmt, /*설정금액*/
        String cntrNo,
        String cntrSn
    ) {

    }

    //지로 입금 에러 조회
    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchErrosRes")
    public record SearchErrosRes(
        String itgDpNo, //--통합입금번호
        String cntrNo, //--계약번호
        String cntr, //--계약번호
        String cstKnm, //--  a. 고객명
        String pyAmt, //--납입금액
        String rveDt, //--입금일
        String fntDt, //--실적일
        String fnlMdfcDtm, //--수정일시
        String fnlMdfcUsrId, //--수정아이디
        String dpErrProcsCn,
        String cntrSn
    ) {}

    //지로 입금 에러 저장
    @ApiModel(value = "WwwdbGiroDepositMgtDto-SaveErrosReq")
    public record SaveErrosReq(
        String rowState,
        String itgDpNo, //--통합입금번호
        String cntrNo, //--계약번호
        String cstKnm, //--  a. 고객명
        String pyAmt, //--납입금액
        String rveDt, //--입금일
        String fntDt, //--실적일
        String cntrSn,
        String dpErrProcsCn,
        String cntr
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDtlStateRes")
    public record SearchDtlStateRes(

        String cntrNo,
        String cntrSn,
        String cntrDtlStatCd //--계약상태코드
    ) {}
    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchDtlStateReq")
    public record SearchDtlStateReq(

        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchLedgerItemizationReq")
    public record SearchLedgerItemizationReq(
        String fntDt,
        String giroDpMtrDvCd

    ) {}

    @ApiModel(value = "WwwdbGiroDepositMgtDto-SearchLedgerItemizationReq")
    public record SearchLedgerItemizationRes(
        String fntDt,
        int chkCnt,
        int itgDpProcsYCnt
    ) {}

}
