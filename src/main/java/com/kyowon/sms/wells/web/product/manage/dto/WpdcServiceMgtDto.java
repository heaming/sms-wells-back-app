package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 서비스 상품 DTO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
public class WpdcServiceMgtDto {

    @ApiModel(value = "WpdcServiceMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String pdCd,
        String pdGrpCd,
        boolean isModifiedProp,
        boolean isOnlyFileModified,
        boolean isModifiedRelation,
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel
    ) {}

    @Builder
    @ApiModel(value = "WpdcServiceMgtDto-SaveReq")
    public record SaveReq(
        String pdCd,
        String pdGrpCd,
        boolean isModifiedProp,
        boolean isOnlyFileModified,
        boolean isModifiedRelation,
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel

    ) {}

    @Builder
    @ApiModel(value = "WpdcServiceMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.PropertyGroupCode> groupCodes,
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl,
        List<ZpdcRelationMgtDto.SearchProductRes> relProducts
    ) {}
}
