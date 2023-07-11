package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbCreditCardApprovalInterfaceDvo {
    private String kwGrpCoCd; // 교원그룹코드
    private String itgDpNo; // 통합입금번호
    private String cardAprno; // 승인번호
    private String rveAkNo; // 수납요청번호
    private String cstNo; // 고객번호
    private String financeCd; // 카드사
    private String crdcdNo1; // 카드번호1
    private String crdcdNo2; // 카드번호2
    private String crdcdNo3; // 카드번호3
    private String crdcdNo4; // 카드번호4
    private String crdcdNo; // 카드번호4
    private String crdcdExpdtYm; // 카드유효기간
    private String crcdonrNm; // 카드주명
    private String bryyMmdd; // 생년월일
    private String bzrno; // 사업자등록번호
    private String incmdcYn; // 소득공제여부
    private String istmMcn; // 할부개월
    private String aprAmt; // 승인금액
    private String aprDtm; // 승인일시
    private String rveAkMthdCd; // 수납요청방식코드
    private String rveAkPhCd; // 수납요청경로코드
    private String rvePrtnrOgTpCd; // 수납요청파트너조직유형코드
    private String rvePrtnrNo; // 수납요청파트너번호
    private String crdcdDv; // 신용카드법인격구분코드
}
