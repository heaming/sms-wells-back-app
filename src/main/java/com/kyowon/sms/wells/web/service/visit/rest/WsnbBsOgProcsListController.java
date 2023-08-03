
package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbBsOgProcsListDto.*;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbBsOgProcsListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-og-procs")
@Api(tags = "[WSNB] K-W-SV-U-0238M01 B/S 처리 현황(조직) REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbBsOgProcsListController {

    private final WsnbBsOgProcsListService service;

    @ApiOperation(value = "B/S 처리 현황(조직)", notes = "BS 처리현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "2"),
    })
    @GetMapping
    public List<SearchRes> getBsOgProcsList(
        SearchReq dto
    ) {
        return service.getBsOgProcsList(dto);
    }

    @ApiOperation(value = "B/S 처리 현황(조직)", notes = "이월현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", example = "2"),
    })
    @GetMapping("/crdOvr")
    public List<SearchCrdOvrRes> getCrdOvrList(
        SearchReq dto
    ) {
        return service.getCrdOvrList(dto);
    }
}
