package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBeforeServiceAsnBatDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBeforeServiceAsnBatService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] BS 배정 배치")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/before-service-asn-bats")
public class WsncBeforeServiceAsnBatController {

    private final WsncBeforeServiceAsnBatService service;

    @ApiOperation(value = "BS 배정 배치", notes = "BS 배정 배치")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "작업담당자사번", paramType = "query", required = false),
    })
    @PostMapping
    public SaveResponse processRegularBfsvcAsn(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(service.processBeforeServiceAsnBat(dto))
            .build();
    }
}
