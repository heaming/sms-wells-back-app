package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncCompanyIstStateService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 회사설치 (8888코드) 현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/company-ist-state")
@Slf4j
public class WsncCompanyIstStateController {

    private final WsncCompanyIstStateService service;

    @ApiOperation(value = "회사설치 (8888코드) 현황 - 전체 조회", notes = "전체 현황 조회")
    @GetMapping("/all/paging")
    public PagingResult<WsncCompanyIstStateDto.SearchAllRes> getCompanyIstStateAll(
        WsncCompanyIstStateDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyIstStateAll(dto, pageInfo);
    }

}
