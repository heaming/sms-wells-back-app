package com.kyowon.sms.wells.web.service.interfaces.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.08
 */
public class WsnbIstWorkInterfaceDto {

    @ApiModel(value = "WsnbIstWorkInterfaceDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String asIstOjNo, // AS설치대상번호
        @NotBlank
        String cntrNo // 계약번호
    ) {}

    @ApiModel(value = "WsnbIstWorkInterfaceDto-CreateRes")
    public record CreateRes(
        String result // 처리 결과
    ) {}

}
