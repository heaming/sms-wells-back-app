package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngtSchdInqrDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBsMngtSchdInqrService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * W-SV-U-0089M01 BS관리일정조회
 * @author segi33 홍세기
 * @since 2023-06-06
 */
@Api(tags = "[WSNC] BS관리일정 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/bs-manager-schedule")
public class WsncBsMngtSchdInqrController {
    private final WsncBsMngtSchdInqrService service;

    @ApiOperation(value = "BS관리일정 조회 화면 - 집계 조회", notes = "조회조건에 따른 BS관리일정 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateFrom", value = "관리년월From", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateTo", value = "관리년월To", paramType = "query", required = true),
    })
    @PostMapping("/aggregate")
    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return service.getBsMngtSchdInqrAgrg(dto);
    }

    @ApiOperation(value = "BS관리일정 조회 화면 - 상세 조회", notes = "조회조건에 따른 BS관리일정 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "파트너사번", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateFrom", value = "관리년월From", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseDateTo", value = "관리년월To", paramType = "query", required = true),
    })
    @PostMapping("/detail")
    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrDtl(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return service.getBsMngtSchdInqrDtl(dto);
    }
}
