package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

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
        String wareLocaraCd, /*창고지역코드*/
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
        String wareCd, /*창고코드*/
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String wareLocaraCd, /* 창고지역코드 */
        String wareLocaraSn, /* 창고지역일련번호 */
        String hgrWareNo, /* 상위창고번호 */
        String wareNm, /* 창고명 */
        String wareIchrNo, /* 창고담당번호 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String hgrWare, /*상위창고*/
        String hgrWareNm, /*상위창고명*/
        //        String coCd, /* 회사코드 */
        String didyDvCd, /* 직배구분코드 */
        //        String sortDvVal, /* 정렬구분값 */
        String wareAdrId, /* 창고주소ID */
        //        String bldCd, /* 빌딩코드 */
        //        String bldNm, /* 빌딩명 */
        //        String ogCd, /* 조직코드 */
        String wareUseYn /* 창고사용여부 */
        //        String adrUseYn, /* 주소사용여부 */
        //        String apyStrtdt, /* 적용시작일자 */
        //        String apyEnddt, /* 적용종료일자 */
        //        String dtaDlYn, /* 데이터삭제여부 */
        //        String rmkCn /* 비고내용 */
    ) {}

    @ApiModel(value = "WsnaWarehouseOrganizationDto-CreateReq")
    @Builder
    public record CreateReq(
        @NotBlank
        String baseYm //기준년월
        //        String userId //사용자ID
    ) {}

}
