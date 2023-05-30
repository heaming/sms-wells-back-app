package com.kyowon.sms.wells.web.competence.education.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpsbZoomMngtDto {
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

        Long expsrOdr, /* 노출순서 */

        String rowState
    ) {}
    @Builder
    @ApiModel("WpsbZoomMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String svEducMnalId, /* 서비스교육매뉴얼ID */
        @NotBlank
        String hgrSvEducMnalId, /* 상위서비스교육매뉴얼ID */
        @NotBlank
        String svEducMnalNm, /* 서비스교육매뉴얼명 */

        Integer inqrLvTcnt, /* 조회단계차수 */

        Long expsrOdr, /* 노출순서 */

        String svEducCtgNm, /* 서비스교육카테고리명 */
        String svEducMnalCn, /* 서비스교육매뉴얼내용 */
        @NotBlank
        String rsbDvCd, /* 직책구분코드 */
        String dtaDlYn, /* 데이터삭제여부 */
        String rowState
    ) {}

    @ApiModel(value = "WpsbZoomMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String hgrSvEducMnalId, /* 상위서비스교육매뉴얼ID */
        List<SaveReq> treeList
    ) {}

    @ApiModel("WpsbZoomMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String svEducMnalId /* 서비스교육매뉴얼ID */
    ) {}

}
