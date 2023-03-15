package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcPriceMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpdcStandardMgtDto {

    @ApiModel(value = "WpdcStandardMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String pdCd,
        String pdTpCd,
        boolean isModifiedProp,
        boolean isModifiedPrice,
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel,
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl,
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl,
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl
    ) {}

    @Builder
    @ApiModel(value = "WpdcStandardMgtDto-SaveReq")
    public record SaveReq(
        String pdCd,
        String pdTpCd,
        boolean isModifiedProp,
        boolean isModifiedPrice,
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel,
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl,
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl,
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl
    ) {}

    @Builder
    @ApiModel(value = "WpdcStandardMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdDtl> tbPdbsPdDtl,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcPriceMgtDto.ProductPriceDetail> tbPdbsPdPrcDtl,
        List<ZpdcPriceMgtDto.ProductPriceFinalDetail> tbPdbsPdPrcFnlDtl,
        List<ZpdcPriceMgtDto.ProductDiscountPremiumDetail> tbPdbsPdDscPrumDtl,
        List<ZpdcProductDto.PropertyGroupCode> groupCodes,
        List<ZpdcRelationMgtDto.SearchProductRes> relProducts
    ) {}
}