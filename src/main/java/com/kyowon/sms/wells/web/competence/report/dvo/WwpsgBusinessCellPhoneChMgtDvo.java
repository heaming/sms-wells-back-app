package com.kyowon.sms.wells.web.competence.report.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class WwpsgBusinessCellPhoneChMgtDvo {
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
    private String rpotBizProcsStatCd;
    private String aplcDtm;
    private String fnlMdfcUsrNm;
    private String fnlMdfcDtm;
    private String fnlMdfcUsrId;
    private String procsSn;
    private String procsCn;

    private String bizAkCn;
    private String rpotBizAplcId;
    private String rntAplcTpCd;
    private String rntAplcTpNm;
    private String procsPrtnrNo;
    private String procsPrtnrNm;
    private String procsCralLocaraTno;
    @DBDecField
    private String procsMexnoEncr;
    @DBDecField(common = true)
    private String procsMexnoGbencr;
    private String procsCralIdvTno;

    private String rsbDvNm;
    private String sellCralLocaraTno;
    @DBDecField
    private String sellMexnoEncr;
    private String sellCralIdvTno;
    private String bldNm;
    private String bizCralTelChTpCd;
    private String bizCralTelChTpNm;
    private String chRqdt;
    private String sellPrtnrNo;
    private String sellPrtnrNm;
    private String bfchCralLocaraTno;
    @DBDecField
    @DBEncField
    private String bfchMexnoEncr;
    private String bfchCralIdvTno;
    private String afchCralLocaraTno;
    @DBDecField
    @DBEncField
    private String afchMexnoEncr;
    private String afchCralIdvTno;
    private String rcstPrtnrNo;
    private String rcstPrtnrNm;
    private String rcstCralLocaraTno;
    @DBDecField
    private String rcstMexnoEncr;
    private String rcstCralIdvTno;
    private String ogCd;
    private String ogNm;

}
