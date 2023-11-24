package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 대체전표 생성 DVO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-08-14
 */
@Getter
@Setter
@ToString
public class WwdbBillDepositSlipProcessingDvo {
    String zzsnum; // 전송번호
    String zkey; // 전표번호
    String lineseq; /*전표라인번호*/
    String ifnum;
    String zzavflg; // 증빙구분
    String blart; // 전표유형
    String bukrs; // 회사코드
    String zzuser;
    String budat; // 전기일
    String bldat; // 증빙일
    String bktxt; // 전표적요
    String shkzg; // 차대구분
    String lifnr; // 구매처
    String kunnr; // 고객
    String wrbtr; // 금액
    String umskz; // 특별지시자
    String hkont; // gl계정
    String empfb; // 대체지급인
    String bvtyp; // 지급계좌키
    String wmwst; // 부가세금액
    String mwskz; // 부가세코드
    String branch; // 부가세사업장
    String wtqsshh; // 원천세표준액
    String incomamt; // 소득세금액
    String inhabamt; // 주민세금액
    String withcd; // 원천세코드
    String secco; // 섹션코드
    String gsber; // 사업영역
    String sgtxt; // 항목적요
    String zfbdt; // 지급기산일
    String kostl; // 코스트센터
    String posid; // wbs
    String xref1; // 참조1
    String xref2; // 참조2
    String xref3; // 참조3
    String zzprdha; // 제품계층구조
    String prctr; // 손익센터
    String augbl; // 반제원전표번
    String belnr; // sap전표번호
    String stblg; // sap역분개전표
    String fund; // 자금
    String koinh; // 예금주
    String bankk;
    String bankn;
    String messge; // sap전표메시지
    String trdat; // 전송일
    String redat; // 반영일자
    String zfcgub;
    String zfidpt; // 작성부서
    String zfiman; // 작성담당
    String zfsymd;
    String zfrymd;
    String zfetc1;
    String zfetc2;
    String zfetc3;
    String fieymd;
    String fiehms;
    String fimymd;
    String fimhms;
}
