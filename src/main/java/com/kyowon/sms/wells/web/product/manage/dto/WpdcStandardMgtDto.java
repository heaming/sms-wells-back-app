package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcPriceMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 기준 상품 DTO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
public class WpdcStandardMgtDto {

    @ApiModel(value = "WpdcStandardMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String pdCd, /* 상품코드 */
        String pdTpCd, /* 상품유형코드 */
        boolean isModifiedProp, /* 상품기본속성 수정여부 */
        boolean isModifiedPrice, /* 상품가격 수정여부 */
        boolean isOnlyFileModified, /* 상품기본속성 첨부파일 단독 수정여부 */
        boolean isModifiedRelation, /* 연결상품 수정여부 */
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl, /* 상품상세속성 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel, /* 연결상품 */
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl, /* 상품가격상세 */
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl, /* 상품최종가격상세 */
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl /* 선택변수 */
    ) {}

    @Builder
    @ApiModel(value = "WpdcStandardMgtDto-SaveReq")
    public record SaveReq(
        String pdCd, /* 상품코드 */
        String pdTpCd, /* 상품유형코드 */
        boolean isModifiedProp, /* 상품기본속성 수정여부 */
        boolean isModifiedPrice, /* 상품가격 수정여부 */
        boolean isOnlyFileModified, /* 상품기본속성 첨부파일 단독 수정여부 */
        boolean isModifiedRelation, /* 연결상품 수정여부 */
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl, /* 상품상세속성 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel, /* 연결상품 */
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl, /* 상품가격상세 */
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl, /* 상품최종가격상세 */
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl /* 선택변수 */
    ) {}

    @Builder
    @ApiModel(value = "WpdcStandardMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl, /* 상품가격상세 */
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl, /* 상품최종가격상세 */
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl, /* 선택변수 */
        List<ZpdcProductDto.PropertyGroupCode> groupCodes, /* 상품그룹코드목록 */
        List<ZpdcRelationMgtDto.SearchProductRes> relProducts /* 연결상품정보목록 */
    ) {}
}