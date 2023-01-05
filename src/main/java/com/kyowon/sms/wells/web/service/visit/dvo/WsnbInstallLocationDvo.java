package com.kyowon.sms.wells.web.service.visit.dvo;

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
public class WsnbInstallLocationDvo {

    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String dtlSn; /* 상세일련번호 */
    String istLctDtlCn; /* 설치위치상세 */
    String wkPrtnrNo; /* 작업파트너번호 */
}
