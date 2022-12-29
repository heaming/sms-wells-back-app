package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAfterServiceAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncAfterServiceAssignPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-assign-state")
@Api(tags = "[WSNC] 제품 서비스 현황 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncAfterServiceAssignPsController {

    private final WsncAfterServiceAssignPsService service;

    @ApiOperation(value = "상품각사속성상세(ST101TB), 고객서비스수행배정내역(AC221TB, AC261TB), 고객서비스AS설치대상내역(AC211TB), 고객서비스BS대상내역(AC251TB) 테이블을 JOIN하여 가져온다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wkExcnDt", value = "작업수행일자", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리자구분코드", paramType = "query"),
        @ApiImplicitParam(name = "pdPrpVal20", value = "상품속성값20", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping("/product-service-states")
    public List<WsncAfterServiceAssignPsDto.SearchRes> getProductServiceStates(
        WsncAfterServiceAssignPsDto.SearchReq req
    ) {
        return service.getProductServiceStates(req);
    }

}
