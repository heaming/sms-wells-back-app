package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbBsPeriodChangeHistoryListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-period-change-history-list")
@Api(tags = "[WSNB] B/S 주기변경 이력 조회 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbBsPeriodChangeHistoryListController {

    private final WsnbBsPeriodChangeHistoryListService service;

    @GetMapping("/paging")
    public PagingResult<SearchRes> getBsPeriodChangeHistoryList(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getBsPeriodChangeHistoryList(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<SearchRes> getBsPeriodChangeHistoryListForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getBsPeriodChangeHistoryListForExcelDownload(dto);
    }

}
