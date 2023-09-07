package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageCostByTaskTypeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/out-of-storage-cost-by-task-type")
@Api(tags = "[WSNA] 업무유형별 자재출고금액 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaOutOfStorageCostByTaskController {

    private final WsnaOutOfStorageCostByTaskTypeService service;

    @ApiOperation(value = "업무유형별 자재출고금액 조회", notes = "업무유형별 / 서비스센터별로 자재출고금액을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOutOfStorageCostByTaskType(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getOutOfStorageCostByTaskType(dto, pageInfo);
    }

    @ApiOperation(value = "업무유형별 자재출고금액 엑셀 다운로드", notes = "업무유형별 / 서비스센터별로 자재출고집계금액을 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getOutOfStorageCostByTaskType(
        SearchReq dto
    ) {
        return this.service.getOutOfStorageCostByTaskType(dto);

    }
}
