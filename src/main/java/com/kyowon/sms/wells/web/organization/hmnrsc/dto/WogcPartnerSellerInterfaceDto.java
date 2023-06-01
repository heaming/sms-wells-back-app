package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;

import javax.validation.constraints.NotBlank;

public class WogcPartnerSellerInterfaceDto {

    /**
     * 조직유형코드, 고객번호, 고객생년월일을 받아 판매자 정보를 조회 (서비스) Request Dto
     * @param sellOgTpCd 조직유형코드
     * @param cntrCstNo 고객번호
     * @param bryyMmdd 생년월일
     */
    @ApiModel(value = "WogcPartnerSellerInterfaceDto-SearchCustomerInfoReq")
    public record SearchWMReq(
        @JsonProperty("SELL_OG_TP_CD")
        String sellOgTpCd,
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd

    ) {}

    /**
     * 조직유형코드, 고객번호, 고객생년월일을 받아 판매자 정보를 조회 (서비스) Response Dto
     * @param errCd 오류코드
     * @param errNm 오류코드명
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너성명
     * @param cralTno 연락처
     * @param dgr3LevlOgCd 센터코드
     * @param dgr3LevlOgNm 센터명
     */
    @ApiModel(value = "WogcPartnerSellerInterfaceDto-SearchCustomerRes")
    public record SearchWMRes(
        @JsonProperty("ERR_CD")
        String errCd,
        @JsonProperty("ERR_NM")
        String errNm,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm,
        @JsonProperty("CRAL_TNO")
        String cralTno,
        @JsonProperty("DGR3_LEVL_OG_CD")
        String dgr3LevlOgCd,
        @JsonProperty("DGR3_LEVL_OG_NM")
        String dgr3LevlOgNm
    ) {}

    /**
     * 조직유형코드, 고객번호을 받아 최근 계약 판매자를 조회 (서비스) Request Dto
     * @param sellOgTpCd 조직유형코드
     * @param cntrCstNo 고객번호
     */
    @ApiModel(value = "WogcPartnerSellerInterfaceDto-SearchRecentContractReq")
    public record SearchRecentContractReq(
        @NotBlank
        @JsonProperty("SELL_OG_TP_CD")
        String sellOgTpCd,
        @NotBlank
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo

    ) {}

    /**
     * 조직유형코드, 고객번호을 받아 최근 계약 판매자를 조회 (서비스) Response Dto
     * @param errCd 오류코드
     * @param errNm 오류코드명
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너성명
     * @param cralTno 연락처
     * @param ogCd 센터코드
     * @param ogNm 센터명
     */
    @ApiModel(value = "WogcPartnerSellerInterfaceDto-SearchRecentContractRes")
    public record SearchRecentContractRes(
        @JsonProperty("ERR_CD")
        String errCd,
        @JsonProperty("ERR_NM")
        String errNm,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm,
        @JsonProperty("CRAL_TNO")
        String cralTno,
        @JsonProperty("DGR3_LEVL_OG_CD")
        String ogCd,
        @JsonProperty("DGR3_LEVL_OG_NM")
        String ogNm
    ) {}

}
