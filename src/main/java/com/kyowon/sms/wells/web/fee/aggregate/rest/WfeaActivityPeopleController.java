package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaActivityPeopleDto.*;

import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaActivityPeopleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WFEA] EDU 실활동인원관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/activity-people")
public class WfeaActivityPeopleController {

    private final WfeaActivityPeopleService service;

    @ApiOperation(value = "EDU 실활동인원관리 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogType", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbType", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "confirmDiv", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevel1", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevel2", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevel3", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getActivityPeoples(
        @Valid
        SearchReq dto
    ) {
        return service.getActivityPeoples(dto);
    }

}
