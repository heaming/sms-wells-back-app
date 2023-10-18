package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnyPdlvDto {
    @ApiModel(value = "WsnyPdlvDto-SearchRes")
    public record SearchRes(
        String pdlvNo,      // 출고지 번호
        String pdlvDvCd,    // 출고지 구분코드
        String pdlvKey,
        String pdlvNm,      // 출고지명
        String pdlvAdd      // 출고지 주소
    ){}
}
