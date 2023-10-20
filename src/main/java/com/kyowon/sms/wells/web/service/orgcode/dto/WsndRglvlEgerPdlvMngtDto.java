package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsndRglvlEgerPdlvMngtDto {
    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SearchReq")
    public record SearchReq(
        String inqrDt,      // 조회일자
        String ogCd,        // 서비스센터
        String fnitAprRsCd  // 승인여부
    ) {}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SearchRes")
    public record SearchRes(
        String chk,             // 체크박스
        String mngtSn,          // 관리일련번호
        String egerPrtnrOgTpCd, // 엔지니어파트너조직유형코드
        String deptNm,          // 서비스센터
        String empId,           // 사번
        String empNm,           // 성명
        String apldFr,          // 적용시작일
        String apldTo,          // 적용종료일
        String aprDt,           // 승인일자
        String aprHh,           // 승인시간
        String aprPrtnrNo,      // 승인파트너번호
        String cfrmYn,          // 승인여부
        String cfrmEmpNm,       // 승인자성명
        String cfrmEmpDeptNm,   // 승인팀명
        String bstrRsonCn,      // 출장사유내용
        String bsNm,            // 기본출고지명
        String bsAdd,            // 기본출고지주소
        String basicShpCd,      // 기본출고지번호
        String basicShp,        // 기본출고지번호
        String mvNm,            // 출장출고지명
        String mvAdd,           // 출장출고지주소
        String mvShpCd          // 출장출고지번호
    ){}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-SaveEgerReq")
    public record SaveEgerReq(
        @NotBlank
        String empId,
        @NotBlank
        String apldFr,      // 적용시작일
        String basicShpCd,  // 기본출고지
        String mvShpCd,     // 출장출고지
        String bstrRsonCn,  // 출장사유
        String apldTo       // 적용종료일
    ){}

    @ApiModel(value = "WsndRglvlEgerPdlvMngtDto-ApporovalEgerReq")
    public record ApporovalEgerReq(
        String cnrOgId,  // 서비스센터
        String prtnrNo   // 사번
    ){}
}
