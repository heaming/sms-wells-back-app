package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbServiceRefundDvo {

    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String rfndRcpNo; /*환불접수번호*/
    private String rveCoCd; /*수납회사코드*/
    private String cstNo; /*고객번호*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String rfndPrtnrOgTpCd; /*환불요청파트너조직유형코드*/
    private String rfndPrtnrNo; /*환불요청파트너번호*/
    private String rfndRcpPhCd; /*환불접수경로코드*/
    private String rfndAkDt; /*환불요청일자*/
    private String rfndDsbDuedt; /*환불완료일자*/
    private String cstSvAsnNo; /*고객서비스배정번호*/
    private String csBilNo; /*비용청구번호*/
    private String rfndCshAkAmt; /*환불현금금액*/
    private String rfndCardAkAmt; /*환불카드금액*/
    private String rfndBltfAkAmt; /*환불전금금액*/
    private String rfndDdtnAmt; /*환불공제금액*/
    private String crdcdFeeAmt; /*환불수수료금액*/
    private String bilAmt; /*청구금액*/
    private String rfndRveDt; /*환불수납일자*/
    private String rfndPerfDt; /*환불실적일자*/
    private String rfndDsbDt; /*환불지급일자*/

    private String rfrndTpCd; /*환불유형코드*/
    private String rfrndDvCd; /*환불구분코드*/
    private String rfrndDpKndCd; /*환불입금종류코드*/
    private String rfrndDbsDvCd; /*환불지급구분코드*/
    private String cshRfndDvCd; /*현금환불구분코드*/
    private String rfrndAcDvCd; /*환불계좌구분코드*/
    private String cshRfndFnitCd; /*현금환불금융기관코드*/
    private String cshRfndAcnoEncr; /*현금환불계좌번호암호화*/
    private String cshRfndAcownNm; /*현금환불계좌주명*/

    private String cardRfndFnitCd; /*카드환불금융기관코드*/
    private String cardRfndCrdcdAprStrtDt; /*카드환불신용카드승인시작일자*/
    private String cardRfndCrdcdAprEndDt; /*카드환불신용카드승인종료일자*/
    private String cardRfndCrcdnoEncr; /*카드환불신용카드번호암호화*/
    private String cardRfndCrdcdExpdtYm; /*카드환불신용카드유효기간*/
    private String cardRfndCrdcdIstmMcn; /*카드환불신용카드할부개월수*/
    private String cardRfndCrcdonrNm; /*카드환불신용카드주명*/
    private String cardRfndCrdcdAprno; /*카드환불신용카드승인번호*/
    private String cardRfndCrdcdAprAmt; /*카드환불신용카드승인금액*/
    private String cardRfndFee; /*카드환불수수료*/
    private String cardRfndFer; /*카드환불수수료율*/
    private String rfndRsonCd; /*환불사유코드*/
    private String rfndRsonCn; /*환불사유내용*/
    private String itgDpNo; /*통합입금번호*/
    private String rveAkNo; /*수납요청번호*/
    private String rveNo; /*수납번호*/
    private String rveAkPhCd; /*수납요청경로코드*/
    private String rfndAkAmt; /*실지급액*/
    private String rfndFeeYn; /*수수료존재 여부*/
    private String rveCd; /*수납코드*/

}
