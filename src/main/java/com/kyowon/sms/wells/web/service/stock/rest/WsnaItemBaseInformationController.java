package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemBaseInformationService;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 품목기본정보")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-base-informations")
public class WsnaItemBaseInformationController {

    private final WsnaItemBaseInformationService service;

    @ApiOperation(value = "품목기본정보 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getItemBaseInformations(
        @Valid
        SearchReq dto
    ) {
        return service.getItemBaseInformations(dto);
    }

    @GetMapping("out-of")
    public List<OstrRes> getItemBaseInformationsOutOf(
        SearchReq dto
    ) {
        return service.getItemBaseInformationsOutOf(dto);
    }

}
