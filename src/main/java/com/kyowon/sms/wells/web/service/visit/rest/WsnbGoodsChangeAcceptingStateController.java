package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbGoodsChangeAcceptingStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/goods-change-accepting-state")
@Api(tags = "[WSNB] 제품교체 접수 현황(K-W-SV-U-0061M01)")
@RequiredArgsConstructor
@Validated
public class WsnbGoodsChangeAcceptingStateController {
    private final WsnbGoodsChangeAcceptingStateService service;

    @ApiOperation(value = "제품교체 접수 현황 목록 조회", notes = "조회조건에 일치하는 제품교체 접수 현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrBaseCd", value = "조회기준코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "조회시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "조회종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "svCnrCd", value = "서비스센터코드", paramType = "query"),
        @ApiImplicitParam(name = "partnerNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "statCd", value = "상태코드", paramType = "query"),
        @ApiImplicitParam(name = "tpChYn", value = "유현변경여부", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getGoodsChangeAcceptingStates(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getGoodsChangeAcceptingStatePages(dto, pageInfo);
    }

    @ApiOperation(value = "제품교체 접수 현황 목록 조회 엑셀다운로드", notes = "조회조건에 일치하는 제품교체 접수 현황 데이터 조회 및 엑셀다운로드.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrBaseCd", value = "조회기준코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "조회시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "조회종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "svCnrCd", value = "서비스센터코드", paramType = "query"),
        @ApiImplicitParam(name = "partnerNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "statCd", value = "상태코드", paramType = "query"),
        @ApiImplicitParam(name = "tpChYn", value = "유현변경여부", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getGoodsChangeAcceptingStatesExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getGoodsChangeAcceptingStateExcelDownload(dto);
    }

    @ApiOperation(value = "제품교체 접수 현황(반려) 저장", notes = "제품교체 접수 현황(반려)을 저장한다.")
    @PostMapping
    public SaveResponse saveGoodsChangeAcceptingStateReject(
        @RequestBody
        @NotEmpty
        List<WsnbGoodsChangeAcceptingStateDto.SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder()
            .processCount(service.saveGoodsChangeAcceptingStateReject(dtos))
            .build();
    }

    @ApiOperation(value = "제품교체 접수 현황(승인) 저장", notes = "제품교체 접수 현황(승인)을 저장한다.")
    @PostMapping("/approve")
    public SaveResponse saveGoodsChangeAcceptingStateApprove(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsnbGoodsChangeAcceptingStateDto.SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder()
            .processCount(service.saveGoodsChangeAcceptingStateApprove(dtos))
            .build();
    }

}
