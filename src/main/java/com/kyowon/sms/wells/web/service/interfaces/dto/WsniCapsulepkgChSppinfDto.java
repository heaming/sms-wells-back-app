package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
public class WsniCapsulepkgChSppinfDto {

    @ApiModel(value = "WsnbCapsulepkgChSppinfDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /* 계약번호 */
        @JsonProperty("CNTR_SN")
        String cntrSn, /* 계약일련번호 */
        @JsonProperty("PAGE_INDEX")
        int pageIndex, /* 페이지 인덱스 */
        @JsonProperty("PAGE_SIZE")
        int pageSize /* 페이지 크기 */
    ) {}

    @ApiModel(value = "WsnbCapsulepkgChSppinfDto-SearchRes")
    public record SearchRes(
        @JsonProperty("SPP_DUEDT")
        String sppDuedt, /* 배송예정일자 */
        @JsonProperty("PD_CD")
        String pdCd, /* 상품코드 */
        @JsonProperty("PD_NM")
        String pdNm, /* 상품명 */
        @JsonProperty("AFCH_PD_CD")
        String afchPdCd, /* 변경후상품코드 */
        @JsonProperty("AFCH_PD_NM")
        String afchPdNm, /* 변경후상품명 */
        @JsonProperty("DT_INFO")
        String dtInfo, /* 패키지구성정보 */
        @JsonProperty("SELL_AMT")
        String sellAmt, /* 판매금액 */
        @JsonProperty("SPP_PRGS_STAT_CD")
        String sppPrgsStatCd, /* 배송진행상태코드 */
        @JsonProperty("IST_ADDR")
        String istAddr /* 설치주소 */
    ) {}

}
