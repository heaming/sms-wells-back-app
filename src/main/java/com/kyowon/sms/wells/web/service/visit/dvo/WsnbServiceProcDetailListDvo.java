package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbServiceProcDetailListDvo {

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
    String adrId;
    String rnadr;
    String rdadr;
    String pdCd;
    String pdNm;
    String wkBcNo;
    String flBcNo;
    String cnslDtlpTpCd;
    String cnslDtlpTpNm;
    String cnslMoCn;
    String vstFshDt;
    String vstFshHh;
    String prtnrNo;
    String prtnrKnm;
    String asCausCd;
    String svProcsCn;
    // 고객서명 이미지 파일
    String cstSignCn;
    byte[] cstSignCnByte;
    String bzrno;
    String rc101BizOk;
    String asLctCd;
    String asPhnCd;
    String imptaRsonCd;
    String lnfDvCd;
    String tonepIstOptYn;
    String txinvPblD;
    String txinvPblYn;

}
