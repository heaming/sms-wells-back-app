package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundApplicationExcelUploadDvo {
    private String rfndRcpNo; // 환불접수번호 
    private String cntrNo; // 계약번호 
    private String cntrSn;
    private String cstNo; // 고객번호
    private String exRfndRsonCn; // 예외환불 사유
    private String rfndCshAmt; // 현금 환불금액
    private String rfndCardAmt; // 카드 환불금액
    private String rfndBltfAmt; // 전금 금액
    private String rfndPsbResAmt; // 환불가능 잔액
    private String totRfndEtAmt; // 총환불가능 잔액

    private String rfndRcpDtlSn; /* 환불접수상세일련번호 */
    private String rveNo; // 수납상세.수납번호
    private String rfndDvCd; // 환불구분
    private String rfndDsbDvCd; // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
    private String cshRfndDvCd; // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불; 03.카드현금
    private String rfndAcDvCd; // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
    private String cshRfndFnitCd; // 지급은행
    private String cshRfndAcnoEncr; // 계좌번호
    private String cshRfndAcownNm; // 예금주
    private String cshRfndAdrsDvCd; // 수취인
    private String cshRfndDlgpsNm; // 대표자 확인
    private String startDay; // 승인일 시작
    private String endDay; // 승인일 종료
    private String rfndAkAmt; // 환불신청금액, 전금 요청금액(원)
    private String rfndDsbAmt; // 실지급액 (원)
    //    private String rfndAkAmt2; // 
    private String rfndRsonCd; // 환불사유코드

    private String cardRfndFnitCd; // 카드구분
    private String cardRfndCrcdnoEncr; // 카드번호
    private String cardRfndFer; // 카드 공제액
    private String rfndRsonCn; // 기타 환불사유 입력란

    private String bltfRfndDvCd; // 전금구분
    private String bltfRfndMbDvCd; // 회원구분
    private String bltfOjCntrSn; // 고객번호
    private String bltfBfVacNoEncr; // 전금전 가상계좌
    private String bltfAfVacNoEncr; // 전금후 가상계좌
    private String rfndEvidMtrFileId; // 증빙자료 파일첨부

}
