package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundDtlDvo {


    private String aftRfndAkNo; /* 채번용 키값 */


    private String cstNo;
    private String rfndDvCd;
    private String rfndDsbDvCd;
    private String cshRfndDvCd;
    private String rfndCshAkAmt;
    private String rfndCardAkAmt;
    private String crdcdFeeAmt;
    private String crdcdFer;
    private String rfndBltfAkAmt;
    private String rfndRsonCd;
    private String rfndRsonCn;
    private String dtaDlYn;

    private String rfndRcpNo; /*환불접수번호*/
    private String rfndRcpPhCd; /*환불접수경로코드*/
    private String rfndRveDt; /*환불수납일자*/
    private String rfndPerfDt; /*환불실적일자*/
    private String rfndDsbDt; /*환불지급일자*/
    private String rfndProcsCn; /*환불처리내용*/
    private String rfndDsbDuedt; /*환불예정일자*/
    private String kwGrpCoCd; /*법인교원*/
    private String rfndAkNo;   /*환불요청번호*/
    private String cntrNo;      /*계약번호*/
    private String cntrSn;     /*계약일련번호*/
    private String rveNo;   /*수납번호*/
    private String rveSn;  /*수납일련번호*/
}
