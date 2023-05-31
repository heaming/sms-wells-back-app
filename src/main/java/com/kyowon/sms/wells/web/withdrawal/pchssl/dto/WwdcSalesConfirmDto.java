package com.kyowon.sms.wells.web.withdrawal.pchssl.dto;

import io.swagger.annotations.ApiModel;

public class WwdcSalesConfirmDto {

    // 선납예상금 데이터 
    @ApiModel("WwdcSalesConfirmDto-SearchSalesConfirmReq")
    public record SearchSalesConfirmReq(
        String cntrNo,
        String cntrSn,
        String cellTpCd, // 판매유형코드
        String ogTpCd, /* 조직코드 */
        String dgr1LevlOgId, // 조직레벨 1
        String dgr2LevlOgId, // 조직레벨 2
        String dtFrom, // 매출인식일From
        String dtTo, // 매출인식일To
        String sellChnl, // 판매채널
        String slRcogDv // 판매인식
    ) {}

    @ApiModel("WwdcSalesConfirmDto-SearchSalesConfirmRes")
    public record SearchSalesConfirmRes(
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sellTpCd, /* 업무구분코드 */
        String pdNm, /* 상품명 */
        String sellAmt, /* 판매금액 */
        String rentalPtrm, /* 렌탈기간 */
        String rentalTn, /* 렌탈 차월 : 렌탈 회차 */
        String mmIstmAmt, /* 월납부액 : 월 할부금 */
        String slAmt, /* 매출액 */
        String pvdaAmt, /* 현할차금액*/
        String useDt, /* 렌탈일수 */
        //        String rentalDc, /* 월마감: 렌탈일수 */
        String crtErrCn, /* 오류내역 */
        String slRcogPrdCd, /* 매출인식기준 */
        String slRcogPrdDvCd, /* 매출인식주기*/
        String ostrDtm, /* 출고일자 */
        String istDtm, /* 설치일자 */
        String svDt, /* 서비스일자 */
        String cntrCanDtm, /* 계약취소일자 */
        String slRcogDt, /* 매출인식일자 */
        String fnlMdfcDtm, /* 변경일자 */
        String fnlMdfcUsrId/* 변경자 */
    ) {}

    @ApiModel("WwdcSalesConfirmDto-SaveSalesConfirmReq")
    public record SaveSalesConfirmReq(
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sellTpCd, /* 업무구분코드 */
        String pdNm, /* 상품명 */
        String sellAmt, /* 판매금액 */
        String rentalPtrm, /* 렌탈기간 */
        String rentalTn, /* 렌탈 차월 : 렌탈 회차 */
        String mmIstmAmt, /* 월납부액 : 월 할부금 */
        String slAmt, /* 매출액 */
        String pvdaAmt, /* 현할차금액*/
        String useDt, /* 렌탈일수 */
        //        String rentalDc, /* 월마감: 렌탈일수 */
        String crtErrCn, /* 오류내역 */
        String slRcogPrdCd, /* 매출인식기준 */
        String slRcogPrdDvCd, /* 매출인식주기*/
        String ostrDtm, /* 출고일자 */
        String istDtm, /* 설치일자 */
        String svDt, /* 서비스일자 */
        String cntrCanDtm, /* 계약취소일자 */
        String slRcogDt, /* 매출인식일자 */
        String fnlMdfcDtm, /* 변경일자 */
        String fnlMdfcUsrId, /* 변경자 */
        String state // 임시 상태값 컬럼 추가
    ) {}

}
