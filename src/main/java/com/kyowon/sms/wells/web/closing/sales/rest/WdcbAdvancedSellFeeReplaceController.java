package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.*;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbAdvancedSellFeeReplaceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "[EDCB] 선급판매수수료 비용 대체 관리")
@RequiredArgsConstructor
@RequestMapping(value = DcClosingConst.COMMON_URL_V1 + "/advanced-fee-replace")
public class WdcbAdvancedSellFeeReplaceController {
    private final WdcbAdvancedSellFeeReplaceService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGubun", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "piaCsYn", value = "선급대상여부", paramType = "query"),
        @ApiImplicitParam(name = "dgCstId", value = "대표고객코드명", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "feeChk", value = "수수료구분체크박스", paramType = "query"),
        @ApiImplicitParam(name = "feeCd", value = "수수료구분상세", paramType = "query"),
    })

    @ApiOperation(value = "상세리스트", notes = "상세리스트 조회")

    @GetMapping("/details/list")
    public List<SearchRes> getDtlLists(
        SearchReq dto
    ) {
        return service.getDtlLists(dto);
    }

    @GetMapping("/details/summary")
    public List<SearchDtlSummaryRes> getDtlSummary(
        SearchReq dto
    ) {
        return service.getDtlSummary(dto);
    }

    @GetMapping("/aggregates/list")
    public List<SearchAggregateRes> getAggregateLists(
        SearchReq dto
    ) {
        return service.getAggregateLists(dto);
    }

    @GetMapping("/aggregates/summary")
    public List<SearchAggregateSummaryRes> getAggregateSummary(
        SearchReq dto
    ) {
        return service.getAggregateSummary(dto);
    }

    @GetMapping("/divides/list")
    public List<SearchDivideRes> getDivideLists(
        SearchReq dto
    ) {
        return service.getDivideLists(dto);
    }

    @GetMapping("/fee-gubun-code")
    public List<SearchCodeRes> getFeeGubunCodes() {
        return service.getFeeGubunCodes();
    }

    @GetMapping("/info-pop")
    public SearchPopRes getPop(
        String kwGrpCoCd
    ) {
        return service.getPop(kwGrpCoCd);
    }

    @ApiOperation(value = "전표 생성", notes = "전표 생성")
    @PutMapping("/slip-create")
    public SaveResponse saveSlipCreate(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveSlipCreate(dto)).build();
    }
}
