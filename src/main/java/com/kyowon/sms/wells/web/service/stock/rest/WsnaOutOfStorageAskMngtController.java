package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageAskMngtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;
import com.kyowon.sms.wells.web.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_WELLS_SERVICE + "/out-of-storage-asks")
@Api(tags = "[WSNA] 출고요청 관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageAskMngtController {

    private final WsnaOutOfStorageAskMngtService service;

    /**
     * 출고요청 관리 - 조회
     * @param dto : { strOjWareNo: 출고요청창고, ostrAkTpCd: 출고요청유형코드, startStrHopDt: 입고희망일자 시작일, endStrHopDt: 입고희망일자 종료일, wareDvCd: 출고요청 접수창고, wareLocaraCd: 창고지역코드 }
     * @return 조회결과
     */
    @ApiOperation(value = "출고요청 조회", notes = "조회조건에 일치하는 출고요청 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형코드", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "startStrHopDt", value = "입고희망일자 시작일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "endStrHopDt", value = "입고희망일자 종료일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "출고요청 접수창고", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareLocaraCd", value = "창고지역코드", paramType = "query", example = "")
    })
    @GetMapping
    public List<SearchRes> getOutOfStorageAsks(WsnaOutOfStorageAskMngtDto.SearchReq dto) {
        return this.service.getOutOfStorageAsks(dto);
    }

    @GetMapping("/warehouses")
    public List<Warehouse> getOutOfStorageAskWares(WsnaOutOfStorageAskMngtDto.WarehouseReq dto) {
        return this.service.getOutOfStorageAskWares(dto);
    }

}
