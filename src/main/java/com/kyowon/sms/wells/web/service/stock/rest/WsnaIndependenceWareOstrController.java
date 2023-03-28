package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaIndependenceWareOstrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 독립창고출고관리 REST API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/independence-ware-ostrs")
public class WsnaIndependenceWareOstrController {
    private final WsnaIndependenceWareOstrService service;

    @ApiOperation(value = "독립창고출고관리 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query"),
    })
    @GetMapping
    public PagingResult<SearchRes> getIndividualWareOstrs(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.selectIndependenceWareOstrs(dto, pageInfo);
    }

    @ApiOperation(value = "독립창고출고관리 저장", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query"),
    })
    @PostMapping
    public SaveResponse createIndividualWareOstrs(
        @RequestBody
        @Valid
        List<CreateReq> list
    ) {
        return SaveResponse.builder()
            .processCount(service.createIndependenceWareOstr(list))
            .build();
    }
}
