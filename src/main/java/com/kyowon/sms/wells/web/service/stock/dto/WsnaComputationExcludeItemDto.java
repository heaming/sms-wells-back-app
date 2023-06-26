package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0296P01 산출 제외품목 등록 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-23
 */

public class WsnaComputationExcludeItemDto {

    @Builder
    @ApiModel("WsnaComputationExcludeItemDto-SearchReq")
    public record SearchReq(
        // 조회년월
        @NotBlank
        String inqrYm,
        // 품목종류코드
        String itmKndCd,
        // 품목코드
        String itmPdCd,
        // 시작 SAP코드
        String strtSapCd,
        // 종료 SAP코드
        String endSapCd
    ) {}

    @Builder
    @ApiModel("WsnaComputationExcludeItemDto-SearchRes")
    public record SearchRes(
        // SAP코드
        String sapCd,
        // 품목코드
        String itmPdCd,
        // 품목명
        String itmPdNm,
        // 비고
        String rmkCn,
        // 등록일자
        String fstRgstDt,
        // 소속
        String deptNm,
        // 등록자
        String usrNm,
        // 관리년월
        String mngtYm,
        // 산출제외일련번호
        int cmptExcdSn,
        // 품목종류코드
        String itmKndCd
    ) {}

    @Builder
    @ApiModel("WsnaComputationExcludeItemDto-SaveReq")
    public record SaveReq(
        // row 상태
        @NotBlank
        String rowState,
        // 관리년월
        @NotBlank
        String mngtYm,
        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 산출제외일련번호
        Integer cmptExcdSn,
        // 품목종류코드
        @NotBlank
        String itmKndCd,
        // 비고내용
        @Size(max = 4000)
        String rmkCn
    ) {}

    @Builder
    @ApiModel("WsnaComputationExcludeItemDto-RemoveReq")
    public record RemoveReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 산출제외일련번호
        @Positive
        int cmptExcdSn
    ) {}

    @Builder
    @ApiModel("WsnaComputationExcludeItemDto-TransferReq")
    public record TransferReq(
        @NotBlank
        String inqrYm
    ) {}

}
