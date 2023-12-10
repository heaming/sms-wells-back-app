package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncCenterLocalAreaTfDvo {
    String newAdrZip; /* 신주소우편번호 */
    String ctpvNm; /* 시도명 */
    String ctctyNm; /* 시군구명 */
    String emdNm; /* 읍면동명 */
    String chSn; /* 변경일련번호 */
    String mngerRglvlDvCd; /* 매니저급지구분코드 */
    String mngrDvCd; /* 관리자구분코드 */
    String brchOgId; /* 지점조직ID */
    String bfBrchOgId; /* 이전지점조직ID */
    String ichrLocaraCtrRsonCd; /* 담당지역조정사유코드 */
    String fnlMdfcDtm; /* 최종수정일시 */
    String mdfcBrchOgId; /* 수정지점조직ID */
    String mdfcIchrLocaraCtrRsonCd; /* 수정담당지역조정사유코드 */
}
