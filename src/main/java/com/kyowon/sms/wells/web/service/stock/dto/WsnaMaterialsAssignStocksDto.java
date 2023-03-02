package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-01-19
 */
public class WsnaMaterialsAssignStocksDto {

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        String ogId, /* 조직ID */
        String prtnrNo, /* 사번 */
        String prtnrKnm, /* 담당자명 */
        String wareNo, /* 창고번호 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String ogTpCd /* 조직유형코드 */
    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-SearchRes")
    public record SearchRes(
        String baseYm, /* 기준년월 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호*/
        String prtnrKnm, /* 파트너명 */
        String ogId, /* 조직ID */
        String ogCd, /* 조직코드 */
        String ogNm, /* 소속 */
        String hgrOgId, /* 상위조직ID */
        String bldCd, /*빌딩코드*/
        String bldNm, /* 빌딩 */
        String wareNm, /* 창고명 */
        String wareNo, /* 창고번호 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String hgrWareNm, /* 상위창고명 */
        String hgrWareNo, /* 상위창고번호 */
        String qomAsnApyYn, /*물량배정적용여부*/
        String didyDvCd, /*직배구분코드*/
        String didyDvNm, /*독립매니저여부*/
        String adrUseYn, /*지정주소여부*/
        String wareAdrId, /*창고주소ID*/
        String rdadr, /*주소*/
        String newAdrZip, /*우편번호*/
        String rmkCn /*비고*/
    ) {}
    @ApiModel(value = "WsnaMaterialsAssignStocksDto-CreateReq")
    public record CreateReq(
        String prtnrNo, /* 파트너번호 */
        String qomAsnApyYn /* 물량배정적용여부 */
    ) {}
    @ApiModel(value = "WsnaMaterialsAssignStocksDto-prtnrsReq")
    public record PrtnrReq(
        @NotBlank
        String baseYm, /*기준년월*/
        String ogId /* 조직ID */
    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-prtnrRes")
    public record PrtnrRes(
        String ogId, /* 조직ID */
        String prtnrNo, /* 파트너번호*/
        String prtnrKnm /* 파트너명*/
    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-ogReq")
    public record OgReq(
        @NotBlank
        String baseYm,
        String ogId, /* 조직ID */
        String ogLevlDvCd,
        String ogTpCd
    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-ogRes")
    public record OgRes(
        String ogId, /* 조직ID */
        String hgrOgId,
        String ogTpCd,
        String ogLevlDvCd,
        String ogCd,
        String ogNm,
        String hgrOgCd,
        String bldCd,
        String bldNm,
        String hooOgTpCd,
        String hooPrtnrNo,
        String hooPrtnrNm
    ) {}
}
