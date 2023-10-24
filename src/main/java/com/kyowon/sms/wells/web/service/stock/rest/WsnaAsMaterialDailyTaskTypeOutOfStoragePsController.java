package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialDailyTaskTypeOutOfStoragePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/as-material-daily-task-type-out-of-storage-ps")
@Api(tags = "[WSNA] A/S 자재 일일 출고 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaAsMaterialDailyTaskTypeOutOfStoragePsController {
    private final WsnaAsMaterialDailyTaskTypeOutOfStoragePsService service;

    @ApiOperation(value = "A/S 자재 일일 출고 현황 조회", notes = "A/S 자재 일일 출고 현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsMaterialDailyTaskTypeOutOfStoragePss(SearchReq dto, @Valid PageInfo pageInfo){
        return service.getAsMaterialDailyTaskTypeOutOfStoragePss(dto, pageInfo);
    }
}
