package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-01-19
 */
public class WsnaMaterialsAssignStocksDto {

    @Builder
    @ApiModel(value = "WsnaMaterialsAssignStocksDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String wareDvCd, /* 창고구분코드 */
        String hgrWareNo, /* 상위창고번호 */
        String wareNo, /* 창고번호 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String prtnrNo, /* 사번 */
        String prtnrKnm /* 담당자명 */
    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-SearchRes")
    public record SearchRes(
        // 사번
        String prtnrNo,
        // 성명
        String prtnrKnm,
        // 조직ID
        String ogId,
        // 조직유형
        String ogTpCd,
        // 소속
        String ogNm,
        // 빌딩코드
        String bldCd,
        // 빌딩
        String bldNm,
        // 상위창고
        String hgrWareNm,
        // 물량배정적용여부
        String qomAsnApyYn,
        // 독립 매니저 여부
        String didyDvNm,
        // 지정주소여부
        String adrUseYn,
        // 우편번호
        String adrZip,
        // 주소
        String adr,
        // 기준년월
        String baseYm,
        // 창고번호
        String wareNo,
        // 창고상세구분
        String wareDtlDvNm

    ) {}

    @ApiModel(value = "WsnaMaterialsAssignStocksDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String prtnrNo, /* 파트너번호 */
        @NotBlank
        String qomAsnApyYn, /* 물량배정적용여부 */
        @NotBlank
        String ogTpCd /* 조직유형코드 */
    ) {}
}
