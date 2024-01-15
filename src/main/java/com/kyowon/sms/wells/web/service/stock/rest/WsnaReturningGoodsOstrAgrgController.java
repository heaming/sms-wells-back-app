package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchWareRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchWareReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsOstrAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-out-of-storages-agrg")
@Api(tags = "[WSNA] 반품출고집계현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaReturningGoodsOstrAgrgController {

    private final WsnaReturningGoodsOstrAgrgService service;

    //    @ApiOperation(value = "반품출고집계현황 조회", notes = "반품으로 출고된 상품들의 집계 현황을 조회한다.")
    //    @GetMapping("/paging")
    //    public PagingResult<SearchRes> getReturningGoodsOstrAgrg(
    //        @Valid
    //        SearchReq dto, PageInfo pageInfo
    //    ) {
    //        return service.getReturningGoodsOstrAgrg(dto, pageInfo);
    //    }

    @ApiOperation(value = "반품출고집계현황", notes = "반품으로 출고된 상품들의 집계 현황을 조회.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDate", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDate", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "rtngdProcsTpCd", value = "반품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "svCnrCd", value = "센터코드", paramType = "query", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchReq dto
    ) {
        return service.getReturningGoodsOstrAgrg(dto);
    }

    @ApiOperation(value = "반품출고집계현황 창고 드롭박스 초기 설정", notes = "유저 정보를 받아 담당 창고를 선택한다")
    @GetMapping("/user-og-ware")
    public List<SearchWareRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchWareReq dto
    ) {
        return service.getWareByUserOgId(dto);
    }
}
