package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccProductAccountDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdccProductAccountDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /*기준년월 시작*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String ogTpCd, /*조직유형*/
        String prdtCateHigh, /*상품대분류*/
        String prdtCateMid /*상품중분류*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchTotalRes")
    public record SearchTotalRes(
        String baseYm, /*기준년월*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String agrgCt1, /*이월*/
        String agrgCt2, /*유입*/
        String agrgCt3, /*해지*/
        String agrgCt4, /*만료*/
        String agrgCt5, /*순증*/
        String agrgCt6 /*합계*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchProductRes")
    public record SearchProductRes(
        String baseYm, /*기준년월*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String pdHclsfNm, /*상품대분류*/
        String pdMclsfNm, /*상품중분류*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String agrgCt1, /*이월*/
        String agrgCt2, /*유입*/
        String agrgCt3, /*해지*/
        String agrgCt4, /*만료*/
        String agrgCt5, /*순증*/
        String agrgCt6 /*합계*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchExcelRes")
    public record SearchExcelRes(
        String sellTpDtlCd, /*판매유형상세*/
        String custClsCd, /*고객구분(법인격)*/
        String cntrNo, /*계약번호*/
        String cstKnm, /*고객명*/
        String newCustYn, /*신규교원키여부*/
        String cstNo, /*고객번호*/
        String bryyMmdd, /*고객연령*/
        String sexDvCd, /*성별*/
        String ogTpNm, /*소속조직명*/
        String ogCd, /*파트너지점코드*/
        String prtnrNo, /*판매자 파트너번호*/
        String prtnrNm, /*파트너명*/
        String ctntCstZip, /*계약우편번호*/
        String ojZip, /*배송주소우편번호*/
        String pdHclsfNm, /*상품대분류명*/
        String pdMclsfNm, /*상품중분류명*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String svPrd, /*서비스주기*/
        String vstPrdVal, /*방문주기*/
        String dutyUseMcn, /*의무기간*/
        String cntrPtrm, /*계약기간*/
        String mmIstmAmt, /*렌탈료*/
        String cntrPmotId, /*프로모션코드*/
        String rcpdt, /*접수일자*/
        String cntrDt, /*계약일자*/
        String rsgDt, /*해지일자*/
        String reqdDt, /*철거일자*/
        String exnDt, /*의무만료일*/
        String cntrExnDt, /*계약만료일*/
        String dutyUseMcnYn, /*의무기간 도래 여부*/
        String rentalTn, /*렌탈차월*/
        String nomAccYn, /*정상계정여부*/
        String sellTpDtlCd2, /*판매유형상세*/
        String custClsCd2, /*고객구분(법인격)*/
        String cntrNo2, /*계약번호*/
        String cstKnm2, /*고객명*/
        String newCustYn2, /*신규교원키여부*/
        String cstNo2, /*고객번호*/
        String bryyMmdd2, /*고객연령*/
        String sexDvCd2, /*성별*/
        String ogTpNm2, /*소속조직명*/
        String ogCd2, /*파트너지점코드*/
        String prtnrNo2, /*판매자 파트너번호*/
        String prtnrNm2, /*파트너명*/
        String ctntCstZip2, /*계약우편번호*/
        String ojZip2, /*배송주소우편번호*/
        String pdHclsfNm2, /*상품대분류명*/
        String pdMclsfNm2, /*상품중분류명*/
        String pdCd2, /*상품코드*/
        String pdNm2, /*상품명*/
        String svPrd2, /*서비스주기*/
        String vstPrdVal2, /*방문주기*/
        String dutyUseMcn2, /*의무기간*/
        String cntrPtrm2, /*계약기간*/
        String mmIstmAmt2, /*렌탈료(월할부금액)*/
        String cntrPmotId2, /*프로모션코드*/
        String rcpdt2, /*접수일자*/
        String cntrDt2, /*계약일자*/
        String rsgDt2, /*해지일자*/
        String reqdDt2, /*철거일자*/
        String exnDt2, /*의무만료일*/
        String cntrExnDt2, /*계약만료일*/
        String dutyUseMcnYn2, /*의무기간 도래 여부*/
        String rentalTn2, /*렌탈차월*/
        String nomAccYn2, /*정상계정여부*/
        String sellTpDtlCd3, /*판매유형상세*/
        String custClsCd3, /*고객구분(법인격)*/
        String cntrNo3, /*계약번호*/
        String cstKnm3, /*고객명*/
        String newCustYn3, /*신규교원키여부*/
        String cstNo3, /*고객번호*/
        String bryyMmdd3, /*고객연령*/
        String sexDvCd3, /*성별*/
        String ogTpNm3, /*소속조직명*/
        String ogCd3, /*파트너지점코드*/
        String prtnrNo3, /*판매자 파트너번호*/
        String prtnrNm3, /*파트너명*/
        String ctntCstZip3, /*계약우편번호*/
        String ojZip3, /*배송주소우편번호*/
        String pdHclsfNm3, /*상품대분류명*/
        String pdMclsfNm3, /*상품중분류명*/
        String pdCd3, /*상품코드*/
        String pdNm3, /*상품명*/
        String svPrd3, /*서비스주기*/
        String vstPrdVal3, /*방문주기*/
        String dutyUseMcn3, /*의무기간*/
        String cntrPtrm3, /*계약기간*/
        String mmIstmAmt3, /*렌탈료(월할부금액)*/
        String cntrPmotId3, /*프로모션코드*/
        String rcpdt3, /*접수일자*/
        String cntrDt3, /*계약일자*/
        String rsgDt3, /*해지일자*/
        String reqdDt3, /*철거일자*/
        String exnDt3, /*의무만료일*/
        String cntrExnDt3, /*계약만료일*/
        String dutyUseMcnYn3, /*의무기간 도래 여부*/
        String rentalTn3, /*렌탈차월*/
        String nomAccYn3 /*정상계정여부*/
    ) {}

    @ApiModel("WdccProductAccountDto-SearchStatusRes")
    public record SearchStatusRes(
        @NotBlank
        String zfgubn, /* 구분 (WA) */
        String zfcdty, /* 기준년 */
        String zfcdtm, /* 기준월 */
        String zfcseq /* 상태 (0: 진행 중, 1: 완료) */
    ) {}
}
