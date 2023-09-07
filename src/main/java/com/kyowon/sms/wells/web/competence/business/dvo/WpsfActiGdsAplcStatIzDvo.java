package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActiGdsAplcStatIzDvo {
    private String actiGdsAplcId; /* 활동물품신청ID */
    private Integer actiGdsAplcSn; /* 활동물품신청일련번호 */
    private String actiGdsAplcStatCd; /* 활동물품신청상태코드 */
    private String aplcDt; /* 신청일자 */
    private String aplcRsonCn; /* 신청사유내용 */
    private String sppDt; /* 배송일자 */
    private String sppBzsCd; /* 배송업체코드 */
    private String sppIvcNo; /* 배송송장번호 */
    private String dtaDlYn; /* 데이터삭제여부 */

}
