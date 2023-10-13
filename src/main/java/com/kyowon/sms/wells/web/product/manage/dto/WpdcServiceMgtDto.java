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
        String pdCd, /* 상품코드 */
        String pdGrpCd, /* 상품그룹코드 */
        boolean isModifiedProp, /* 상품기본속성 수정여부 */
        boolean isOnlyFileModified, /* 상품기본속성 첨부파일 단독 수정여부 */
        boolean isModifiedRelation, /* 연결상품 수정여부 */
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel /* 연결상품 */
    ) {}

    @Builder
    @ApiModel(value = "WpdcServiceMgtDto-SaveReq")
    public record SaveReq(
        String pdCd, /* 상품코드 */
        String pdGrpCd, /* 상품그룹코드 */
        boolean isModifiedProp, /* 상품기본속성 수정여부 */
        boolean isOnlyFileModified, /* 상품기본속성 첨부파일 단독 수정여부 */
        boolean isModifiedRelation, /* 연결상품 수정여부 */
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRel /* 연결상품 */

    ) {}

    @Builder
    @ApiModel(value = "WpdcServiceMgtDto-ProductInfoRes")
    public record ProductInfoRes(
        ZpdcProductDto.TbPdbsPdBas tbPdbsPdBas, /* 상품기본속성 FRONT pdConst.js 동기화 */
        List<ZpdcProductDto.PropertyGroupCode> groupCodes, /* 상품속성그룹목록 */
        List<ZpdcProductDto.TbPdbsPdEcomPrpDtl> tbPdbsPdEcomPrpDtl, /* 각사송성 */
        List<ZpdcRelationMgtDto.SearchProductRes> relProducts /* 연결상품 */
    ) {}
}
