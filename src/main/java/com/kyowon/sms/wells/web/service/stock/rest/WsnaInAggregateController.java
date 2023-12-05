package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInAggregateDto.SearchReq;

import java.util.HashMap;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateWareDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaInAggregateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */

@Api(tags = "[WSNA] 입고 집계")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/in-aggregate")
public class WsnaInAggregateController {

    private final WsnaInAggregateService service;

    @ApiOperation(value = "입고 집계 조회", notes = "입고 집계 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", example = "121"),
        @ApiImplicitParam(name = "pdCdFrom", value = "품목코드from", paramType = "query", example = "WM01100243"),
        @ApiImplicitParam(name = "pdCdTo", value = "품목코드to", paramType = "query", example = "WM01100264"),
        @ApiImplicitParam(name = "sapCdFrom", value = "sap코드from", paramType = "query", example = "000000000300004440"),
        @ApiImplicitParam(name = "sapCdTo", value = "sap코드to", paramType = "query", example = "000000000300005370"),
        @ApiImplicitParam(name = "baseDateFrom", value = "입고일자from", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "입고일자to", paramType = "query", example = "20230631"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "pdCd", value = "폼목코드", paramType = "query", example = "WM01100243"),
        @ApiImplicitParam(name = "pdGdCd", value = "등급", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y")
    })
    @GetMapping
    public List<HashMap<String, String>> getInAggregate(
        SearchReq dto
    ) {
        return service.getInAggregate(dto);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "출고집계현황 창고 조회", notes = "출고집계현황 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDateFrom", value = "입고일자from", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", required = true),
    })
    public List<WsnaInAggregateWareDvo> getWareHouseNames(
        @RequestParam(name = "baseDateFrom")
        String baseDateFrom,
        @RequestParam(name = "wareDvCd")
        String wareDvCd
    ) {
        return this.service.getWareHouses(baseDateFrom, wareDvCd);
    }
}
