package com.kyowon.sms.wells.web.bond.credit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WbndRentalCbMgtMessageExcludeDto {
    @ApiModel("WbndRentalCbMgtMessageExcludeDto-SearchReq")
    public record SearchReq(
        String cstNo, /*고객번호*/
        @NotBlank
        String ctntExcdBndBizCd, /*연락제외채권업무코드*/
        @NotBlank
        String ctntExcdOjTpCd, /*연락제외대상유형코드*/
        @NotBlank
        String ctntExcdMediTpCd/*연락제외매체유형코드*/
    ) {}

    @ApiModel("WbndRentalCbMgtMessageExcludeDto-SearchRes")
    public record SearchRes(
        String cstNo, /*고객번호*/
        String apyStrtdt, /*적용시작일자*/
        String apyEnddt, /*적용종료일자*/
        String bndCntcExcdOjId, /*채권접촉제외대상ID*/
        String ctntExcdBndBizCd, /*연락제외채권업무코드*/
        String ctntExcdOjTpCd, /*연락제외대상유형코드*/
        String ctntExcdMediTpCd, /*연락제외매체유형코드*/
        String ctntExcdRsonDvCd, /*연락제외사유구분코드*/
        String fnlMdfcDtm
    ) {}

    @ApiModel("WbndRentalCbMgtMessageExcludeDto-SaveReq")
    public record SaveReq(
        String cstNo, /*고객번호*/
        String apyStrtdt, /*적용시작일자*/
        String apyEnddt, /*적용종료일자*/
        String bndCntcExcdOjId, /*채권접촉제외대상ID*/
        String ctntExcdBndBizCd, /*연락제외채권업무코드*/
        String ctntExcdOjTpCd, /*연락제외대상유형코드*/
        String ctntExcdMediTpCd, /*연락제외매체유형코드*/
        String ctntExcdRsonDvCd, /*연락제외사유구분코드*/
        String fnlMdfcDtm,
        String rowState
    ) {}
}
