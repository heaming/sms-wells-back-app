package com.kyowon.sms.wells.web.product.manage.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.service.ZpdcPriceMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcCompositionMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcCompositionMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 복합상품 관리 컨트롤러
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@RestController
@Api(tags = "[WPDC] 상품(Wells) - 상품운영관리 - 복합상품 관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/compositions")
@RequiredArgsConstructor
@Validated
public class WpdcCompositionMgtController {

    private final WpdcCompositionMgtService service;
    private final ZpdcProductService pdService;
    private final ZpdcPriceMgtService priceService;
    private final ZpdcRelationMgtService relService;

    /**
     * 복합상품 상품 수정
     * @param dto 수정내용 정보
     * @return 상품정보
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "path", required = true, example = "WP04103177"),
    })
    @ApiOperation(value = "기준상품 상품 단건 조회")
    @GetMapping("/{pdCd}")
    public WpdcCompositionMgtDto.ProductInfoRes getProductInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = pdService.getProductByPdCd(pdCd);
        return WpdcCompositionMgtDto.ProductInfoRes.builder().tbPdbsPdBas(pdBas)
            .tbPdbsPdDtl(pdService.getProductDetailsByPdCd(pdCd))
            .tbPdbsPdEcomPrpDtl(pdService.getEachCompanyProps(pdCd))
            .tbPdbsPdPrcDtl(priceService.getPriceDetailProps(pdCd))
            .tbPdbsPdPrcFnlDtl(priceService.getPriceFinalDetailProps(pdCd))
            .tbPdbsPdDscPrumDtl(priceService.getDiscountPremiumDtls(pdCd))
            .relProducts(relService.getRelationProducts(pdCd, null))
            .build();
    }

    /**
     * 복합상품 상품 수정
     * @param dto 수정내용 정보
     * @return 상품정보
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "path", required = true, example = "WP04103177"),
    })
    @ApiOperation(value = "기준상품 상품 수정", notes = "수정된 상품정보를 반영한다.")
    @PutMapping("/{pdCd}")
    public SaveResponse editProduct(
        @RequestBody
        WpdcCompositionMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(
                service.saveProduct(
                    WpdcCompositionMgtDto.SaveReq.builder()
                        .pdCd(dto.pdCd())
                        .pdTpCd(dto.pdTpCd())
                        .tbPdbsPdBas(dto.tbPdbsPdBas()) /* FRONT pdConst.js 동기화 */
                        .tbPdbsPdDtl(dto.tbPdbsPdDtl())
                        .tbPdbsPdEcomPrpDtl(dto.tbPdbsPdEcomPrpDtl())
                        .tbPdbsPdRel(dto.tbPdbsPdRel())
                        .tbPdbsPdPrcDtl(dto.tbPdbsPdPrcDtl())
                        .tbPdbsPdPrcFnlDtl(dto.tbPdbsPdPrcFnlDtl())
                        .tbPdbsPdDscPrumDtl(dto.tbPdbsPdDscPrumDtl())
                        .isModifiedProp(dto.isModifiedProp())
                        .isModifiedPrice(dto.isModifiedPrice())
                        .isOnlyFileModified(dto.isOnlyFileModified())
                        .isModifiedRelation(dto.isModifiedRelation())
                        .build(),
                    false
                )
            )
            .build();
    }

    /**
     * 복합상품 상품 생성
     * @param dto 생성 정보
     * @return 상품정보
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "기준상품 상품 생성", notes = "수정된 상품정보를 반영한다.")
    @PostMapping
    public SaveResponse createProduct(
        @RequestBody
        WpdcCompositionMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveProduct(dto, true))
            .build();
    }

    /**
     * 복합상품 상품 삭제
     * @param pdCd 상품코드
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "path", required = true, example = "WP04103177"),
    })
    @ApiOperation(value = "기준상품 상품 삭제")
    @DeleteMapping("/{pdCd}")
    public SaveResponse removeProduct(@PathVariable("pdCd")
    String pdCd) throws Exception {
        return SaveResponse.builder().processCount(service.removeProduct(pdCd)).build();
    }
}
