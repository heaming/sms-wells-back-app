package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WcsbTbSsopPspcCstDdlvHistDvo {
    // TB_SSOP_PSPC_CST_DDLV_HIST
    private String pspcCstId; /* 가망고객ID */
    private String pspcCstTpCd; /* 가망고객유형코드 */
    private String ogAsnStatCd; /* 조직배정상태코드 */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String ogId; /* 조직ID */
    private String ogTpCd; /* 조직유형코드 */
    private String prtnrNo; /* 파트너번호 */
    private String asnFshDtm; /* 배정완료일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
