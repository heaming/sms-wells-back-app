package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SaveSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeySellProductTypeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEY] 판매상품별 제품유형")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/sell-product-type")
@Slf4j
public class WfeySellProductTypeController {

    private final WfeySellProductTypeService service;


    @ApiOperation(value = "판매상품별 제품유형 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
    })
    @GetMapping()
    public List<SearchSellProductRes> getSellProductType(@ApiParam @Valid SearchSellProductReq req) {
        return service.getSellProductTypeList(req);
    }

    @ApiOperation(value = "판매상품별 제품유형 조회 페이징")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
    })
    @GetMapping("/pages")
    public PagingResult<SearchSellProductRes> getSellProductType(@ApiParam @Valid SearchSellProductReq req, @Valid PageInfo pageInfo) {
        return service.getSellProductTypeList(req, pageInfo);
    }

    @ApiOperation(value = "판매상품별 제품유형 저장", notes = "판매상품별 제품유형 데이터를 수정한다.")
    @PostMapping()
    public SaveResponse saveSellProductType(@RequestBody @Valid List<SaveSellProductReq> dto) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveSellProductType(dto))
            .build();
    }

    @ApiOperation(value = "판매상품별 제품유형 엑셀 업로드", notes = "엑셀 업로드")
    @PostMapping("/excel-upload")
    public ExcelUploadDto.UploadRes saveSellProductTypeExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveSellProductTypeExcelUpload(file);
    }


}
