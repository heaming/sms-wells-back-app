package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsHavePresentStateDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMaterialsHavePresentStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[K-W-SV-U-0112M01] 자재보유현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/material-have-qty")
public class WsnaMaterialsHavePresentStateController {

    private final WsnaMaterialsHavePresentStateService service;

    @ApiOperation(value = "자재보유현황 조회", notes = "자재현황 자재보유현황 센터 개인별 창고 재고")
    @GetMapping("/paging")
    public PagingResult<WsnaMaterialsHavePresentStateDto.SearchRes> getMaterialsHavePresentPages(
        WsnaMaterialsHavePresentStateDto.SearchReq dto, PageInfo pageInfo
    ) {
        return service.getMaterialsHavePresentListPages(dto, pageInfo);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "자재보유현황 창고 조회", notes = "자재보유현황 창고 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20")
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(@Valid
    WsnaAsMaterialItemGradeDto.SearchWareReq dto) {
        return this.service.getWareHouses(dto);
    }

}
