package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndRglvlEgerPdlvMngtDto {
    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SearchReq")
    public record SearchReq(
        String inqrDt,      // 조회일자
        String ogId,        // 서비스센터
        String fnitAprRsCd  // 승인여부
    ) {}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SearchRes")
    public record SearchRes(
        String ogId,        // 서비스센터
        String prtnrNo,     // 사번
        String prtnrKnm,    // 성명
        String basPdlv,     // 기본출고지
        String basPdlvAdr,  // 기본출고지 주소
        String bstrPdlv,    // 기본출고지
        String bstrPdlvAdr  // 기본출고지 주소
    ){}
}
