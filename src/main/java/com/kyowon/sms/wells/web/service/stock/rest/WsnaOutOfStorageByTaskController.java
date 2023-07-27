package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageByTaskTypeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/out-of-storage-by-task-type")
@Api(tags = "[WSNA] 업무유형별 자재출고현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaOutOfStorageByTaskController {

    private final WsnaOutOfStorageByTaskTypeService service;

    @ApiOperation(value = "업무유형별 자재출고현황 조회", notes = "업무유형별 / 서비스센터별로 자재출고집계현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWarehouseOgs(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getOutOfStorageByTaskType(dto, pageInfo);
    }

    @ApiOperation(value = "업무유형별 자재출고현황 엑셀 다운로드", notes = "업무유형별 / 서비스센터별로 자재출고집계현황을 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getWarehouseOgsExcelDownload(
        SearchReq dto
    ) {
        return this.service.getOutOfStorageByTaskType(dto);

    }
}
