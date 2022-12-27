
package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsBstrCostDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyRecapAsBstrCostService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_WELLS_SERVICE + "/recap-as-bstr-costs")
@Api(tags = "[WSNY] 유상 AS 출장비 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnyRecapAsBstrCostController {

    private final WsnyRecapAsBstrCostService service;

    @ApiOperation(value = "유상 AS 출장비 관리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "4103000000"),
    })
    @GetMapping("/paging")
    public PagingResult<WsnyRecapAsBstrCostDto.SearchRes> getRecapAsBstrCostPages(
        WsnyRecapAsBstrCostDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getRecapAsBstrCostPages(dto, pageInfo);
    }

    @ApiOperation(value = "유상 AS 출장비 관리 저장")
    @PutMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "izSn", value = "내역일련번호", paramType = "query"),
        @ApiImplicitParam(name = "bstrCsAmt", value = "비용", paramType = "query"),
        @ApiImplicitParam(name = "vlStrtDtm", value = "유효시작일시", paramType = "query"),
        @ApiImplicitParam(name = "vlEndDtm", value = "유효종료일시", paramType = "query"),
        @ApiImplicitParam(name = "rmkCn", value = "비고내용", paramType = "query"),
    })
    public SaveResponse saveRecapAsBstrCost(
        @RequestBody
        @Valid
        @NotEmpty
        List<WsnyRecapAsBstrCostDto.SaveReq> rowData
    ) throws ParseException {
        return SaveResponse.builder().processCount(service.saveRecapAsBstrCost(rowData)).build();
    }
}
