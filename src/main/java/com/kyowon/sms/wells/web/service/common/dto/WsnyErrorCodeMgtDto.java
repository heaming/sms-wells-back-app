package com.kyowon.sms.wells.web.service.common.dto;

import static com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 상품별 에러코드 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.03
 */
public class WsnyErrorCodeMgtDto {

    @ApiModel(value = "WsnyErrorCodeMgtDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd

    ) {}

    @ApiModel(value = "WsnyErrorCodeMgtDto-SearchRes")
    public record SearchRes(
        String pdCd,
        String pdNm,
        String errCn,
        String errDvCn,
        String errCausCn,
        String errImageYn,
        String errImageUId,
        String errImageDocId
    ) {}

    @ApiModel("WsnyErrorCodeMgtDto-DeleteReq")
    public record DeleteReq(
        @NotBlank
        String pdCd,
        @NotBlank
        String errCn
    ) {}

    @ApiModel("WsnyErrorCodeMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String pdCd,
        @NotBlank
        String errCn,
        String errDvCn,
        String errCausCn,
        String errImageUId,
        String errImageDocId,
        List<AttachFile> attachFiles,
        String flag
    ) {}
}
