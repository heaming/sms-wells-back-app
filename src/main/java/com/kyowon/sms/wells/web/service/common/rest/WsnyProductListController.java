package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.service.WsnyProductListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNY] 품목종류별 상품목록 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/product-list")
public class WsnyProductListController {
    private final WsnyProductListService service;

    @ApiOperation(value = "품목종류별 상품목록 조회", notes = "공통코드의 ITM_KND_CD를 통해 상품목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query")
    })
    @GetMapping(value = "/by-itmkndcd")
    public List<SearchRes> getProductListByItmKndCd(
        SearchReq dto
    ) {
        return service.getProductListByItmKndCd(dto);
    }
}
