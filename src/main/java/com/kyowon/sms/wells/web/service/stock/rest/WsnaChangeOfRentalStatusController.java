package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.service.WsnaChangeOfRentalStatusService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaChangeOfRentalStatusDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 렌탈 상태변경 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/change-rental-status")
public class WsnaChangeOfRentalStatusController {

    private final WsnaChangeOfRentalStatusService service;

    @ApiOperation(value = "렌탈 상태변경 현황 페이징 조회", notes = "렌탈 상태변경 현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getChangeOfRentalStatusPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getChangeOfRentalStatusPages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈 상태변경 현황 엑셀 다운로드", notes = "렌탈 상태변경 현황페이지를 엑셀 다운로드 처리한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getChangeOfRentalStatussForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getChangeOfRentalStatussForExcelDownload(dto);
    }

    @ApiOperation(value = "렌탈 상태변경 현황 조회조건 품목코드 조회", notes = "렌탈 상태변경 현황페이지의 조회조건인 품목코드를 조회한다")
    @GetMapping("/products")
    public List<SearchItmPdCdRes> getChangeOfRentalStatusItmPdCd() {
        return service.getChangeOfRentalStatusItmPdCd();
    }

    @ApiOperation(value = "렌탈 상태변경 현황 조회조건 창고를 조회", notes = "렌탈 상태변경 현황페이지의 조회조건인 창고코드를 조회한다")
    @GetMapping("/warehouses")
    public List<SearchWarehouseRes> getChangeOfRentalStatusWarehouse(
        SearchWarehouseReq dto
    ) {
        return service.getChangeOfRentalStatusWarehouse(dto);
    }
}
