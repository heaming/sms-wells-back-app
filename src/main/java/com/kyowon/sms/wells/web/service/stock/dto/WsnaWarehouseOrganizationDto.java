package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.12.08
 */

public class WsnaWarehouseOrganizationDto {

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /*기준년월*/
        String codeUseYn, /*사용여부*/
        String wareDtlDvCd, /*창고상세구분코드*/
        String wareNoM, // 상위창고번호
        String wareNoD, // 개인창고번호
        String wareMngtPrtnrNo, /*창고관리파트너번호*/
        String wareDvCd /*창고구분*/
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-CountReq")
    public record CountReq(
        @NotBlank
        String baseYm //기준년월

    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchRes")
    public record SearchRes(
        String apyYm, /* 적용년월 */
        String wareNo, /* 창고번호 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String wareLocaraCd, /* 창고지역코드 */
        String wareLocaraSn, /* 창고지역일련번호 */
        String hgrWareNo, /* 상위창고번호 */
        String wareNm, /* 창고명 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String dsnBldNm, /*지정빌딩명*/
        String adrUseYn, /*주소사용여부*/
        String hgrWareNm, /*상위창고명*/
        String didyDvCd, /* 직배구분코드 */
        String sortDvVal, /* 정렬구분값 */
        String wareStocMgr, /*관리자*/
        String wareAdrId, /* 창고주소ID */
        String adrNm, /* 주소명 */
        String bldNm, /* 빌딩명 */
        String ogCd, /* 조직코드 */
        String wareUseYn /* 창고사용여부 */
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-CreateReq")
    @Builder
    public record CreateReq(
        @NotBlank
        String baseYm //기준년월
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-FindRes")
    public record FindRes(
        String apyYm, // 기준년월
        String wareDvCd, // 창고구분코드
        String wareDtlDvCd, // 창고상세구분코드
        String wareNo, // 창고번호
        String prtnrNo, // 관리자번호
        String prtnrKnm, // 관리자명
        String wareNm, // 창고명
        String hgrWareNo, // 상위창고번호
        String hgrWareNm, // 상위창고명
        String rmkCn, // 비고내용
        String wareUseYn, // 창고사용여부
        String fstRgstDt, // 최초등록일
        String adrUseYn, // 주소사용여부
        String bldCd, // 빌딩코드
        String bldCdNm, //빌딩코드명
        String wareAdrId, // 창고주소ID
        String sortDvVal, // 정렬구분값
        String rnadr, // 도로명주소
        String rdadr, // 도로명상세주소
        String newAdrZip, //신주소우편번호
        String ogId, // 조직ID
        String ogCd, // 조직코드
        String ogNm, // 조직명
        String dgr1LevlOgCd, //1차레벨조직코드
        String dgr1LevlOgId, //1차레벨조직ID
        String dgr1LevlOgNm, //1차레벨조직명
        String dgr1LevlOgCdNm, //1차레벨조직코드명
        String dgr2LevlOgCd, //2차레벨조직코드
        String dgr2LevlOgNm, //2차레벨조직명
        String dgr2LevlOgId, //2차레벨조직ID
        String dgr2LevlOgCdNm //2차레벨조직코드명
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String apyYm, // 기준년월
        @NotBlank
        String wareDvCd, // 창고구분코드
        @NotBlank
        String wareDtlDvCd, // 창고상세구분코드
        String wareNo, // 창고번호
        String prtnrNo, // 관리자번호
        String prtnrKnm, // 관리자명
        String wareNm, // 창고명
        String hgrWareNo, // 상위창고번호
        String hgrWareNm, // 상위창고명
        String ogTpCd, /* 조직유형코드 */
        String rmkCn, // 비고내용
        String wareUseYn, // 창고사용여부
        String fstRgstDt, // 최초등록일
        @NotBlank
        String adrUseYn, // 주소사용여부
        String bldCd, // 빌딩코드
        String wareAdrId, // 창고주소ID
        String rnadr, // 도로명주소
        String rdadr, // 도로명주소상세
        String newAdrZip, // 신주소우편번호
        String ogId, // 조직ID
        String ogNm, // 조직명
        String dgr1LevlOgId, // 1차조직레벨ID
        String dgr1LevlOgNm, // 1차조직레벨명
        String dgr2LevlOgId, // 2차조직레벨ID
        String dgr2LevlOgNm, // 2차조직레벨명
        String sortDvVal, // 정렬구분값
        String orglhgrWareNo // 기존상위창고번호
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        String ogId, // 조직ID
        @NotBlank
        String wareDvCd, // 창고구분코드
        @NotBlank
        String wareDtlDvCd // 창고상세구분코드
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId, //코드ID
        String codeName, //코드NAME
        String sortDvVal, // 정렬구분값
        String isSameOg // 동일조직여부
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchBuildingReq")
    public record SearchBuildingReq(
        String dgr1LevlOgId, // 1차레벨 조직ID
        String dgr2LevlOgId // 2차레벨 조직ID
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-SearchBuildingRes")
    public record SearchBuildingRes(
        String bldCd, // 빌딩코드
        String bldCdNm, // 빌딩코드명
        String bldNm, // 빌딩명
        String bldAdr, // 빌딩주소
        String adrId, // 주소ID
        String rnadr, // 도로명주소
        String rdadr, // 도로명주소상세
        String newAdrZip // 신주소우편번호
    ) {}

}
