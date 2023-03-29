package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailInqrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "wells 주문상세조회/관리(일시불-개인정보)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailInqrController {
    private final WctaOrderDetailInqrService service;

    @ApiOperation(value = "주문상세조회/관리", notes = "일시불-개인정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGbn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "sexGbn", value = "남녀구분", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCanYn", value = "취소제외", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/singlepayments/paging")
    public PagingResult<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPages(
        @Valid
        WctaOrderDetailInqrDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailSpayCntrtPages(dto, pageInfo);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "일시불-개인정보를 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGbn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "sexGbn", value = "남녀구분", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCanYn", value = "취소제외", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/singlepayments/excel-download")
    public List<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPagesExcelDownload(
        @Valid
        WctaOrderDetailInqrDto.SearchReq dto
    ) {
        return service.getOrderDetailSpayCntrtPagesExcelDownload(dto);
    }
}
