package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbContractChangeDvo {

    /* input */
    private String cntrCnfmDtmFr; // 계약시작접수일자
    private String cntrCnfmDtmTo; // 계약종료접수일자

    /* output */
    private String cntrNo; /* 계약번호  lcyear + lccode*/
    private int cntrSn; /* 계약일련번호  */
    private String sellTpCd; /* 판매유형코드 lcType (2:l20)*/
    private String sellTpDtlCd; /* 판매유형상세코드 */

    /* 화면 표시 */
    private String sellTpDtlNm; /* 판매유형사케코드명 (판매유형:lcTypeNm)*/
    private String cstKnm; /* 계약자명 - 고객명(lc31.lccnam) */
    private String bryyBzrno; /* 계약자정보 - 사업자번호(lccino) / 생년 */
    private String copnDvCd; /* 법인격구분코드 (lccgub) 개인:1; 법인:2*/
    private String copnDvNm; /* 법인격구분명 (lccgub) 개인:1; 법인:2*/
    private String cntrDtlNo; /* 계약상세번호 (lcOrdrNo)*/
    private String cntrCnfmDt; /* 계약확정일시(접수일) : 접수일자(lccrtDt) */
    private String pdNm; /* 상품명 (kainam) */
    private String basePdCd; /* 상품코드 (lcicde) */
    private String svPdTpCd; /* 용도구분코드 - 상품의 서비스유형 (용도구분명:lciuseNm; lciuse) */
    private String svPdTpNm; /* 용도구분 - 상품의 서비스유형 (용도구분명:lciuseNm; lciuse) */
    private String svPrd; /* (계약상세.서비스주기) lc31.lcimon */
    private String svPtrmUnitCd; /* 서비스기간단위코드 */
    private String svPtrmUnitNm; /* 서비스기간단위명 */
    private String sexDvCd; /* 성별 (lccsex) */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String cntrDtlStatNm; /* 계약상세상태명 (사용구분 - 1:관리(101);2:탈퇴(201~303); 만료(401;402) :usedivnm)*/
    private int rentalAmt1; /* 렌탈료-최종값 (렌탈료1:lcamt1) */
    private int cntrPtrm1; /* 계약기간 (렌탈기간1 : lcmon1) */
    private int rentalAmt2; /* todo.맵핑없음 (렌탈료2:lcamt2) */
    private int cntrPtrm2; /* todo.맵핑없음 (렌탈기간2 : lcmon2) */
    private String sellDscDvCd;
    private String sellDscDvNm; /* 판매할인구분코드-할인적용유형명 - 할인구분 명(lcetc3Nm) */
    private String sellDscrCd;
    private String sellDscrNm; /* 판매할인율코드- - 할인유형 명(lcetc4M;) */
    private String sellDscTpCd;
    private String sellDscTpNm; /* 판매할인유형코드  (프로모션코드 :lcflg4Nm ; lcflg4)  */
    private String alncmpCd; /* 제휴사코드 (제휴코드:lcetc8)*/
    private String alncmpNm; /* 제휴코드명- 제휴코드명(lcetc8Nm) */
    private String fgptInfo; /* 사은품정보 (lcGift; prmt) 첫번째 사은품명 + 첫번째 사은품코드 외 사은품갯수 */
    private String hclsfRefPdClsfVal; /* kaetc1 06(order.kaetc1=='8') */
    private String mclsfRefPdClsfVal; /* kaetc2 06003("order.kaetc1=='8' and order.kaetc2=='m');  06005(order.kaetc1=='8' and order.kaetc2=='2') */
    private String pdHclsfNm; /* 대분류 kaetc1 kaetc1nm*/
    private String pdMclsfNm; /* 중분류 kaetc2 kaetc2nm*/
    private String hcrDvNm; /* 홈케어구분코드(합쳐짐) = lcprt1Nm + lcprt2Nm*/
    private String bdtMnftNm; /* 제조회사 (일시불일때만 제조회사 표시하지만; 맵핑없음 공통코드:bdtMnftCoCd) lcjejoNm */
    private String istDt; /* 설치일자  lcsetDt*/
    private String sellInflwChnlDtlCd; /* 판매유입채널상세코드 (직원구매 체크용(9020:직원구매->vSalediv:9)) */

    private String prtnrKnm; // 판매자한글명
    private String sellPrtnrNo; // 판매파트너번호
    private String ogTpCd; // 조직유형코드
    private String bfPrtnrNo; /* 변경전 파트너번호 */
    private String bfOgTpCd; /* 변경전 조직유형코드 */

    // DATETIME 반환값
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록유저ID
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID

    private String cntrChRcpId; // 계약변경접수ID
    private String cntrChSn; // 계약변경일련번호
}
