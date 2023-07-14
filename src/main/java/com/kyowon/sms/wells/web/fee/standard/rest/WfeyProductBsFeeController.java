package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SaveProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeyProductBsFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEY] 상품별 BS 수수료 기준정보")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/product-bs-fee")
@Slf4j
public class WfeyProductBsFeeController {

    private final WfeyProductBsFeeService service;


    @ApiOperation(value = "상품별 BS 수수료 기준정보 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "vstMcn", value = "방문개월수 ", paramType = "query"),
            @ApiImplicitParam(name = "svFeeDvCd", value = "서비스수수료구분코드 ", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
            @ApiImplicitParam(name = "hcrDvCd", value = "홈케어구분코드 ", paramType = "query"),
    })
    @GetMapping()
    public List<SearchProductBsFeeRes> getProductBsFee(@ApiParam @Valid SearchProductBsFeeReq req) {
        return service.getProductBsFeeList(req);
    }

    @ApiOperation(value = "상품별 BS 수수료 기준정보 조회 페이징")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "vstMcn", value = "방문개월수 ", paramType = "query"),
            @ApiImplicitParam(name = "svFeeDvCd", value = "서비스수수료구분코드 ", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
            @ApiImplicitParam(name = "hcrDvCd", value = "홈케어구분코드 ", paramType = "query"),
    })
    @GetMapping("/pages")
    public PagingResult<SearchProductBsFeeRes> getProductBsFee(@ApiParam @Valid SearchProductBsFeeReq req, @Valid PageInfo pageInfo) {
        return service.getProductBsFeeList(req, pageInfo);
    }

    @ApiOperation(value = "상품별 BS 수수료 기준정보 저장", notes = "상품별 BS 수수료 기준정보 데이터를 수정한다.")
    @PostMapping()
    public SaveResponse saveProductBsFee(@RequestBody @Valid List<SaveProductBsFeeReq> dto) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveProductBsFee(dto))
            .build();
    }
}
