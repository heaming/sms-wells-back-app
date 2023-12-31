package com.kyowon.sms.wells.web.fee.confirm.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

import com.kyowon.sms.wells.web.fee.confirm.service.WfeeFeeSpecificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WFEE] WELLS 수수료 지급명세서")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/fee-specifications")
public class WfeeFeeSpecificationController {

    private final WfeeFeeSpecificationService service;

    @ApiOperation(value = "EDU 수수료 지급명세서 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel1", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel2", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel3", value = "", paramType = "query", required = false)
    })
    // 수수료코드 가져옴
    @GetMapping("/fee-codes")
    public List<SearchFeeCdRes> getFeeCodes(
        @Valid
        SearchReq dto
    ) {
        return service.getFeeCodes(dto);
    }

    @GetMapping
    public List<HashMap<String, Object>> getFeeSpecifications(
        @Valid
        SearchReq dto
    ) {
        return service.getFeeSpecifications(dto);
    }

    @ApiOperation(value = "EDU 수수료 지급명세서 리스트 리포트 생성", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel1", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel2", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevel3", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "isSum", value = "", paramType = "query", required = false)
    })
    // 수수료코드 가져옴
    @GetMapping("/report")
    public Map<String, Object> getFeeSpecificationsReport(
        @Valid
        SearchReq dto
    ) {
        return service.getFeeSpecificationsReport(dto);
    }
}
