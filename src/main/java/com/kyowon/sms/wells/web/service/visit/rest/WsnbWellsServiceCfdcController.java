package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWellsServiceCfdcService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wells-service-cfdc")
@Api(tags = "[WSNB] 웰스 서비스 확인서 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbWellsServiceCfdcController {
    private final WsnbWellsServiceCfdcService service;

    @ApiOperation(value = "웰스 서비스 확인서 발송을 위한 주문내역 조회", notes = "'1. Input Parameter 값을 이용하여 웰스 서비스 확인서 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "toDate", value = "기간", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "fromDate", value = "기간", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "searchType", value = "조회유형", paramType = "query", example = "1,2,3,4,5,6,7"),
        @ApiImplicitParam(name = "searchParam1", value = "조회유형 파라미터", paramType = "query", example = ""),
        @ApiImplicitParam(name = "searchParam2", value = "조회유형 파라미터", paramType = "query", example = ""),
        @ApiImplicitParam(name = "searchParam3", value = "조회유형 파라미터", paramType = "query", example = ""),
        @ApiImplicitParam(name = "searchParam4", value = "조회유형 파라미터", paramType = "query", example = ""),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getServiceProcessings(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getWellsServiceConfirmations(dto, pageInfo);
    }
}
