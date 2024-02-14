package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchPdRes;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaWasteFilterCollectionPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0275M01 폐필터 회수 현황 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

@Api(tags = "[WSNA] 폐필터 회수 현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/waste-filter-collections-state")
public class WsnaWasteFilterCollectionPsController {

    private final WsnaWasteFilterCollectionPsService service;

    @GetMapping("/products")
    @ApiOperation(value = "품목 조회", notes = "품목을 조회한다.")
    public List<SearchPdRes> getProducts() {
        return this.service.getProducts();
    }

    @GetMapping
    @ApiOperation(value = "폐필터 회수 현황 조회", notes = "폐필터 회수 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200609"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200898"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "업무유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmGrpCd", value = "품목그룹코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목코드 리스트", paramType = "query", example = "[WM07102157]", dataType = "array"),
    })
    public List<SearchRes> getWasteFilterCollections(@Valid
    SearchReq dto) {

        return this.service.getWasteFilterCollections(dto);
    }

}
