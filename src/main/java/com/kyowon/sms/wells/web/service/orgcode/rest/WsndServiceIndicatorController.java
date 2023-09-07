package com.kyowon.sms.wells.web.service.orgcode.rest;

import com.kyowon.sms.wells.web.service.orgcode.service.WsndServiceIndicatorService;
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
import java.util.List;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchRes;

@Api(tags = "웰스매니저 서비스종합지표")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-indicator")
public class WsndServiceIndicatorController {
    private final WsndServiceIndicatorService service;


    @ApiOperation(value = "웰스매니저 서비스종합지표 조회(Paging)", notes = "조회조건에 따른 페이징 조회")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getServiceIndicators(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getServiceIndicators(dto, pageInfo);
    }

    @ApiOperation(value = "웰스매니저 서비스종합지표 엑셀다운로드", notes = "조회조건에 따른 엑셀다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getServiceIndicators(SearchReq dto) {
        return service.getServiceIndicators(dto);
    }
}
