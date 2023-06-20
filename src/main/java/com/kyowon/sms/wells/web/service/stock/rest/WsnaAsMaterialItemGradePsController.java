package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialItemGradePsConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialItemGradePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

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

    /**
     * 창고 조회
     * @param dto
     * @return
     */
    @GetMapping("/ware-houses")
    @ApiOperation(value = "AS자재 품목등급현황 창고 조회", notes = "AS자재 품목등급현황 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true)
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouseNames(@Valid
    WsnaAsMaterialItemGradePsDto.SearchWareReq dto) {
        return this.service.getWareHouses(dto);
    }

    /**
     * AS자재 품목등급현황 조회
     * @param dto
     * @return
     */
    @GetMapping
    @ApiOperation(value = "AS자재 품목등급현황 조회", notes = "AS자재 품목등급현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public List<HashMap<String, String>> getAsMaterialsItemGradePs(@Valid
    WsnaAsMaterialItemGradePsDto.SearchReq dto) {

        WsnaAsMaterialItemGradePsDvo dvo = this.converter.mapSearchReqToWsnaAsMaterialItemGradePsDvo(dto);
        return this.service.getAsMaterialItemGradePs(dvo);
    }

}
