package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncAsAssignPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-assign-state")
@Api(tags = "[WSNC] 배정관리 집계 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncAsAssignPsController {

    private final WsncAsAssignPsService service;

    @ApiOperation(value = "총 관리고객 집계(W-SV-U-0228M01)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "year", value = "년도", paramType = "query", example = "2022"),
        @ApiImplicitParam(name = "pdGdCd", value = "상품등급코드", paramType = "query", example = "A"),
    })
    @GetMapping("/total-customers")
    public List<WsncAsAssignPsDto.SearchTotalCustomerRes> getTotalCustomers(
        WsncAsAssignPsDto.SearchTotalCustomerReq dto
    ) {
        return service.getTotalCustomers(dto);
    }

    @ApiOperation(value = "제품 서비스 현황(W-SV-U-0229M01)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wkExcnDt", value = "작업수행일자", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리자구분코드", paramType = "query"),
        @ApiImplicitParam(name = "pdPrpVal20", value = "상품속성값20", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping("/product-services")
    public List<WsncAsAssignPsDto.SearchProductServicesRes> getProductServices(
        WsncAsAssignPsDto.SearchProductServicesReq req
    ) {
        return service.getProductServices(req);
    }

}
