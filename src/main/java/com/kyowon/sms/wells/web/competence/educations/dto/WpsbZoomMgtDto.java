package com.kyowon.sms.wells.web.competence.educations.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsbZoomMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  wells 교육관리 알려zoom 관리 Search Request Dto
    @Builder
    @ApiModel("WpsbZoomMgtDto-SearchReq")
    public record SearchReq(
        String rsbDvCd,
        String oneDepth,
        String twoDepth,
        String threeDepth
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************

    //  wells 교육관리 알려zoom 관리 Search Result Dto
    @ApiModel("WpsbZoomMgtDto-SearchRes")
    public record SearchRes(
        String svEducMnalId,
        String hgrSvEducMnalId,
        String rsbDvCd,
        String svEducMnalNm,
        String orgPath,
        String svEducMnalCn,

        Integer inqrLvTcnt,

        Integer expsrOdr
    ) {}
    @Builder
    @ApiModel("WpsbZoomMgtDto-SaveReq")
    public record SaveReq(
        String svEducMnalId, /* 서비스교육매뉴얼ID */
        @NotBlank
        String hgrSvEducMnalId, /* 상위서비스교육매뉴얼ID */
        @NotBlank
        String svEducMnalNm, /* 서비스교육매뉴얼명 */

        Integer inqrLvTcnt, /* 조회단계차수 */

        Integer expsrOdr, /* 노출순서 */

        String svEducCtgNm, /* 서비스교육카테고리명 */
        String svEducMnalCn, /* 서비스교육매뉴얼내용 */
        @NotBlank
        String rsbDvCd, /* 직책구분코드 */
        String dtaDlYn, /* 데이터삭제여부 */
        String rowState
    ) {}

    @ApiModel("WpsbZoomMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String svEducMnalId /* 서비스교육매뉴얼ID */
    ) {}

}
