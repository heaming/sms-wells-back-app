package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.service.WsncBsServicePresentStateService;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsServicePresentStateDto.*;

import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-service-present-state")
@Api(tags = "[WSNC] B/S 서비스 현황 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsncBsServicePresentStateController {
    private final WsncBsServicePresentStateService service;

        @ApiOperation(value = "B/S 서비스 현황", notes = "B/S 서비스 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mgtYnm", value = "관리년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "mgtDept", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "rgnlGrp", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "직급", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "사번", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "성명", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchResList> getBsServicePresentStateList(SearchReq dto, @Valid PageInfo pageInfo){
        return service.getBsServicePresentStateList(dto, pageInfo);
    }

    @ApiOperation(value = "B/S 서비스 현황 엑셀 다운로드", notes = "B/S 서비스 현황 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mgtYnm", value = "관리년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "mgtDept", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "rgnlGrp", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "직급", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "사번", paramType = "query"),
        @ApiImplicitParam(name = "branch", value = "성명", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchResList> getBsServicePresentStateExcelDownload(SearchReq dto){
        return service.getBsServicePresentStateExcelDownload(dto);
    }

    @GetMapping("/info")
    public List<SearchResInfo> getBsServicePresentStateInfo(SearchReq dto){
        return service.getBsServicePresentStateInfo(dto);
    }
}
