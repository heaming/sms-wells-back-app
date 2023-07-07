package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCancelBaseService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 취소등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbCancelBaseController {

    private final WctbCancelBaseService service;


    @ApiOperation(value = "취소등록 - 기본 정보 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/cancel-base-infos")
    public List<SearchRes> getCancelBases(
        @Valid
        SearchReq dto
    ) {
        return service.getCancelBases(dto);
    }
}
