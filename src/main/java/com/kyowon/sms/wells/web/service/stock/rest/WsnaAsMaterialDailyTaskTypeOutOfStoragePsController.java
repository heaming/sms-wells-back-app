package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialDailyTaskTypeOutOfStoragePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/as-material-daily-task-type-out-of-storage-ps")
@Api(tags = "[WSNA] A/S 자재 일일 출고 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaAsMaterialDailyTaskTypeOutOfStoragePsController {

    private final WsnaAsMaterialDailyTaskTypeOutOfStoragePsService service;

    @ApiOperation(value = "A/S 자재 일일 출고 현황 조회", notes = "A/S 자재 일일 출고 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseYear", value = "기준년", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseMonth", value = "기준월", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query"),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query"),
        @ApiImplicitParam(name = "wareNoM", value = "창고번호", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고번호상세", paramType = "query"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "서비스업무대분류코드", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분코드", paramType = "query"),
        @ApiImplicitParam(name = "itmGrpCd", value = "품목그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목코드 리스트", paramType = "query", example = "[WM07102157]", dataType = "array"),
        @ApiImplicitParam(name = "itmPdCdFrom", value = "상품코드시작", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCdTo", value = "상품코드종료", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248"),
    })
    @GetMapping
    public List<SearchRes> getAsMaterialDailyTaskTypeOutOfStoragePss(
        @Valid
        SearchReq dto
    ) {
        return service.getAsMaterialDailyTaskTypeOutOfStoragePss(dto);
    }
}
