package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemLocationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 품목위치관리 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-locations")
public class WsnaItemLocationController {

    private final WsnaItemLocationService service;

    @GetMapping
    public PagingResult<SearchRes> getItemLocations(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return service.getItemLocations(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<SearchRes> getItemLocationsExcelDownload(@Valid
    SearchReq dto) {
        return service.getItemLocations(dto);
    }

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

    @GetMapping("/locations/paging")
    public PagingResult<SearchLocationRes> getStockItemLocations(@Valid
    SearchLocationReq dto, @Valid
    PageInfo pageInfo) {
        return service.getStockItemLocations(dto, pageInfo);
    }

    @GetMapping("/locations/excel-download")
    public List<SearchLocationRes> getStockItemLocationsExcelDownload(@Valid
    SearchLocationReq dto) {
        return service.getStockItemLocationsExcelDownload(dto);
    }

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

    @PutMapping("/standard/locations")
    public int saveStandardWarehouse(
        @RequestBody
        @Valid
        CreateWareLocationReq dto
    ) {
        return service.saveStandardWarehouse(dto);
    }
}
