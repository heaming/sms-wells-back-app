package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.service.stock.service.WsnaAsConsumablesStoreService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "[WSNA] AS소모품입고관리(엑셀업로드)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-consumables-stores")
public class WsnaAsConsumablesStoreController {

    private final WsnaAsConsumablesStoreService service;

    @ApiOperation(value = "AS소모품입고관리(엑셀업로드) 페이징 조회", notes = "AS소모품입고관리 (엑셀업로드) 를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsConsumablesStorePages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getAsConsumablesStorePages(dto, pageInfo);
    }

    @ApiOperation(value = "AS소모품입고관리(엑셀업로드) 엑셀 다운로드", notes = "AS소모품입고관리 엑셀다운로드를 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getAsConsumablesStoresForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getAsConsumablesStoresForExcelDownload(dto);
    }

    @ApiOperation(value = "AS소모품입고관리 엑셀업로드", notes = "AS소모품입고관리 엑셀업로드를 한다.")
    @PostMapping("/excel-upload")
    public ExcelUploadDto.UploadRes saveAsConsumablesStoresExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveAsConsumablesStoresExcelUpload(file);
    }

    @ApiOperation(value = "AS소모품입고관리 저장", notes = "AS소모품입고관리 CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping
    public SaveResponse saveAsConsumablesStores(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveAsConsumablesStores(dtos)).build();
    }

    @ApiOperation(value = "AS소모품입고관리 삭제", notes = "AS소모품입고관리 CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @DeleteMapping
    public int removeAsConsumablesStores(
        @Valid
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return this.service.removeAsConsumablesStores(dtos);

    }

    @ApiOperation(value = "조회조건에 해당하는 품목코드 조회", notes = "조회조건에 해당하는 품목코드를 조회한다.")
    @GetMapping("/filter-items")
    public List<SearchRes> getItemProductCodes(
        SearchItemReq dto
    ) {
        return this.service.getItemProductCodes(dto);
    }

}
