package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaEtcOutOfStorageRsonService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/etc-out-of-storage-resons")
@Api(tags = "[WSNA] 기타출고 사유내역 REST API")
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageRsonController {

    private final WsnaEtcOutOfStorageRsonService service;

    @ApiOperation(value = "출고요청 조회", notes = "조회조건에 일치하는 출고요청 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "출고요청창고", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "출고요청유형코드", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "bilRsonCd", value = "입고희망일자 시작일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "pdGdCd", value = "입고희망일자 종료일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "출고요청 접수창고", paramType = "query", example = "", required = true),
    })

    @GetMapping
    public List<SearchRes> getEtcOutOfStorageRsons(
        SearchReq dto
    ) {
        return this.service.getEtcOutOfStorageRsons(dto);
    }

}
