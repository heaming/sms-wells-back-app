package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchWareRes;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemByStockAggregationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

@Api(tags = "[WSNA] 품목별 재고 집계")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-by-stock-aggs")
public class WsnaItemByStockAggregationController {

    private final WsnaItemByStockAggregationService service;

    @GetMapping("/ware-houses")
    @ApiOperation(value = "품목별 재고 집계 창고 조회", notes = "품목별 재고 집계 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDt", value = "기준일자", paramType = "query", example = "20230808", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", example = "2", required = true)
    })
    public List<SearchWareRes> getWareHouseNames(
        @RequestParam(name = "baseDt")
        @ValidDate
        String baseDt, @RequestParam(name = "wareDvCd")
        String wareDvCd
    ) {
        return this.service.getWareHouses(baseDt, wareDvCd);
    }

    @GetMapping
    @ApiOperation(value = "품목별 재고 집계 조회", notes = "품목별 재고 집계 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDt", value = "기준일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "mgtTypCd", value = "재고유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmGrpCd", value = "품목그룹코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]", dataType = "array"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareTpCd", value = "창고유형", paramType = "query", example = "CORP"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급코드", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "svMatGrpCd", value = "자재그룹", paramType = "query", example = "B"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "commGb", value = "중수리자재여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "baseGb", value = "기초자재여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "turnoverGb", value = "회전율대상여부", paramType = "query", example = "Y")
    })
    public List<HashMap<String, Object>> getItemByStockAggs(@Valid
    SearchReq dto) {

        return this.service.getItemByStockAggsExcelDownload(dto);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "품목별 재고 집계 엑셀 다운로드", notes = "조회조건에 일치하는 품목별 재고 집계 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDt", value = "기준일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "mgtTypCd", value = "재고유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmGrpCd", value = "품목그룹코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]", dataType = "array"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareTpCd", value = "창고유형", paramType = "query", example = "CORP"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급코드", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "svMatGrpCd", value = "자재그룹", paramType = "query", example = "B"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "commGb", value = "중수리자재여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "baseGb", value = "기초자재여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "turnoverGb", value = "회전율대상여부", paramType = "query", example = "Y")
    })
    public List<HashMap<String, Object>> getItemByStockAggsExcelDownload(@Valid
    SearchReq dto) {

        return this.service.getItemByStockAggsExcelDownload(dto);
    }

}
