package com.kyowon.sms.wells.web.competence.voc.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpshVocReceiptPsicMngtDvo {
    private String vocBizAsnId;
    private String ipvmProsTpCd;
    private String mattVocDvTpCd;
    private String ichrDeptId;
    private String vocBizTpDtlCd;
    private String dgYn;
    private String ogTpCd;
    private String prtnrNo;
    private String prtnrKnm;
    private String ogNm;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String mpno;

    private String rowState;
}
