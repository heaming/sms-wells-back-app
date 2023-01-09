package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
@Setter
@Getter
public class WsncRpbAreaCodeDvo {

    String chLocaraCd; /* 변경 책임지역코드 */
    String fr2pLgldCd; /* 법정동코드 앞2자리 */
    String ctpvNm; /* 시도명 */
    String ctctyNm; /* 시군구명 */
    String lawcEmdNm; /* 법정읍면동명 */
    String amtdNm; /* 행정동명 */
    String apyStrtdt; /* 적용시작일자 */
    String apyEnddt; /* 적용종료일자 */

}
