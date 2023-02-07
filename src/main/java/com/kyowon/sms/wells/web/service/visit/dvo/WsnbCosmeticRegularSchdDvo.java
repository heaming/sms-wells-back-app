package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO: 필요 서비스 개발 완료 후 추가 작업
 * <pre>
 * W-SV-S-0050 화장품 캡슐 정기구매 고객 스케줄 INSERT/UPDATE
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.01.25
 */
@Setter
@Getter
public class WsnbCosmeticRegularSchdDvo {

    String cntrNo;
    String cntrSn;
    String cancDt; // 취소일자 (임시)

}
