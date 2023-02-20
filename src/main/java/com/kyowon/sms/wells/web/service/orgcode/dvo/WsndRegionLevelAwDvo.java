package com.kyowon.sms.wells.web.service.orgcode.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.14
 */
@Setter
@Getter
public class WsndRegionLevelAwDvo {

    String rglvlDvCd; // 급지구분코드
    String bizRglvlCd; // 업무급지코드
    String apyStrtdt; // 유효시작일시
    String mmtLdtm; // 이동소요시간
    String mmtDstn; // 이동거리
    String rglvlGdCd; // 급지등급코드
    String rglvlAwAmt; // 급지수당금액
    String minPerManho; // 분당공수
    String rglvlWeit; // 급지비중
    String avVe; // 평균속도

}
