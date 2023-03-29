package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchPartsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaEngineerToolService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNA] 엔지니어 공구관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/engineer-tools")
@Slf4j
public class WsnaEngineerToolController {
    private final WsnaEngineerToolService service;

    @GetMapping
    public List<SearchRes> getEngineerToolDsbHist(SearchReq dto) {
        return service.getEngineerToolDsbHist(dto);
    }

    @GetMapping("/parts/paging")
    public PagingResult<SearchPartsRes> getEngineerToolParts(
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEngineerToolParts(pageInfo);
    }

    @PostMapping
    public SaveResponse createEngineerToolsDsb(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createEngineerToolsDsb(dto))
            .build();
    }

}
