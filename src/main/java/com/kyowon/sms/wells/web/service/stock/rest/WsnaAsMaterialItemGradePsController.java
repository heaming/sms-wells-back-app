package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto.SearchWareReq;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialItemGradePsConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialItemGradePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0257M01 AS자재 품목등급현황 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-20
 */

@Api(tags = "[WSNA] AS자재 품목등급현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-material-item-grade-state")
public class WsnaAsMaterialItemGradePsController {

    private final WsnaAsMaterialItemGradePsConverter converter;

    private final WsnaAsMaterialItemGradePsService service;

    @GetMapping("/ware-houses")
    @ApiOperation(value = "AS자재 품목등급현황 창고 조회", notes = "AS자재 품목등급현황 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true)
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouseNames(@Valid
    SearchWareReq dto) {
        return this.service.getWareHouses(dto);
    }

    @GetMapping("/paging")
    @ApiOperation(value = "AS자재 품목등급현황 페이징 조회", notes = "AS자재 품목등급현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public PagingResult<HashMap<String, String>> getAsMaterialsItemGradePsPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {

        WsnaAsMaterialItemGradePsDvo dvo = this.converter.mapSearchReqToWsnaAsMaterialItemGradePsDvo(dto);
        return this.service.getAsMaterialItemGradePsPaging(dvo, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "AS자재 품목등급현황 엑셀 다운로드", notes = "조회조건에 일치하는 AS자재 품목등급 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public List<HashMap<String, String>> getAsMaterialsItemGradePsExcelDownload(@Valid
    SearchReq dto) {

        WsnaAsMaterialItemGradePsDvo dvo = this.converter.mapSearchReqToWsnaAsMaterialItemGradePsDvo(dto);
        return this.service.getAsMaterialItemGradePsExcelDownload(dvo);
    }

}
