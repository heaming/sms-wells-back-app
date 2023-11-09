package com.kyowon.sms.wells.web.competence.affiars.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwpseGenenalAffairsElcBizAkPsDvo {
    private String rpotBizAsnId;
    private String rpotBizTpId;
    private String dgYn;
    private String ogTpCd;
    private String prtnrNo;
    private String aplcnsPrtnrNo;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String prtnrKnm;

    private String rpotBizProcsStatNm;
    private String aplcDtm;
    private String fnlMdfcUsrNm;
    private String fnlMdfcDtm;
    private String fnlMdfcUsrId;
    private String procsSn;
    private String procsCn;

    private String bizAkCn;
    private String rpotBizAplcId;
    private String bizAkBldCd;
    private String bizAkBldNm;
    private String rntAplcTpCd;
    private String rntAplcTpNm;
    private String procsPrtnrKnm;
    private String procsCralLocaraTno;
    @DBDecField
    private String procsMexnoEncr;
    private String procsCralIdvTno;
    private String rcstPrtnrNo;
    private String rcstPrtnrNm;
    private String rcstCralLocaraTno;
    @DBDecField
    private String rcstMexnoEncr;
    private String rcstCralIdvTno;
}
