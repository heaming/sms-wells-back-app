package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-U-0218M01 - 급지 출고지 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2022.12.14
 */
public class WsndRegionLevelPdlvMgtDto {

    @ApiModel(value = "WsndRegionLevelPdlvMngtDto-SearchReq")
    public record SearchReq(
        String pdlvDvCd, /* 출고지구분코드 */
        String pdlvNm, /* 출고지명 */
        String ogId, /* 서비스센터ID */
        String applyDate /* 적용일자 */
    ) {}

    @ApiModel(value = "WsndRegionLevelPdlvMngtDto-SearchRes")
    public record SearchRes(
        String pdlvDvCd, /* 출고지구분코드 */
        String pdlvNo, /* 출고지번호 */
        String apyStrtdtMax, /* 적용시작일-최대값 */
        String apyStrtdt, /* 적용시작일 */
        String apyEnddt, /* 적용종료일 */
        String pdlvNm, /* 출고지명 */
        String zip, /* 우편번호 */
        String pdlvAdr, /* 출고지주소 */
        String pdlvDtlAdr, /* 출고지상세주소 */
        String cnrOgId /* 서비스센터ID */

    ) {}

    @ApiModel(value = "WsndRegionLevelPdlvMngtDto-FindRes")
    public record FindRes(
        String pdlvNo, /* 출고지번호 */
        String apyStrtdt, /* 적용시작일 */
        String dataDlYn /* 데이터삭제여부 */
    ) {}

    @Builder
    @ApiModel(value = "WsndRegionLevelPdlvMngtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 상태 */
        @NotBlank
        String pdlvNo, /* 출고지번호 */
        String pdlvDvCd, /* 출고지구분코드 */
        String pdlvNm, /* 출고지명 */
        String zip, /* 우편번호 */
        String pdlvAdr, /* 출고지주소 */
        String pdlvDtlAdr, /* 출고지상세주소 */
        String apyStrtdtOrigin, /* 수정전적용시작일 */
        String apyStrtdt, /* 적용시작일 */
        String apyEnddt, /* 적용종료일 */
        String cnrOgId /* 서비스센터ID */
    ) {}

}
