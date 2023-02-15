package com.kyowon.sms.wells.web.product.manage.rest;

import java.util.List;

import javax.validation.Valid;

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
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcAsPartsMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WPDC] 상품 >> 상품운영관리 >> AS부품관리")
@RequestMapping(value = PdProductConst.REST_URL_V1 + "/as-parts")
@RequiredArgsConstructor
@Validated
public class WpdcAsPartsMgtController {

    private final ZpdcProductService cmnService;
    private final WpdcAsPartsMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdTpCd", value = "상품구분", paramType = "query", required = false, example = "S"),
        @ApiImplicitParam(name = "pdNm", value = "AS부품명", paramType = "query", required = false, example = "정수기"),
        @ApiImplicitParam(name = "pdCd", value = "AS부품코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateHigh", value = "대분류", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateMid", value = "중분류", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "AS부품 목록 페이징 조회", notes = "검색조건을 입력 받아 Paging된 AS부품 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdcAsPartMgtDto.SearchRes> getAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsPartPages(dto, pageInfo);
    }

    @ApiOperation(value = "AS부품 목록 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 AS부품 목록을 조회한다.")
    @GetMapping({"/excel-download"})
    public List<WpdcAsPartMgtDto.SearchRes> getAsPartsForExcelDownload(
        WpdcAsPartMgtDto.SearchReq dto
    ) {
        return this.service.getAsPartsForExcelDownload(dto);
    }

    @ApiOperation(value = "AS부품 단건 조회")
    @GetMapping("/{pdCd}")
    public WpdcAsPartMgtDto.ProductInfoRes getAsPartsInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = cmnService.getProductByPdCd(pdCd);
        return WpdcAsPartMgtDto.ProductInfoRes.builder()
            .tbPdbsPdBas(pdBas)
            .tbPdbsPdEcomPrpDtl(cmnService.getEachCompanyProps(pdCd))
            .groupCodes(cmnService.getPropertyGroupCodes(pdBas.pdTpCd(), "", null))
            .build();
    }

    @ApiOperation(value = "AS부품 저장 및 임시저장", notes = "최초 신규 생성 또는 최초 임시저장한 AS부품 정보를 반영한다.")
    @PostMapping
    public SaveResponse createAsParts(
        @Valid
        @RequestBody
        WpdcAsPartMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveAsParts(dto))
            .build();
    }

    @ApiOperation(value = "AS부품 수정", notes = "수정된 AS부품 정보를 반영한다.")
    @PutMapping
    public SaveResponse editAsParts(
        @Valid
        @RequestBody
        WpdcAsPartMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.editAsParts(dto))
            .build();
    }

    @ApiOperation(value = "AS부품 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "상품코드", value = "PD_CD", paramType = "path", required = true)
    })
    @DeleteMapping("/{pdCd}")
    public SaveResponse removeAsParts(@PathVariable
    String pdCd) throws Exception {
        return SaveResponse.builder().processCount(service.removeAsParts(pdCd)).build();
    }

}
