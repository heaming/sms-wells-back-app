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

    String SPP_DUEDT; /* 배송예정일자 */
    String PD_CD; /* 상품코드 */
    String AFCH_PD_CD; /* 변경후상품코드 */
    String FILT_PD_CD; /* 필터상품코드 */
    String SELL_AMT; /* 판매금액 */
    String SPP_PRGS_STAT_CD; /* 배송진행상태코드 */
    String IST_ADDR; /* 설치주소 */
}
