package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAssignPsicTfDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncAssignPsicTfService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 고객 담당 지역담당자로 업데이트")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/assign-psic-tfs")
public class WsncAssignPsicTfController {

    private final WsncAssignPsicTfService service;

    @ApiOperation(value = "고객 담당 지역담당자로 업데이트", notes = "조회조건에 따른 고객 담당 지역담당자로 업데이트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = false),
    })
    @PostMapping
    public SaveResponse processAssignPsicTf(
        @RequestBody
        WsncAssignPsicTfDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processAssignPsicTf(dto)).build();
    }

    @ApiOperation(value = "고객 담당 지역담당자로 업데이트", notes = "조회조건에 따른 고객 담당 지역담당자로 업데이트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @PostMapping("/cst-sv-asn-no")
    public SaveResponse processAssignPsicTfByPk(
        @Valid
        @RequestBody
        WsncAssignPsicTfDto.SearchProcessPkReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processAssignPsicTfByPk(dto)).build();
    }
}
