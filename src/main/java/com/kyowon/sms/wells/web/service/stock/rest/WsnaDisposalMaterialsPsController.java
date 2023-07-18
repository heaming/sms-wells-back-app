package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaDisposalMaterialsPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0277M01 매각자재관리 현황 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-18
 */

@Api(tags = "[WSNA] 매각자재 관리 현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/disposal-materials-state")
public class WsnaDisposalMaterialsPsController {

    private final WsnaDisposalMaterialsPsService service;

    @GetMapping("/ware-houses")
    @ApiOperation(value = "매각자재 관리 현황 창고 조회", notes = "매각자재 관리 현황 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true)
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouseNames(@RequestParam(name = "baseYm")
    String baseYm) {
        return this.service.getMcbyWareHouses(baseYm);
    }

    @GetMapping
    @ApiOperation(value = "매각자재 관리 현황 조회", notes = "매각자재 관리 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200001")
    })
    public List<WsnaDisposalMaterialsPsDvo> getAsMaterialsItemGradePsExcelDownload(@RequestParam(name = "baseYm")
    String baseYm, @RequestParam(name = "wareNo", required = false)
    String wareNo) {

        return this.service.getDisposalMaterials(baseYm, wareNo);
    }

}
