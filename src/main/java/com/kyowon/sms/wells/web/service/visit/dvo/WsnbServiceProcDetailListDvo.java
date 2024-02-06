package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbServiceProcDetailListDvo {

    String cnslNo;
    String cntrNo;
    String cntrSn;
    String rcgvpKnm;
    String cntrDtlNo;
    String cralLocaraTno;
    // 휴대전화암호화
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String cstSvAsnNo;
    String rnadr;
    String rdadr;
    String pdPrpVal20;
    String pdNm;
    String wkBcNo;
    String flBcNo;
    String cnslCn;
    String cnslMoCn;
    String vstFshDt;
    String vstFshHh;
    String prtnrNo;
    String prtnrKnm;
    String asCausCd;
    String asCausCdNm;
    String svProcsCn;
    // 고객서명 이미지 파일
    byte[] cstSignCnByte;
    String bzrno;
    String bizOk;
    String asLctCd;
    String asLctCdNm;
    String asPhnCd;
    String asPhnCdNm;
    String imptaRsonCd;
    String imptaRsonCdNm;
    String acpnPrtnrNo;
    String acpnPrtnrKnm;
    String tonepIstOptYn;
    String tonepIstOpt;
    String pblDt;
    String aplcDt;
    String aplcYn;
    String istLctDtlCn;
    String svBizDclsfCd;
}
