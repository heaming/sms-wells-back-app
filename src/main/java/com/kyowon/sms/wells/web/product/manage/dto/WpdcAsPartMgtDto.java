package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 상품 >> AS부품 관리 Dto
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
public class WpdcAsPartMgtDto {

    @ApiModel(value = "WpdcAsPartMgtDto-SearchReq")
    public record SearchReq(
        String pdTpCd, /* 상품유형코드 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String tempSaveYn, /* 임시저장유무 */
        String pdClsfCd, /* 상품분류체계 코드 */
        String prdtCateHigh, /* 상품 대분휴 */
        String prdtCateMid, /* 상품 중분류 */
        String prdtCateLow, /* 상품 소분류 */
        String sapMatCd, /* SAP 자재코드 */
        String asMatCd, /* AS자재번호 */
        String sapItemCdFrom, /* 품목코드 */
        String sapItemCdTo, /* 품목코드 */
        String asMatItmKndCd, /* 각사속성상세 - AS자재 품목그룹 */
        String asMatItmGrpCd, /* 각사속성상세 - AS자재 품목종류 */
        String svMatGrpCd, /* 각사속성상세 - 자재그룹 */
        String modelNo /* 모델번호 */
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SearchRes")
    public record SearchRes(
        String pdTpCd, /* 상품유형코드 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String tempSaveYn, /* 임시저장유무 */
        String pdClsfNm, /* 상품분륲체계 코드 */
        String sellPrice, /* 판매가격 */
        String sellTpCd, /* 판매유형코드 */
        String channelId, /* 채널ID */
        String sellDurtion, /* 판매기간 */
        String sapMatCd, /* SAP 자재코드 */
        String sapMatTpVal, /* SAP자재유형값 */
        String pdAbbrNm, /* 상품약어명 */
        String modelNo, /* 모델No */
        String ostrCnrCd, /* 출고센터코드 */
        String asItmCd, /* 각사속성상세 - 품목코드 */
        String asMatCd, /* 각사속성상세 - AS지재번호 */
        String asMatEngNm, /* 각사속성상세 - 품목명 */
        String asMatItmKndCd, /* 각사속성상세 - AS자재 품목그룹 */
        String asMatItmGrpCd, /* 각사속성상세 - AS자재 품목종류 */
        String svMatGrpCd, /* 각사속성상세 - 자재그룹 */
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,
        String fstRgstUsrId,
        String fnlMdfcUsrId
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-SaveReq")
    public record SaveReq(
        String pdCd, /* 상품코드 */
        String pdGrpCd, /* 상품군코드 */
        boolean isModifiedProp, /* 변경여부와 관련된 내부 변수 */
        boolean isOnlyFileModified, /* 변경여부와 관련된 내부 변수 */
        @NotNull
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas,
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl
    ) {}

    @ApiModel(value = "WpdcAsPartMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String pdCd, /* 상품코드 */
        String pdGrpCd, /* 상품군코드 */
        boolean isModifiedProp, /* 변경여부와 관련된 내부 변수 */
        boolean isOnlyFileModified, /* 변경여부와 관련된 내부 변수 */
        @NotNull
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 기본정보 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl, /* 상세정보 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl /* 각사속성정보 */
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
        String pdCd, /* 상품코드 */
        String pdTpCd, /* 상품유형코드 */
        String changeSequence, /* 차수 */
        String split /* 구분자 - 내부변수 */
    ) {
        public SearchHistReq {
            split = PdProductConst.SPLIT_DEFAULT_CHAR;
        }
    }

    @Builder
    @ApiModel(value = "WpdcAsPartMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 기본정보 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl, /* 상세정보 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사속성정보 */
        List<ZpdcProductDto.PropertyGroupCode> groupCodes /* META 그룹코드 */
    ) {}

}
