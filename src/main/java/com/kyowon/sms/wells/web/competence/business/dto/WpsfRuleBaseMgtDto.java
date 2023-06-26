package com.kyowon.sms.wells.web.competence.business.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpsfRuleBaseMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 규정 및 기준관리 Search Request Dto
    @Builder
    @ApiModel("WpsfRuleBaseMgtDto-SearchReq")
    public record SearchReq(
        String urgnYN,
        String oneDepth,
        String twoDepth,
        String threeDepth

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 규정 및 기준관리 Search Result Dto
    @ApiModel("WpsfRuleBaseMgtDto-SearchRes")
    public record SearchRes(
        String bznsSpptMnalId, /* 영업지원매뉴얼ID */
        String vlStrtDtm, /* 유효시작일시 */
        String vlEndDtm, /* 유효종료일시 */
        String bznsSpptMnalNm, /* 영업지원매뉴얼명 */
        String hgrBznsSpptMnalId, /* 상위영업지원매뉴얼ID */
        String bznsSpptMnalRgstCd, /* 영업지원매뉴얼등록코드 */
        String bznsSpptMnalChCn, /* 영업지원매뉴얼변경내용 */
        String bznsSpptMnalMpblDvCd, /* 영업지원매뉴얼공개구분코드 */
        String mnalRghRelId, /* 매뉴얼권한관계ID */
        Integer inqrLvTcnt, /* 조회단계차수 */
        Long expsrOdr, /* 노출순서 */
        String apnFileDocId, /* 첨부파일문서ID */
        String orgPath,
        String fnlMdfcUsrNm,
        String fnlMdfcDt,

        String dtaDlYn

    ) {}

    @Builder
    @ApiModel("WpsfRuleBaseMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String bznsSpptMnalId, /* 영업지원매뉴얼ID */
        String vlStrtDtm, /* 유효시작일시 */
        String vlEndDtm, /* 유효종료일시 */
        @NotBlank
        String bznsSpptMnalNm, /* 영업지원매뉴얼명 */
        @NotBlank
        String hgrBznsSpptMnalId, /* 상위영업지원매뉴얼ID */
        String bznsSpptMnalRgstCd, /* 영업지원매뉴얼등록코드 */
        String bznsSpptMnalChCn, /* 영업지원매뉴얼변경내용 */
        String bznsSpptMnalMpblDvCd, /* 영업지원매뉴얼공개구분코드 */
        String mnalRghRelId, /* 매뉴얼권한관계ID */
        Integer inqrLvTcnt, /* 조회단계차수 */
        Long expsrOdr, /* 노출순서 */
        String apnFileDocId, /* 첨부파일문서ID */
        List<String> rsbDvCds, /* 직책구분코드 */
        List<AttachFile> attachFiles,
        String ogTpCd

    ) {}

}
