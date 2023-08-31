
package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto.*;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncIntergratedQrProcsListSearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/intergrated-qr-procs")
@Api(tags = "[WSNC] K-W-SV-U-0239M01 통합QR 처리현황 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncIntergratedQrProcsListSearchController {

    private final WsncIntergratedQrProcsListSearchService service;

    @ApiOperation(value = "통합QR 처리현황", notes = "조직별 집계")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "baseDateFrom", value = "처리일자from", paramType = "query", example = "20230801"),
        @ApiImplicitParam(name = "baseDateTo", value = "처리일자to", paramType = "query", example = "20230825"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", example = "OG00065912"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", example = "OG00065978"),
        @ApiImplicitParam(name = "svOgId", value = "서비스센터", paramType = "query", example = "OG00065978"),
    })
    @GetMapping
    public List<SearchByOgRes> getByOgProcsList(
        SearchReq dto
    ) {
        return service.getByOgProcsList(dto);
    }

    @ApiOperation(value = "통합QR 처리현황", notes = "조직상세집계")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "baseDateFrom", value = "처리일자from", paramType = "query", example = "20230801"),
        @ApiImplicitParam(name = "baseDateTo", value = "처리일자to", paramType = "query", example = "20230825"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", example = "OG00065912"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", example = "OG00065978"),
        @ApiImplicitParam(name = "svOgId", value = "서비스센터", paramType = "query", example = "OG00065978"),
    })
    @GetMapping("/organization-detail")
    public List<SearchOgDetailRes> getOgDetailProcsList(
        SearchReq dto
    ) {
        return service.getOgDetailProcsList(dto);
    }
}
