package com.kyowon.sms.wells.web.product.standard.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 상품 판매유형 변수 관리 DTO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
public class WpdySalesTypeVariableMgtDto {

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-SearchReq")
    public record SearchReq(
        String sellTpCd, /* 판매유형 */
        String rgltnVarbNm /* 변수명 */
    ) {}

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-SearchRes")
    public record SearchRes(
        String rgltnVarbNm, /* 변수명 */

        String fstRgstUsrId, /* 등록자 */
        String fnlMdfcUsrId, /* 수정자 */
        String fstRgstDtm, /* 등록일 */
        String fstRgstUsrNm, /* 등록자명 */
        String fnlMdfcDtm, /* 수정일 */
        String fnlMdfcUsrNm, /* 수정자명 */

        String sellTpCd, /* 판매유형코드 */
        Integer varbSn, /* 변수일련번호 */
        String choFxnDvCd, /* 선택고정구분코드 */
        String rgltnVarbId, /* 규칙변수ID */
        Long sortOdr, /* 정렬순서 */
        String tempSaveYn, /* 임시저장여부 */
        String dtaDlYn /* 데이터삭제여부 */

    ) {}

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-SaveReq")
    public record SaveReq(
        @NotEmpty
        List<WpdySalesTypeVariableMgtDto.TypeVariableBase> bases /* 기본정보 */
    ) {}

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-TypeVariableBase")
    public record TypeVariableBase(
        @NotBlank
        String sellTpCd, /* 판매유형코드 */
        Integer varbSn, /* 변수일련번호 */
        @NotBlank
        String choFxnDvCd, /* 선택고정구분코드 */
        String rgltnVarbId, /* 규칙변수ID */
        Long sortOdr, /* 정렬순서 */
        String tempSaveYn, /* 임시저장여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}
}
