package com.kyowon.sms.wells.web.competence.voc.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpshFalseVisitMgtDvo {
    private String ocYm; /* 발생년월 */
    private Integer rgstSn; /* 등록일련번호 */
    private String prtnrNo; /* 파트너번호 */
    private String ogId; /* 조직ID */
    private String fsVstYm; /* 허위방문년월 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String hooPrtnrNo; /* 조직장파트너번호 */
    private String hooPrtnrNm; /* 조직장파트너명 */
    private String ocRsonCn; /* 발생사유내용 */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String ogTpCd; /* 조직유형 */

}
