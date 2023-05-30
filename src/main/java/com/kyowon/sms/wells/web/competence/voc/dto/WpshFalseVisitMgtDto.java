package com.kyowon.sms.wells.web.competence.voc.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpshFalseVisitMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 허위방문관리 Search Request Dto
    @Builder
    @ApiModel("WpshFalseVisitMngtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ocYm,

        String prtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 허위방문관리 Search Result Dto
    @ApiModel("WpshFalseVisitMngtDto-SearchRes")
    public record SearchRes(
        String ocYm, /* 발생년월 */

        Integer rgstSn, /* 등록일련번호 */
        String prtnrNo, /* 파트너번호 */
        String ogId, /* 조직ID */
        String fsVstYm, /* 허위방문년월 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String hooPrtnrNo, /* 조직장파트너번호 */
        String hooPrtnrNm, /* 조직장파트너명 */
        String ocRsonCn, /* 발생사유내용 */
        String dtaDlYn, /* 데이터삭제여부 */
        String ogTpCd, /* 조직유형 */
        String prtnrKnm, /* 파트너 명 */

        String cstKnm, /* 고객 명 */

        String cntrNoSn

    ) {}

    @ApiModel("WpshFalseVisitMngtDto-SaveReq")
    public record SaveReq(
        String ocYm, /* 발생년월 */
        Integer rgstSn, /* 등록일련번호 */
        @NotBlank
        String prtnrNo, /* 파트너번호 */
        String ogId, /* 조직ID */
        @NotBlank
        String fsVstYm, /* 허위방문년월 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        Integer cntrSn, /* 계약일련번호 */
        String hooPrtnrNo, /* 조직장파트너번호 */
        String hooPrtnrNm, /* 조직장파트너명 */
        String ocRsonCn, /* 발생사유내용 */
        String dtaDlYn, /* 데이터삭제여부 */

        String ogTpCd, /* 조직유형 */
        String rowState

    ) {}

    @ApiModel("WpshFalseVisitMngtDto-RemoveReq")
    public record RemoveReq(
        Integer rgstSn /* 등록일련번호 */

    ) {}

}
