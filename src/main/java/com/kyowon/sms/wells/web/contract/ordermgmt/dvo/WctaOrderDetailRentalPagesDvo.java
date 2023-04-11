package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailRentalPagesDvo {
    private String cntrDtlNo; /* 계약상세번호 */
    private String sellTpDtlNm; /* 판매유형 */
    private String dgr3LevlDgPrtnrNo; /* 파트너정보-지점장 사번 */
    private String dgr3LevlDgPrtnrNm; /* 파트너정보-지점장명 */
    private String dgr3LevlOgCd; /* 파트너정보-지점코드 */
    private String sellPrtnrNo; /* 파트너정보-파트너사번 */
    private String prtnrKnm; /* 파트너정보-파트너명 */
    private String sellPrtnrCralTno; /* 판매자-휴대전화번호 */
    private String cralLocaraTno; /* 판매자-휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 판매자-휴대전화국번호암호화 */
    private String cralIdvTno; /* 판매자-휴대개별전화번호 */
    private String cntrDt; /* 파트너정보-업무개시일 */
    private String cltnDt; /* 파트너정보-업무해약일 */
    private String cstKnm; /* 계약자 정보-계약자명 */
    private String bryy; /* 계약자 정보-생년(YY) */
    private String bzrNo; /* 계약자 정보-사업자번호 */
    private String sexDvNm; /* 계약자 정보-성별 */
    private String cntrCstNo; /* 계약자 정보-고객번호 */
    private String adrZip; /* 계약자 정보-우편번호 */
    private String cntrCstRnadr; /* 계약자 정보-기준주소 */
    private String cntrCstRdadr; /* 계약자 정보-상세주소 */
    private String rcgvpKnm; /* 설치정보-설치자명 */
    private String istCralTno; /* 설치정보-휴대전화번호 */
    private String istCralLocaraTno; /* 설치정보-휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 설치정보-휴대전화국번호암호화 */
    private String istCralIdvTno; /* 설치정보-휴대개별전화번호 */
    private String istAdrZip; /* 설치정보-우편번호 */
    private String istRnadr; /* 설치정보-기준주소 */
    private String istRdadr; /* 설치정보-상세주소 */
    private String sppOrdNo; /* 설치정보-운송장 번호 */
    private String pdctIdno; /* 설치정보-S N 번호 */
    private String istAkDt; /* 설치요청일 */
    private String sellInflwChnlDtlNm; /* 판매구분 */
    private String copnDvNm; /* 고객구분 */
    private String pdClsfNm; /* 상품 정보-상품분류 */
    private String pdNm; /* 상품 정보-상품명 */
    private String basePdCd; /* 상품 정보-상품코드 */
    private String pdTpNm; /* 상품 정보-상품유형 */
    private String svPrd; /* 상품 정보-주기 */
    private String svTpCd; /* 상품 정보-용도구분 */
    private String svTpNm; /* 상품 정보-용도구분 명 */
    private String cntrRcpFshDt; /* 접수일 */
    private String sppDuedt; /* 예정일 */
    private String istDt; /* 설치일 */
    private String slDt; /* 매출일 */
    private String istmMcn; /* 계약기간 */
    private String cntrPdEnddt; /* 만료일 */
    private String canDt; /* 취소일 */
    private String reqdDt; /* 철거일 */
    private String exnReqdDt; /* 회수일 */
    private String recapDutyPtrmN; /* 의무기간 */
    private String cntrAmt; /* 등록비 */
    private String cntrCtrAmt; /* 등록할인 */
    private String pdBaseAmt; /* 렌탈료1 */
    private String ctrVal; /* 렌탈할인1 */
    private String rentalAmt2; /* 렌탈료2 */
    private String rentalDscAmt2; /* 렌탈할인1=2 */
    private String rentalDscDfam; /* 할인차액 */
    private String booSellYn; /* 예약 */
    private String mchnChYn; /* 기변 */
    private String mchnCpsApyr; /* 기변실적율 */
    private String ackmtPerfAmt; /* 인정실적액 */
    private String ackmtPerfRt; /* 인정실적률(%) */
    private String feeAckmtBaseAmt; /* 수수료기준금액 */
    private String feeFxamYn; /* 수수료정액여부 */
    private String dscApyDtlCd; /* 할인구분 */
    private String dscApyTpCd; /* 할인유형 */
    private String dscPmotCd; /* 할인제도 */
    private String mchnChTpCd; /* 기변유형 */
    private String ojCntrDtlNo; /* 기변상대코드 */
    private String ojBasePdCd; /* 기변이전상품 */
    private String ojPdNm; /* 기변이전상품명 */
    private String bogoCd; /* １＋１연계코드 */
    private String bogoPdCd; /* １＋１이전상품 */
    private String bogoPdNm; /* １＋１이전상품명 */
    private String mpyMthdTpNm; /* 계좌정보-자동이체 */
    private String aftnInf; /* 계좌정보-카드／계좌번호 */
    private String aftnOwrKnm; /* 계좌정보-예금／카드주명 */
    private String sellEvCd; /* 행사코드 */
    private String alncmpCd; /* 제휴코드 */
    private String alncmpNm; /* 제휴코드명 */
    private String alncStatTpNm; /* 제휴상태 */
    private String alncmpCstCd; /* 제휴고객코드 */
    private String alncmpPrtnrNo; /* 제휴파트너 */
    private String cttRsCd; /* 컨택코드 */
    private String cttRsNm; /* 컨택내용 */
    private String fstRgstPrgId; /* 등록프로그램 */
    private String fstRgstUsrId; /* 업무담당 */
    private String fstRgstUsrNm; /* 업무담당명 */
    private String fstPerfYm; /* 최초연체 */
    private String fnlPerfYm; /* 최종연체 */
    private String fstMngtYm; /* 최초매출중지 */
    private String fnlMngtYm; /* 최종매출중지 */
    private String pmotCd; /* 프로모션 정보-프로모션 번호 */
    private String pmotFvrMcn; /* 프로모션 정보-할인개월 */
    private String pmotFvrAmt; /* 프로모션 정보-할인금액 */
    private String frisuBfsvcPtrmN; /* 프로모션 정보-무료개월 */
    private String canPdGdCd; /* 취소등급 */
    private String freExpnYn; /* 무료체험여부 */
    private String freExpnCnfmStrtdt; /* 무료체험시작일 */
    private String freExpnCnfmYn; /* 체험확정여부 */
    private String freExpnCnfmDt; /* 체험확정일 */
    private String combiNm; /* 결합구분 */
    private String fstRgstDt; /* 주문 등록일 */
    private String fstRgstTm; /* 주문 등록시간 */
    private String z11Yn; /* 정보수집 동의 */
    private String z13Yn; /* 마케팅 동의 */
    private String z15Yn; /* 제３자동의 */
    private String w22Yn; /* 제３자동의(피버) */
    private String cntrCralTno; /* 계약자 휴대전화번호 */
    private String cntrCralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cntrCralIdvTno; /* 계약자 휴대개별전화번호 */
    private String dntcYn; /* 두낫콜 여부 */
    private String stplDscAmt; /* 재약정 가입정보-할인금액 */
    private String stplStrtdt; /* 재약정 가입정보-시작일 */
    private String stplCanDt; /* 재약정 가입정보-취소일 */
    private String stplRcpDt; /* 재약정 가입정보-접수일 */
    private String stplRentalTn; /* 재약정 가입정보-재약정 가입차월 */
    private String mshCntrRcpFshDt; /* 멤버십 정보접수일 */
    private String mshCntrTempSaveDt; /* 멤버십 정보가입일 */
    private String mshReqdDt; /* 멤버십 정보취소일 */
    private String mshCntrPdEnddt; /* 멤버십 정보탈퇴일 */
    private String lcet13; /* 타사보상업체 */
    private String svAmt; /* 서비스료 */
    private String rentalTn; /* 렌탈차월 */
    private String fgpt1PdNm; /* 사은품정보-사은품명1 */
    private String fgpt1PdCd; /* 사은품정보-사은품코드1 */
    private String fgpt1Qty; /* 사은품정보-사은품수량1 */
    private String fgpt2PdNm; /* 사은품정보-사은품명2 */
    private String fgpt2PdCd; /* 사은품정보-사은품코드2 */
    private String fgpt2Qty; /* 사은품정보-사은품수량2 */
    private String fgpt3PdNm; /* 사은품정보-사은품명3 */
    private String fgpt3PdCd; /* 사은품정보-사은품코드3 */
    private String fgpt3Qty; /* 사은품정보-사은품수량3 */
    private String fgpt4PdNm; /* 사은품정보-사은품명4 */
    private String fgpt4PdCd; /* 사은품정보-사은품코드4 */
    private String fgpt4Qty; /* 사은품정보-사은품수량4 */
    private String fgpt5PdNm; /* 사은품정보-사은품명5 */
    private String fgpt5PdCd; /* 사은품정보-사은품코드5 */
    private String fgpt5Qty; /* 사은품정보-사은품수량5 */
}
