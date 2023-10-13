package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0099M01 서비스처리 내역(품질)
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.22
 */
@Setter
@Getter
public class WsnbServiceProcsIzQltyDvo {

    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cntrNoSn; // 계약상세번호
    String cstKnm; // 고객서비스배정번호
    String copnDvNm; // 고객명
    String cttIchr; // 법인격구분명
    String hsp; // 컨택담당
    String alncBzsNm; // 홈쇼핑
    String frisuYn; // 제휴회사명
    String sellTpNm; // 무료체험(무상여부)
    String cralLocaraTno; // 고객유형(판매유형명)
    @DBDecField
    String mexnoEncr; // 휴대전화국번호암호화
    String cralIdvTno; // 휴대개별전화번호
    String newAdrZip; // 우편번호
    String radr; // 주소
    String cntrCnfmDtm; // 계약일자
    String pdNm; // 상품명
    String modelNm; // 모델명
    String scnYn; // 스캔여부
    String bfMnftCoId; // 이전제조번호
    String bcNo; // 제조번호
    String prdtYm; // 제조년월
    String ogNm; // 접수자소속
    String prtnrKnm; // 접수자표시
    String cnslMoCn; // 접수내역
    String istDt; // 설치일자
    String useMcn; // 사용개월
    String svBizHclsfNm; // 서비스유형명
    String svBizDclsfNm; // 서비스유형상세명
    String cntrCstNo; // 상대고객번호
    String asRefriDvNm; // A/S고객구분
    String bfsvcRefriDvNm; // B/S고객구분
    String refriDvNm; // 유/무상구분
    String egerCnrNm; // 담당센터
    String prtnr; // 담당엔지니어
    String prtnrPstnDv; // 직급구분
    String siteAwDvNm; // 현장수당(항목명)
    String siteAwAmt; // 현장수당(금액)
    String awAmt; // 현장수당(금액)
    String contactCralLocaraTno; // 연락처(핸드폰) 1
    @DBDecField
    String contactMexnoEncr; // 연락처(핸드폰) 2
    String contactCralIdvTno; // 연락처(핸드폰) 3
    String fnlRcpdt; // 접수일자
    String vstDuedt; // 예정일자
    String vstChYn; // 변경여부
    String vstCnfmdt; // 확정일자
    String vstCnfmHh; // 확정일시
    String dtmChCausNm; // 변경원인
    String dtmChRsonNm; // 변경사유
    String dtmChRsonDtlCn; // 변경사유상세
    String smsFwYn; // SMS발송여부
    String promHh; // 약속시간
    String arvDtm; // 작업시간(도착)
    String vstFshDtm; // 작업시간(완료)
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
    String svProcs; // 상세(작업결과상세)
    String svProcsCn; // 상세내역
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
    String pu1Uprc; // 투입부품비용1
    String pu2Uprc; // 투입부품비용2
    String pu3Uprc; // 투입부품비용3
    String pu4Uprc; // 투입부품비용4
    String pu5Uprc; // 투입부품비용5
    String pu6Uprc; // 투입부품비용6
    String pu7Uprc; // 투입부품비용7
    String pu8Uprc; // 투입부품비용8
    String pu9Uprc; // 투입부품비용9
    String pu10Uprc; // 투입부품비용10
    String totalPuUprc; // 투입부품비용 총계
    String partCs; // 수납(부품비)
    String tcfee; // 수납(기술료)
    String bstrCs; // 수납(출장료)
    String etcCs; // 수납(기타비용)
    String rveCsTot; // 수납(전체)
    String cashStlm; // 현금
    String cardStlm; // 카드
    String elcStlm; // 전자결제
    byte[] cstSignCnByte; // 고객서명(byte)
    String cstSignCn; // 고객서명

}
