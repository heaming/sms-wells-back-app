package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbContractChangeMngtDto {

    @ApiModel(value = "WctbContractChangeMngtDto-SearchContractChangeReq")
    public record SearchContractChangeReq(
        String cntrNo, // 계약번호
        int cntrSn, // 계약일련번호
        String sellTpCd, // 판매유형코드
        String cstKnm, // 계약자명
        String cntrCnfmDtmFr, // 계약시작접수일자
        String cntrCnfmDtmTo // 계약종료접수일자
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbContractChangeMngtDto-SearchContractChangeRes")
    public record SearchContractChangeRes(
        String cntrNo, /* 계약번호  lcyear + lccode*/
        int cntrSn, /* 계약일련번호  */
        String sellTpCd, /* 판매유형코드 lcType (2:l20)*/
        String sellTpDtlCd, /* 판매유형상세코드 */
        /* 화면 표시 */
        String sellTpDtlNm, /* 판매유형사케코드명 (판매유형:lcTypeNm)*/
        String cstKnm, /* 계약자명 - 고객명(lc31.lccnam) */
        String bryyBzrno, /* 계약자정보 - 사업자번호(lccino) / 생년 */
        String copnDvCd, /* 법인격구분코드 (lccgub) 개인:1, 법인:2*/
        String copnDvNm, /* 법인격구분명 (lccgub) 개인:1, 법인:2*/
        String cntrDtlNo, /* 계약상세번호 (lcOrdrNo)*/
        String cntrCnfmDt, /* 계약확정일시(접수일) : 접수일자(lccrtDt) */
        String pdNm, /* 상품명 (kainam) */
        String basePdCd, /* 상품코드 (lcicde) */
        String svPdTpCd, /* 용도구분코드 - 상품의 서비스유형 (용도구분명:lciuseNm, lciuse) */
        String svPdTpNm, /* 용도구분 - 상품의 서비스유형 (용도구분명:lciuseNm, lciuse) */
        String svPrd, /* (계약상세.서비스주기) lc31.lcimon */
        String svPtrmUnitCd, /* 서비스기간단위코드 */
        String svPtrmUnitNm, /* 서비스기간단위명 */
        String sexDvCd, /* 성별 (lccsex) */
        String cntrDtlStatCd, /* 계약상세상태코드 */
        String cntrDtlStatNm, /* 계약상세상태명 (사용구분 - 1:관리(101),2:탈퇴(201~303), 만료(401,402) :usedivnm)*/
        int rentalAmt1, /* 렌탈료-최종값 (렌탈료1:lcamt1) */
        int cntrPtrm1, /* 계약기간 (렌탈기간1 : lcmon1) */
        int rentalAmt2, /* todo.맵핑없음 (렌탈료2:lcamt2) */
        int cntrPtrm2, /* todo.맵핑없음 (렌탈기간2 : lcmon2) */
        String sellDscDvCd,
        String sellDscDvNm, /* 판매할인구분코드-할인적용유형명 - 할인구분 명(lcetc3Nm) */
        String sellDscrCd,
        String sellDscrNm, /* 판매할인율코드- - 할인유형 명(lcetc4M,) */
        String sellDscTpCd,
        String sellDscTpNm, /* 판매할인유형코드  (프로모션코드 :lcflg4Nm , lcflg4)  */
        String alncmpCd, /* 제휴사코드 (제휴코드:lcetc8)*/
        String alncmpNm, /* 제휴코드명- 제휴코드명(lcetc8Nm) */
        String fgptInfo, /* 사은품정보 (lcGift, prmt) 첫번째 사은품명 + 첫번째 사은품코드 외 사은품갯수 */
        String hclsfRefPdClsfVal, /* kaetc1 06(order.kaetc1=='8') */

        String mclsfRefPdClsfVal, /* kaetc2 06003("order.kaetc1=='8' and order.kaetc2=='m'),  06005(order.kaetc1=='8' and order.kaetc2=='2') */
        String pdHclsfNm, /* 대분류 kaetc1 kaetc1nm*/
        String pdMclsfNm, /* 중분류 kaetc2 kaetc2nm*/
        String hcrDvNm, /* 홈케어구분코드(합쳐짐) = lcprt1Nm + lcprt2Nm*/
        String bdtMnftNm, /* 제조회사 (일시불일때만 제조회사 표시하지만, 맵핑없음 공통코드:bdtMnftCoCd) lcjejoNm */
        String istDt, /* 설치일자  lcsetDt*/
        String sellInflwChnlDtlCd /* 판매유입채널상세코드 (직원구매 체크용(9020:직원구매->vSalediv:9)) */
    ) {}

}
