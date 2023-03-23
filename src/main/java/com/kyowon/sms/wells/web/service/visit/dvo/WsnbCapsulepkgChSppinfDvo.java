package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
@Setter
@Getter
public class WsnbCapsulepkgChSppinfDvo {

    String sppDuedt; /* 배송예정일자 */
    String pdCd; /* 상품코드 */
    String afchPdCD; /* 변경후상품코드 */
    String filtPdCd; /* 필터상품코드 */
    String sellAmt; /* 판매금액 */
    String sppPrgsStatCd; /* 배송진행상태코드 */
    String istAddr; /* 설치주소 */
}
