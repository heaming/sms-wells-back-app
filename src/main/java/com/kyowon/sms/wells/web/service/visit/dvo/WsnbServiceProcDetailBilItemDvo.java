package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbServiceProcDetailBilItemDvo {

    String csBilAtcCd;
    String bilOjAmt;
    String bilCtrSumAmt;
    String bilRveAmt;

    String cttRcpDtm; // 컨택일[컨택접수일시]
    String cttDuedt; // 방문예정일[컨택예정일]
    String cttMoCn; // 전달사항[컨택요청내용]
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cttOjId; // 컨택대상ID
    String cttSn; // 컨택일련번호
    String wkKnd; // 작업구분
    String beforeRcpDtm; // 기등록 컨택일
    String beforeDuedt; // 기등록 방문예정일
    String beforeMoCn; // 기등록 전달사항
    String cttPrgsStatCd; // 컨택진행상태코드
    String cstNo; // 고객번호
}
