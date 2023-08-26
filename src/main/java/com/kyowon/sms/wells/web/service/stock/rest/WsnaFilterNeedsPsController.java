package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterNeedsPsDto.SearchReq;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaFilterNeedsPsDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaFilterNeedsPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0278M01 필터소요 현황(교체주기) Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

@Api(tags = "[WSNA] 필터소요 현황(교체주기)")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/filter-needs-state")
public class WsnaFilterNeedsPsController {

    private final WsnaFilterNeedsPsService service;

    @GetMapping
    @ApiOperation(value = "필터소요 현황(교체주기) 조회", notes = "필터소요 현황(교체주기)을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "출고시작일자", paramType = "query", example = "20230501", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", example = "20230808", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "4", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "b2bMngtCd", value = "B2B 관리", paramType = "query", example = "A")
    })
    public List<WsnaFilterNeedsPsDvo> getFilterNeedsState(@Valid
    SearchReq dto) {
        return this.service.getFilterNeedsState(dto);
    }

}
