package com.kyowon.sms.wells.web.fee.simulation.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.fee.simulation.dto.WfefFeeDeductionPresentStateDto.*;

import com.kyowon.sms.wells.web.fee.simulation.service.WfefFeeDeductionPresentStateService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WFEF] 수수료 공제현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/fee-deduction-present-state")
public class WfefFeeDeductionPresentStateController {

    private final WfefFeeDeductionPresentStateService service;

    @ApiOperation(value = "수수료 공제현황 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getFeeDeductionPresentStatePages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFeeDeductionPresentStatePages(dto, pageInfo);
    }

    @ApiOperation(value = "수수료 공제현황 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getFeeDeductionPresentStatesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getFeeDeductionPresentStatesForExcelDownload(dto);
    }
}
