package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaWareItemReceivingAndPayingStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 창고 품목별 수불현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/ware-item-receiving-and-paying-state")

public class WsnaWareItemReceivingAndPayingStateController {

    private final WsnaWareItemReceivingAndPayingStateService service;

    @ApiOperation(value = "창고 품목별 수불현황 목록 조회", notes = "조회조건에 일치하는 창고 품목별 수불현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "확인일자시작", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "확인일자종료", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고구분코드상세", paramType = "query", required = true),
        @ApiImplicitParam(name = "itmGdCd", value = "상품등급", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmKindCd", value = "품목구분", paramType = "query"),
    })
    @GetMapping
    public List<WsnaWareItemReceivingAndPayingStateDto.SearchRes> getWsnaWareItemReceivingAndPayingStates(
        @Valid
        WsnaWareItemReceivingAndPayingStateDto.SearchReq dto
    ) {
        return service.getWareItemReceivingAndPayingStates(dto);
    }

}
