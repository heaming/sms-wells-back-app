package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbGiroDepositSaveDvo {
    //    String girodpmtrdvcd; // --구분코드
    //    String dpsn; // --일련번호
    //    String rvedt; //--수납일자
    //    String fntdt; //--이체일자
    //    String bnkcd; //--은행코드
    //    String bnkbrnccd; //--은행점포코드
    //    String giroindxno; //--지로색인번호
    //    String giroinqno; //--지로조회번호
    //    String pyamt; //--납입금액
    //    String girorvedvcd; //--지로수납구분코드
    //    String girofeedvcd; //--지로수수료구분코드
    //    String rmkcn; //--비고
    String giroDpMtrDvCd; // --구분코드
    String dpSn; // --일련번호
    String rveDt; //--수납일자
    String fntDt; //--이체일자
    String giroDpBnkCd; //--은행코드
    String bnkBrncCd; //--은행점포코드
    String giroIndxNo; //--지로색인번호
    String giroInqNo; //--지로조회번호
    String pyAmt; //--납입금액
    String giroRveDvCd; //--지로수납구분코드
    String giroFeeDvCd; //--지로수수료구분코드
    String rmkCn; //--비고
    String giroDpUldDtm; //
    String cntrNo; //계약상세번호
    String cntrSn;
    String cntr;
    String cstNo;
    String cstKnm;
    String procsErrTpCd; //오류코드

    String ogTpCd; /*조직유형코드*/
    String prtnrNo; /*파트너번호*/
    String ogId;
    String dpAmt; /*입금금액*/
    String dpCprcnfAmt; /*입금대사금액*/
}
