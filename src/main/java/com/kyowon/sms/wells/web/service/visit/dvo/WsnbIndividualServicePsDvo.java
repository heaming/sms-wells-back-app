package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbIndividualServicePsDvo {
    String cntrNoDtl; /*계약상세번호*/
    String cstNm; /*고객명*/
    String cstGdNm; /*고객등급*/
    String locaraTno; /*전화번호(지역전화번호)*/
    @DBDecField
    String exnoEncr; /*전화번호(전화국번호암호화)*/
    String idvTno; /*전화번호(개별전화번호)*/
    String cralLocaraTno; /*휴대전화번호(휴대지역전화번호)*/
    @DBDecField
    String mexnoEncr; /*휴대전화번호(휴대전화국번호암호화)*/
    String cralIdvTno; /*휴대전화번호(휴대개별전화번호)*/
    String adrDtl; /*상세주소*/
    String basePdNm; /*계약상품*/
    String pdNm; /*현재상품*/
    String frisuAsPtrmN; /*무상AS개월수*/
    String frisuBfsvcPtrmN; /*무상BS개월수*/
    String pdUswy; /*용도구분*/
    String ogCd; /*지점코드*/
    String brmgrPrtnrNm; /*지점장*/
    String brmgrPrtnrNo; /*지점장사번*/
    String asnDt; /*배정일자*/
    String sellChnl; /*판매채널*/
    String prtnrNo; /*판매자사번*/
    String prtnrKnm; /*판매자*/
    String bfCntrNo; /*전상대코드*/
    String istDt; /*설치일자*/
    String chngDt; /*교체일자*/
    String canDt; /*취소일자*/
    String svStpDt; /*중지일자*/
    String asExprDt; /*AS만기*/
    String bsExprDt; /*BS만기*/
    String cpsDt; /*기변일자 - 보상일자*/
    String mshJDt; /*멤버십시작일*/
    String mshWdwalDt; /*멤버십종료일*/
    String bcNo; /*바코드*/
    String pblBcNo; /*발행바코드*/
    String qrsRdmNo; /*맞춤가이드 - 랜덤QR번호*/
    String ssPdctBcNo; /*삼성제조번호*/
    String sppIvcNo; /*송장번호*/

    /*가구화정보 조회*/
    String svHshdNo; /*가구화번호*/
    String cntrDtl; /*계약상세번호*/
    String cstKnm; /*고객명*/
    String adrZip; /*주소*/

    /*특이사항 저장*/
    String cntrNo; /*계약번호*/
    String cntrSn; /*계약일련번호*/
    String ogTpCd; /*소속(작성자)*/
    String wkPrtnrNo; /*파트너번호(작성자)*/
    String cstUnuitmCn; /*고객특이사항상세*/

    /*처리내역조회*/
    String gubun; /*작업구분*/
    String svTp; /* 유형*/
    String rcpDt; /* 접수(배정)일자*/
    String svBizDclsf; /*접수(배정)내역*/
    String reqDt; /*요청약속일자*/
    String vstFshDt; /* 처리일자*/
    String wkPrgsStat; /* 처리결과*/
    String asCaus; /* 처리내역*/
    String rtngdProcsTp; /* 반품처리정보*/
    String fstVstFshDt; /* 폐기일자: 출고확인일자*/
    String zipNo; /* 우편번호*/
    String ogTp; /* 구분(담당자)*/
    String ogNm; /* 소속(담당자)*/
    String prtnrNm; /* 성명(담당자)*/
    String bldNm; /* 소속빌딩(담당자)*/
    String imgYn; /* 사진*/
    String cstSvAsnNo; /*배정번호*/
    String procStus; /* 작업진행상태코드*/
    String istEnvrPhoPhDocId; /* 설치환경사진경로*/
    String istKitPhoPhDocId; /* 설치키트사진경로*/
    String istCelngPhoPhDocId; /* 설치천장사진경로*/
    String istEnvrFileUid; /* 설치환경사진UID*/
    String istKitFileUid; /* 설치키트사진UID*/
    String istCelngFileUid; /* 설치천장사진UID*/
    String svHshdNoCnt; /* 가구화번호CNT*/
    String svBizHclsfCd; /* 서비스대분류코드*/
    String svBizDclsfCd; /* 서비스소분류코드*/
    String taikYn; /*배송여부*/

}
