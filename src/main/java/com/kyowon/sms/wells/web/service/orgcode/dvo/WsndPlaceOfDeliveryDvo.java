package com.kyowon.sms.wells.web.service.orgcode.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.08
 */
@Setter
@Getter
public class WsndPlaceOfDeliveryDvo {

    private String pdlvDvCd; // 출고지구분코드
    private String pdlvNo; // 출고지번호
    private String apyStrtdt; // 적용시작일자
    private String apyEnddt; // 적용시작일자
    private String pdlvNm; // 출고지명
    private String zip; // 우편번호
    private String pdlvAdr; // 출고지주소
    private String pdlvDtlAdr; // 출고지상세주소
    private String cnrOgId; // 센터조직ID
    private String lawcEmdNm; // 읍면동명
    private String ildYn; // 섬구분
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록사용자ID
    private String apyStrtdtOrigin; // 적용시작일자(수정 전)
}
