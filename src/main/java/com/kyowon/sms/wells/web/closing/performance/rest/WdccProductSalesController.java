package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchSapMaterialsRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccProductSalesService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 상품별 매출 현황")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-sales")
public class WdccProductSalesController {
    private final WdccProductSalesService service;

    @ApiOperation(value = "상품별 매출 현황(일시불, 금융리스, 정기구매)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDvCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분", paramType = "query"),
    })
    @GetMapping("/basic/lists")
    public List<SearchRes> getBasicList(
        @Valid
        SearchReq dto
    ) {
        return service.getBasicList(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(렌탈/리스)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDvCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분", paramType = "query"),
    })
    @GetMapping("/rental/lists")
    public List<SearchRentalRes> getRentalList(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalList(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(멤버십)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDvCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분", paramType = "query"),
    })
    @GetMapping("/membership/lists")
    public List<SearchMembershipRes> getMembershipList(
        @Valid
        SearchReq dto
    ) {
        return service.getMembershipList(dto);
    }

    @ApiOperation(value = "상품별 매출 현황(멤버십)", notes = "조회조건에 따른 상품별 매출 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDvCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분", paramType = "query"),
    })
    @GetMapping("/sap-materials")
    public SearchSapMaterialsRes getProductSalesSapMaterials(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesSapMaterials(dto);
    }
}
