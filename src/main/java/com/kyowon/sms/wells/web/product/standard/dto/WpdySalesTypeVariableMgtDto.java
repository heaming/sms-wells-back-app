package com.kyowon.sms.wells.web.product.standard.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WpdySalesTypeVariableMgtDto {

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-SearchReq")
    public record SearchReq(
        String sellTpCd, /* 판매유형 */
        String rgltnVarbNm /* 변수명 */
    ) {}

    @ApiModel(value = "WpdySalesTypeVariableMgtDto-SearchRes")
    public record SearchRes(
        String rgltnVarbNm, /* 변수명 */

        String fstRgstUsrId,
        String fnlMdfcUsrId,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,

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
        List<WpdySalesTypeVariableMgtDto.TypeVariableBase> bases
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
