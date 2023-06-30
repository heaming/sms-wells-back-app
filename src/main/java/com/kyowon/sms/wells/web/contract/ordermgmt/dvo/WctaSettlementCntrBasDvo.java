package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaSettlementCntrBasDvo {
    String cstNo;// 고객번호
    String rveAkMthdCd;// 수납요청방식코드 (대면/비대면)
    String rveAkPrtnrNo;// 수납요청파트너번호
    String rveAkPrtnrOgTpCd;// 수납요청파트너조직유형코드
    String copnDvCd;
    String bryyMmdd;
    String bzrno;
}
