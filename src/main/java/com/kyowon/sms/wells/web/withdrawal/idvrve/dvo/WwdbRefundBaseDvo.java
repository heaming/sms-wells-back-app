package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundBaseDvo {
    private String kwGrpCoCd;
    private String rfndAkNo;
    private String aftRfndAkNo; /* 채번용 키값 */
    private String rfndAkDtm;
    private String rfndAkPrtnrOgTpCd;
    private String rfndAkPrtnrNo;
    private String arfndYn;
    private String cshRfndFnitCd;
    private String cshRfndAcnoEncr;
    private String cshRfndAcownNm;
    private String rfndCshAkSumAmt;
    private String rfndCardAkSumAmt;
    private String rfndBltfAkSumAmt;
    private String crdcdFeeSumAmt;
    private String rfndAkStatCd;
    private String rfndProcsDvCd;
    private String rfndProcsCn;
    private String rfndProcsUsrId;
    private String rveCoCd;
    //
    private String rveDt; /* 수납일자 */
    private String perfDt; /* 실적일자 */
    private String dsbDt; /* 지급일자*/
    private String procsDv; /* 처리구분 */
    private String procsCn; /* 처리내용 */
}
