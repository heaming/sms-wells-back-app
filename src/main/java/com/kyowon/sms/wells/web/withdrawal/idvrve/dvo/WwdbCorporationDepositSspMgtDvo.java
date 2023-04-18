package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WwdbCorporationDepositSspMgtDvo {
    String itgDpNo; // 통합입금번호
    String kwGrpCoCd; // 교원회사그룹코드
    String alncmpSuscOrdNo; // 구독주문번호
    String cntrDtlNo; // 계약상세번호
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String sellTpCd; // 판매유형
    //    String crpAcno; // 계좌번호
    //    String alncmpDprNm; // 입금자명
    String alncmpDpAmt; // 기본금액
    String alncmpFee; // 수수료
    String sumAmt; // 합계금액
    String cstY; // 고객년도
    String cstCd; // 고객코드
    String cstSeqn; // 고객순번

}
