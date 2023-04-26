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
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcServiceMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcServiceMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WPDC] 상품(공통) - 상품운영관리 - 서비스")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/services")
@RequiredArgsConstructor
@Validated
public class WpdcServiceMgtController {

    private final ZpdcProductService pdService;
    private final WpdcServiceMgtService service;
    private final ZpdcRelationMgtService relService;

    @ApiOperation(value = "서비스 상품 단건 조회")
    @GetMapping("/{pdCd}")
    public WpdcServiceMgtDto.ProductInfoRes getProductInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = pdService.getProductByPdCd(pdCd);
        return WpdcServiceMgtDto.ProductInfoRes.builder().tbPdbsPdBas(pdBas)
            .tbPdbsPdEcomPrpDtl(pdService.getEachCompanyProps(pdCd))
            .relProducts(relService.getRelationProducts(pdCd, null))
            .build();
    }

    @ApiOperation(value = "서비스 상품 수정", notes = "수정된 상품정보를 반영한다.")
    @PutMapping("/{pdCd}")
    public SaveResponse editProduct(
        @RequestBody
        WpdcServiceMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(
                service.saveProduct(
                    WpdcServiceMgtDto.SaveReq.builder()
                        .pdCd(dto.pdCd())
                        .tbPdbsPdBas(dto.tbPdbsPdBas()) /* FRONT pdConst.js 동기화 */
                        .tbPdbsPdEcomPrpDtl(dto.tbPdbsPdEcomPrpDtl())
                        .tbPdbsPdRel(dto.tbPdbsPdRel())
                        .isModifiedProp(dto.isModifiedProp())
                        .isOnlyFileModified(dto.isOnlyFileModified())
                        .build(),
                    false
                )
            )
            .build();
    }

    @ApiOperation(value = "서비스 상품 생성", notes = "수정된 상품정보를 반영한다.")
    @PostMapping
    public SaveResponse createProduct(
        @RequestBody
        WpdcServiceMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveProduct(dto, true))
            .build();
    }

    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/{pdCd}")
    public SaveResponse removeProduct(@PathVariable("pdCd")
    String pdCd) throws Exception {
        return SaveResponse.builder().processCount(service.removeProduct(pdCd)).build();
    }
}
