package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailMngtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세조회/관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailMngtController {

    private final WctaOrderDetailMngtService service;

    @ApiOperation(value = "주문상세조회/관리", notes = "렌탈 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류-대분류", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류-중분류", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query"),
        @ApiImplicitParam(name = "sellEvCd", value = "행사코드", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드-지점", paramType = "query"),
        @ApiImplicitParam(name = "cndtSellTpCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "booSellYn", value = "자료구분-예약자료", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "자료구분-취소제외", paramType = "query"),
        @ApiImplicitParam(name = "slYn", value = "자료구분-매출생성", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/rentals/paging")
    public PagingResult<SearchRes> getOrderDetailRentalPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailRentalPages(dto, pageInfo);
    }

    @GetMapping("/order-detail-mngt/rentals/excel-download")
    public List<SearchRes> getOrderDtlRentalExcels(
        @Valid
        SearchReq dto
    ) {
        return service.getOrderDtlRentalExcels(dto);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "멤버쉽 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/membership/paging")
    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailMshPages(dto, pageInfo);
    }

    @GetMapping("/order-detail-mngt/membership/excel-download")
    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(
        @Valid
        SearchReq dto
    ) {
        return service.getOrderDetailMshExcels(dto);
    }
}
