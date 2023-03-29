package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchHistoryRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbVisitStopService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 방문중지 조회")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/visit-stops")
public class WsnbVisitStopController {

    private final WsnbVisitStopService service;

    @GetMapping("/{cntrNo}-{cntrSn}")
    public List<SearchCodeRes> getVisitStopCodes(
        @PathVariable
        String cntrNo,
        @PathVariable
        String cntrSn
    ) {
        return service.getVisitStopCodes(cntrNo, cntrSn);
    }

    @GetMapping("/history")
    public List<SearchHistoryRes> getVisitStopHistory(SearchCodeReq dto) {
        return service.getVisitStopHistory(dto);
    }

    @PostMapping
    public SaveResponse createVisitStop(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createVisitStop(dto))
            .build();
    }
}
