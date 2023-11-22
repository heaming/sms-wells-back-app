package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.27
 */
@Setter
@Getter
public class WsnbServiceProcessingDvo {

    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cntrNoSn; // 계약상세번호
    String cstSvAsnNo; // 고객서비스배정번호
    String cstKnm; // 고객명
    String copnDvNm; // 법인격구분명
    String cttIchr; // 컨택담당
    String hsp; // 홈쇼핑
    String alncBzsNm; // 제휴회사명
    String frisuYn; // 무료체험(무상여부)
    String sellTpNm; // 고객유형(판매유형명)
    String wtholVlvOptYn; // 조리수설치여부
    String cralLocaraTno; // 휴대지역전화번호
    @DBDecField
    String mexnoEncr; // 휴대전화국번호암호화
    String cralIdvTno; // 휴대개별전화번호
    String newAdrZip; // 우편번호
    String radr; // 주소
    String cntrCnfmDtm; // 계약일자
    String pdNm; // 상품명
    String pdGrpCd; // 상품군코드
    String pdGrpNm; // 상품군명
    String pdCd; // 상품코드
    String modelNm; // 모델명
    String scnYn; // 스캔여부
    String bfMnftCoId; // 이전제조번호
    String bcNo; // 제조번호
    String prdtYm; // 제조년월
    String channelNm; // 채널명(접수구분)
    String gnrdv; // 총괄단
    String rgrp; // 지역단
    String ogNm; // 접수자소속
    String prtnrKnm; // 접수자표시
    String cnslMoCn; // 접수내역
    String istDt; // 설치일자
    String useMcn; // 사용개월
    String svBizHclsfNm; // 서비스유형명
    String svBizDclsfNm; // 서비스유형상세명
    String cntrCstNo; // 상대고객번호
    String asRefriDvNm; // AS고객구분
    String bfsvcRefriDvNm; // BS고객구분
    String refriDvNm; // 유무상구분
    String egerCnrNm; // 담당센터
    String rpbLocaraCd; // 책임지역
    String ichrCnrSdingDidy; // AS담당센터(모종직배)
    String prtnr; // 담당엔지니어
    String prtnrPstnDv; // 직급구분
    String siteAwDvNm; // 현장수당항목
    String siteAwAmt; // 현장수당금액
    String rglvlGdCd; // 현장수당(급지)
    String awAmt; // 현장수당(금액)
    String contactCralLocaraTno; // 연락처(핸드폰) 1
    @DBDecField
    String contactMexnoEncr; // 연락처(핸드폰) 2
    String contactCralIdvTno; // 연락처(핸드폰) 3
    String fnlRcpdt; // 접수일자
    String fnlRcpHh; // 접수일시
    String vstDuedt; // 예정일자
    String vstExpHh; // 예정일시
    String vstChYn; // 변경
    String vstCnfmdt; // 확정일자
    String vstCnfmHh; // 확정일시
    String dtmChCausNm; // 변경원인
    String dtmChRsonNm; // 변경사유
    String dtmChRsonDtlCn; // 변경사유상세
    String smsFwYn; // SMS발송
    String promHh; // 약속시간
    String arvDtm; // 작업시간(도착)
    String vstFshDtm; // 작업시간(완료)
    String istDelay; // 설치지연일
    String wkLdtm; // 소요시간
    String wkPrgsStatNm; // 작업상태
    String wkCanRsonNm; // 취소사유
    String wkCanMoCn; // 취소상세내역
    String fgptDsbYn; // 사은품지급여부
    String badDvNm; // 불량구분
    String asLctNm; // 고장위치
    String asPhnNm; // 고장현상
    String asLctCdNm; // 고장위치상세
    String svProcsFomNm; // 처리형태
    String imptaRsonNm; // 귀책
    String asCdEyn; // AS코드없음
    String sftAcdnYn; // 안전사고
    String plsSvYn; // 플러스서비스
    String peslArtcDfrnYn; // 인적사항다름
    String cwtrWprsVal; // 수압(냉)
    String wwtWprsVal; // 수압(온)
    String per1mOlqVal; // 유량
    String istPlcTpCd; // 설치유형
    String rcvryOpt; // 복구옵션
    String asCausNm; // AS원인
    String svProcsCn; // 결과입력 상세
    String pu1Part; // 투입부품1
    String pu2Part; // 투입부품2
    String pu3Part; // 투입부품3
    String pu4Part; // 투입부품4
    String pu5Part; // 투입부품5
    String pu6Part; // 투입부품6
    String pu7Part; // 투입부품7
    String pu8Part; // 투입부품8
    String pu9Part; // 투입부품9
    String pu10Part; // 투입부품10
    String partCs; // 청구내역(부품비)
    String tcfee; // 청구내역(기술료)
    String bstrCs; // 청구내역(출장료)
    String etcCs; // 청구내역(기타비용)
    String rveCsTot; // 청구내역(전체)
    String csBilNo; // 결제내역(비용청구번호)
    String adpBilAmt; // 결제내역(합산청구)
    String cardStlm; // 결제내역(신용카드)
    String vacStlmAmt; // 결제내역(가상계좌)
    byte[] cstSignCnByte; // 고객서명(byte)
    String cstSignCn; // 고객서명
    String istImg; // 이미지 파일 존재 유무
    String istEnvrPhoPhDocId; // 설치환경사진 DOC_ID
    String istKitPhoPhDocId; // 설치키트사진 DOC_ID
    String istCelngPhoPhDocId; // 설치천장사진 DOC_ID
    String istEnvrPhoPhFileUid; // 설치환경사진 FILE_UID
    String istKitPhoPhFileUid; // 설치키트사진 FILE_UID
    String istCelngPhoPhFileUid; // 설치천장사진 FILE_UID
    String acpnPrtnrKnm; // 동행작업자명
    String acpnPrtnrGdNm; // 동행작업자직급
    String bfCntrNoSn; // 전상대코드

}
