package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncBsPeriodCustomerTfDvo {
    String asnOjYm;
    String tfStatCd;
    String cntrNo;
    String cntrSn;
    String rcgvpKnm;
    String assign;
    String svpdSapCd;
    String pdctPdCd;
    String svpdNmAbbr1;
    String svBizDclsfCd;
    String ctpvNm;
    String ctctyNm;
    String emdNm;
    String istNmnN;
    String locaraTno;
    @DBDecField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String newAdrZip;
    String rnadr;
    String vstCnfmdt;
    String vstCnfmHh;
    String tfAkRmkCn;
    String bfchIchrOgNm;
    String bfchIchrPrtnrNo;
    String bfchIchrPrtnrKnm;
    String afchIchrOgNm;
    String afchIchrPrtnrNo;
    String afchIchrPrtnrKnm;
    String tfRqdt;
    String tfAkRsonCd;
    String tfOgNm;
    String tfPrtnrKnm;
    String tfFnCnfmdt;
    String tfFnOgNm;
    String tfFnPrtnrKnm;
}
