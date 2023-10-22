package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemLocationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0210P01 품목위치관리 팝업, W-SV-U-0137M01 품목위치 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-10-10
 */

@Api(tags = "[WSNA] 품목위치관리 REST API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-locations")
public class WsnaItemLocationController {

    private final WsnaItemLocationService service;

    @ApiOperation(value = "품목위치관리 팝업 페이징 조회.", notes = "품목위치관리 페이지를 조회한다.")
    @GetMapping
    public PagingResult<SearchRes> getItemLocations(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return service.getItemLocations(dto, pageInfo);
    }

    @ApiOperation(value = "품목위치 관리 팝업 엑셀다운로드.", notes = "품목위치관리 엑셀다운로드 할 데이터를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getItemLocationsExcelDownload(@Valid
    SearchReq dto) {
        return service.getItemLocations(dto);
    }

    @ApiOperation(value = "품목위치 관리 팝업 저장.", notes = "품목위치관리 입력한 내용을 저장한다.")
    @PutMapping
    public SaveResponse saveItemLocations(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> list
    ) {
        return SaveResponse
            .builder()
            .processCount(service.saveItemLocations(list))
            .build();
    }

    @ApiOperation(value = "품목위치 관리 상단 창고 조회.", notes = "품목위치관리 상단 창고항목을 조회한다.")
    @GetMapping("/stock")
    public List<SearchStockCdRes> getItemLocationsStockCd() {
        return service.getItemLocationsStockCd();
    }

    @ApiOperation(value = "품목위치 관리 페이징 조회.", notes = "품목위치관리 항목을 조회한다.")
    @GetMapping("/locations/paging")
    public PagingResult<SearchLocationRes> getStockItemLocations(
        SearchLocationReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getStockItemLocations(dto, pageInfo);
    }

    @ApiOperation(value = "품목위치 관리 엑셀다운로드.", notes = "품목위치관리 엑셀다운로드할 데이터를 조회한다.")
    @GetMapping("/locations/excel-download")
    public List<SearchLocationRes> getStockItemLocationsExcelDownload(@Valid
    SearchLocationReq dto) {
        return service.getStockItemLocationsExcelDownload(dto);
    }

    @ApiOperation(value = "품목위치 관리 저장.", notes = "품목위치관리에서 입력한 내용을 저장한다.")
    @PutMapping("/locations")
    public SaveResponse saveStockItemLocations(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateLocationReq> list
    ) {
        return SaveResponse
            .builder()
            .processCount(service.saveStockItemLocations(list))
            .build();
    }

    @ApiOperation(value = "품목위치 관리 상단 창고 조회.", notes = "품목위치관리 상단 창고항목을 조회한다.")
    @PutMapping("/standard/locations")
    public int saveStandardWarehouse(
        @RequestBody
        @Valid
        CreateWareLocationReq dto
    ) {
        return service.saveStandardWarehouse(dto);
    }
}
