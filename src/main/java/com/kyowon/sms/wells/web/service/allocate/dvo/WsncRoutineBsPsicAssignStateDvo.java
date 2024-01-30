package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncRoutineBsPsicAssignStateDvo {
    String chk;
    String snRpblYn;
    String cntrCstNo;//new
    String cstGdCd;
    String cstGdNm;

    String cstCnttYn;
    String vstPrgsStatCd;
    String vstPrgsStatNm;
    String statDtl;
    String cntrNo;

    String cntrSn;
    String cntrDtlNo;
    String rcgvpKnm;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr; //휴대전화국번호암호화(휴대폰번호)

    String cralIdvTno;
    String locaraTno;
    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)
    String idvTno;
    String pdCd;

    String pdNm;
    String sellTpCd;
    String sellTpNm;
    String sellTpDtlCd;
    String sellTpDtlNm;

    String mngerRglvlDvCd;
    String mngerRglvlDvNm;
    String newAdrZip;
    String rndadr;
    String rltdSvBizDclsfCd;//new

    String clsfCdSrnPrntCn;//new
    String bfVstDuedt;
    String vstDuedt;
    String vstCnfmdt;
    String vstCnfmHh;

    String vstFshDt;
    String vstFshHh;
    String cstUnuitmCn;
    String fstRgstDtm;
    String l1HgrOgCd;//new

    String l2HgrOgCd;//new
    String l3HgrOgCd;//new
    String l1HgrOgNm;//new
    String l2HgrOgNm;//new
    String l3HgrOgNm;//new

    String cnfmPsicDvCd;//new
    String cnfmPsicPrtnrOgTpCd;//new
    String cnfmPsicPrtnrNo;//new
    String prtnrKnm;
    String pstnDvCd;

    String pstnDvNm;//new
    String siteAwAtcCd;
    String siteAwAtcNm;
    String engPrtnrGdCd;//new
    String awAmt;

    String svProcsCn;
    String cstSignCn;
    String bgColo;
    String cntrDtlStatCd;
    String cntrDtlStatNm;

    String cstSvAsnNo;
}
