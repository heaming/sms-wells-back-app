package com.kyowon.sms.wells.web.bond.consultation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncUnpaidGuideUrgentExcludeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 미납요금 안내/촉구 대상 제외관리 Search Request Dto
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentExcludeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String schFwDv /*발송구분*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 미납요금 안내/촉구 대상 제외관리 Search Result Dto
    @ApiModel("WbncUnpaidGuideUrgentExcludeDto-SearchRes")
    public record SearchRes(
        String bndCntcExcdOjId, /* 채권접촉제외대상ID */
        String fwDvNm, /* 발송구분명 */
        String cstNo, /* 고객번호 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String fstRgstDtm, /* 최초등록일시 */
        String usrNm /* 등록자명 */
    ) {}

    @ApiModel("WbncUnpaidGuideUrgentExcludeDto-SaveReq")
    public record SaveReq(
        String bndCntcExcdOjId, /* 채권접촉제외대상ID */
        String apyEnddt /* 적용종료일자 */
    ) {}
}
