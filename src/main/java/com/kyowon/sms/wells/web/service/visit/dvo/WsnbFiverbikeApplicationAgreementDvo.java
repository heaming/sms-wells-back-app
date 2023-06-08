package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0150P01 피버바이크 신청동의서 화면
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.01
 */
@Setter
@Getter
public class WsnbFiverbikeApplicationAgreementDvo {
    String cntrNo;
    String cntrSn;
    String agreeYn; // 동의 여부
    String custNm; // 동의자명
    String signImage; // 사인이미지
}
