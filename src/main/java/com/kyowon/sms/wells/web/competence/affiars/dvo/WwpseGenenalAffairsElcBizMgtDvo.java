package com.kyowon.sms.wells.web.competence.affiars.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwpseGenenalAffairsElcBizMgtDvo {
    private String rpotBizAsnId;     /* 보고서업무배정ID */
    private String rpotBizTpId;      /* 보고서업무유형ID */
    private String dgYn;             /* 대표여부 */
    private String dtaDlYn;          /* 데이터삭제여부 */
    private String ogTpCd;
    private String prtnrNo;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String prtnrKnm;
    private String ogTpNm;
    private String ogNm;
    private String ogCd;
}
