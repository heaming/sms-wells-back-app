package com.kyowon.sms.wells.web.competence.affiars.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class WwpseGenenalAffairsElcBizAkDvo {
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

    private String rpotBizProcsStatCd;
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
    private String procsPrtnrNo;
    private String procsPrtnrKnm;
    private String procsCralLocaraTno;
    @DBDecField(common = true)
    private String procsMexnoGbencr;
    private String procsCralIdvTno;
    private String rcstPrtnrNo;
    private String rcstPrtnrNm;
    private String rcstCralLocaraTno;
    @DBDecField
    private String rcstMexnoEncr;
    private String rcstCralIdvTno;
    private List<AttachFileDto.AttachFile> attachFiles; /* 첨부 파일 */
}
