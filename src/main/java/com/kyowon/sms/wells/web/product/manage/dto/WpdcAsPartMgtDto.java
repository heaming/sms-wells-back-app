package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpdcAsPartMgtDto {

    @ApiModel(value = "WpdcAsPartMgtDto-SearchReq")
    public record SearchReq(
        String pdTpCd,
        String pdNm,
        String pdCd,
        String tempSaveYn,
        String pdClsfCd,
        String prdtCateHigh,
        String prdtCateMid,
        String sapMatCd,
        String modelNo
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SearchRes")
    public record SearchRes(
        String pdTpCd,
        String pdNm,
        String pdCd,
        String tempSaveYn,
        String pdClsfNm,
        String sellPrice,
        String sellTpCd,
        String channelId,
        String sellDurtion,
        String sapMatCd,
        String pdAbbrNm,
        String modelNo,
        String ostrCnrCd,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,
        String fstRgstUsrId,
        String fnlMdfcUsrId
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SaveReq")
    public record SaveReq(
        String pdCd,
        String pdGrpCd,
        @NotNull
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String pdCd, String pdGrpCd, @NotNull
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SearchHistRes")
    public record SearchHistRes(
        String histStrtDtm, /* 이력 시작일 */
        String histEndDtm, /* 이력 종료일 */
        String fnlMdfcDtm, /* 수정일 */
        String fnlMdfcDeptNm, /* 변경부서 */
        String fnlMdfcUsrNmSet, /* 수정자(사번) */
        String pdPrpGrpDvCds, /* 그룹 */
        String changeItems, /* 변경항목 */
        String prevValue, /* 변경전 내용 */
        String changeValue, /* 변경후 내용 */
        String codeGroupName, /* 변경컬럼이 그룹코드인 경우 */
        String pdUiTpCd /* 데이터 타입 */
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-ChangeSequence")
    public record ChangeSequence(
        String codeId, /* 변경차수 ID */
        String codeName /* 변경차수 명 */
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SearchHistReq")
    public record SearchHistReq(
        String pdCd,
        String pdTpCd,
        String changeSequence,
        String split
    ) {
        public SearchHistReq {
            split = PdProductConst.SPLIT_DEFAULT_CHAR;
        }
    }

    @Builder
    @ApiModel(value = "WpdcAsPartMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcProductDto.PropertyGroupCode> groupCodes
    ) {}

}
