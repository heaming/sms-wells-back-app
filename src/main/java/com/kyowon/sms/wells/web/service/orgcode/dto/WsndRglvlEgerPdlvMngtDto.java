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
        String cnrOgId,     // 서비스센터
        String prtnrNo,     // 사번
        String prtnrKnm,    // 성명
        String pdlvNo,      // 출고지번호
        String apyStrtdt,   // 적용시작일
        String apyEnddt,    // 적용종료일
        String zip,         // 우편번호
        String pdlvAdr,     // 기본출고지 주소
        String pdlvNm,      // 출고지명
        String bstrZip,     // 출장출고지 우편번호
        String bstrPdlvAdr, // 출장출고지 주소
        String bstrPdlvNm,  // 출장 출고지명
        String apr,         // 승인
        String aprTeamNm,   // 승인팀명
        String aprPrtnrNo,  // 승인자 사번
        String aprPrtnrKnm, // 승인자 성명
        String bstrRson     // 출장사유
    ){}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SaveEgerReq")
    public record SaveEgerReq(
        String pdlvNm,          // 기본출고지
        String bstrPdlvNm,      // 출장출고지
        String apyStrtdt,       // 적용시작일
        String apyEnddt         // 적용종료일
    ){}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-ApporovalEgerReq")
    public record ApporovalEgerReq(
        String cnrOgId,  // 서비스센터
        String prtnrNo   // 사번
    ){}
}
