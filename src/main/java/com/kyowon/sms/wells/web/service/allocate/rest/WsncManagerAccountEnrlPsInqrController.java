package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagerAccountEnrlPsInqrDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncManagerAccountEnrlPsInqrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * <pre>
 * [K-W-SV-U-0300M01] 매니저 계정 및 재적현황 조회
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.11
 */
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/manager-acc-enrl-ps-inqr")
@Api(tags = "[WSNC] 매니저 계정 및 재적현황 조회")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncManagerAccountEnrlPsInqrController {
    private final WsncManagerAccountEnrlPsInqrService service;

    @ApiOperation(value = "총괄단 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "metgStartDt", value = "관리년월", paramType = "query", example = "202309"),
    })
    @GetMapping("/general-division-agrg")
    public List<SearchGnrdvAgrgRes> getManagerAccountEnrlPss(SearchReq dto){
        return service.getManagerAccountEnrlPss(dto);
    }

    @ApiOperation(value = "매니저 계정 및 재적 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "metgStartDt", value = "관리년월", paramType = "query", example = "202309"),
    })
    @GetMapping("/manager-account-enrl-ps")
    public List<SearchMngerAccEnrlPsRes> getMngerAccEnrlPss(SearchReq dto){
        return service.getMngerAccEnrlPss(dto);
    }
}
