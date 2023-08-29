package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WcsbTbSsopPspcCstRlpplDtlDvo {
    // TB_SSOP_PSPC_CST_RLPPL_DTL (자녀정보)

    private String pspcCstRlpplId; /* 가망고객관계자ID */
    private String pspcCstId; /* 가망고객ID */
    private String pspcCstRlpplTpCd; /* 가망고객관계자유형코드 */
    private String rlpplKnm; /* 관계자한글명 */
    private String rlpplSexDvCd; /* 관계자성별구분코드 */
    private String rlpplBryyMmdd; /* 관계자생년월일 */
    private String rlpplGryCd; /* 관계자학년코드 */
    private String rlpplSchNm; /* 관계자학교명 */
    private Integer lrnnAge; /* 학습연령 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
