package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbSamsungManufactureNoInqrDto {

    @ApiModel(value = "WsnbSamsungManufactureNoInqrDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String startDt,  // 등록일자시작

        @NotBlank
        String endDt,    // 등록일자종료

        String ssPdctBcNo,  // 삼성제조번호

        String cntrDtlNo,  // 계약상세번호 (CNTR_NO || '-' || CNTR_SN)

        String saleCd,  // 판매코드

        String rcgvpKnm  // 고객명

    ) {}
    @ApiModel(value = "WsnbSamsungManufactureNoInqrDto-SearchRes")
    public record SearchRes(
        String cntrNo,  // 계약번호

        String cntrSn,  // 계약상세일련번호

        String rcgvpKnm,  // 고객명

        String sellTpNm,  // 고객유형

        String saleCd, // 판매코드

        String sapMatCd, // SAP코드

        String pdCd, // 품목코드

        String pdNm, // 상품명

        String ssPdctBcNo,  // 삼성제조번호

        String fstRgstDt,  // 등록일자

        String prtnrKnm,  // 담당자

        String ogNm,  // 조직명, 등록지점

        String wkPsicDvCd // 담당자구분
    ) {}
}
