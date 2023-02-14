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
public class WsnaQomAsnStrWareDto {

    @ApiModel(value = "WsnaQomAsnStrWareDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        String ogId, /* 조직ID */
        String prtnrNo, /* 사번 */
        String prtnrKnm, /* 담당자명 */
        String wareNo, /* 창고번호 */
        String wareDvCd, /* 창고구분코드 */
        String hmnrscEmpno /* 인사사원번호 */
    ) {}

    @ApiModel(value = "WsnaQomAsnStrWareDto-SearchRes")
    public record SearchRes(
        String baseYm, /* 기준년월 */
        String prtnrNo, /* 파트너번호*/
        String prtnrKnm, /* 파트너명 */
        String ogId, /* 조직ID */
        String ogNm, /* 소속 */
        String bldCd, /*빌딩코드*/
        String bldNm, /* 빌딩 */
        String wareNm, /* 창고명 */
        String wareNo, /* 창고번호 */
        String hgrWareNm, /* 상위창고명 */
        String hgrWareNo, /* 상위창고번호 */
        String qomAsnApyYn, /*물량배정적용여부*/
        String didyDvCd, /*직배구분코드*/
        String adrUseYn, /*지정주소여부*/
        String wareAdrId, /*창고주소ID*/
        String rmkCn, /*비고*/
        String zipCd, /*우편번호*/
        String addr /*주소*/
    ) {}
    @ApiModel(value = "WsnaQomAsnStrWareDto-CreateReq")
    public record CreateReq(
        String prtnrNo, /* 파트너번호 */
        String qomAsnApyYn /* 물량배정적용여부 */
    ) {}
    @ApiModel(value = "WsnaQomAsnStrWareDto-prtnrsReq")
    public record prtnrsReq(
        @NotBlank
        String baseYm, /*기준년월*/
        String ogId /* 조직ID */
    ) {}

    @ApiModel(value = "WsnaQomAsnStrWareDto-prtnrRes")
    public record prtnrRes(
        String ogId, /* 조직ID */
        String prtnrNo, /* 파트너번호*/
        String prtnrKnm /* 파트너명*/
    ) {}

    @ApiModel(value = "WsnaQomAsnStrWareDto-ogReq")
    public record ogReq(
        @NotBlank
        String baseYm,
        String ogId, /* 조직ID */
        String ogLevlDvCd,
        String ogTpCd
    ) {}

    @ApiModel(value = "WsnaQomAsnStrWareDto-ogRes")
    public record ogRes(
        String ogId, /* 조직ID */
        String hgrOgId,
        String ogTpCd,
        String ogLevlDvCd,
        String ogCd,
        String ogNm,
        String bldCd,
        String bldNm,
        String hooOgTpCd,
        String hooPrtnrNo
    ) {}
}
