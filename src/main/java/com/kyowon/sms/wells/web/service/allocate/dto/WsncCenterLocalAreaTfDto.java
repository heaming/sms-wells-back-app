package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncCenterLocalAreaTfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncCenterLocalAreaTfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String wareDvCd, /* 구분 */
        String wareAreaCd, /* 관리지역단 */
        String zipFrom, /* 우편번호From */
        String zipTo, /* 우편번호To */
        String rsonCd /* 이관사유 */
    ) {}

    @ApiModel(value = "WsncCenterLocalAreaTfDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String newAdrZip, /* 신주소우편번호 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String emdNm, /* 읍면동명 */
        String chSn, /* 변경일련번호 */
        String mngerRglvlDvCd, /* 매니저급지구분코드 */
        String mngrDvCd, /* 관리자구분코드 */
        String brchOgId, /* 지점조직ID */
        String bfBrchOgId, /* 이전지점조직ID */
        String ichrLocaraCtrRsonCd, /* 담당지역조정사유코드 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String mdfcBrchOgId, /* 수정지점조직ID */
        String mdfcIchrLocaraCtrRsonCd /* 수정담당지역조정사유코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncCenterLocalAreaTfDto-SearchRes")
    public record SearchRes(
        String newAdrZip, /* 신주소우편번호 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String emdNm, /* 읍면동명 */
        String chSn, /* 변경일련번호 */
        String mngerRglvlDvCd, /* 매니저급지구분코드 */
        String mngrDvCd, /* 관리자구분코드 */
        String brchOgId, /* 지점조직ID */
        String bfBrchOgId, /* 이전지점조직ID */
        String ichrLocaraCtrRsonCd, /* 담당지역조정사유코드 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String mdfcBrchOgId, /* 수정지점조직ID */
        String mdfcIchrLocaraCtrRsonCd /* 수정담당지역조정사유코드 */
    ) {}
}
