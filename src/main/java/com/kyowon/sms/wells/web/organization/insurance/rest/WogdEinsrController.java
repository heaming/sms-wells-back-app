package com.kyowon.sms.wells.web.organization.insurance.rest;

import com.kyowon.sms.common.web.organization.insurance.dto.ZogdEinsrDto.SaveStoppageApplcationReq;
import com.kyowon.sms.wells.web.organization.insurance.service.WogdEinsrService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@Api(tags = "[WOGD] 고용보험 REST API")
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/einsr")
@RequiredArgsConstructor
@Validated
public class WogdEinsrController {

    private final WogdEinsrService wogdEinsrService;

    @ApiOperation(value = "휴업신청 저장", notes = "휴업신청 저장한다.")
    @PostMapping("/stoppage-application")
    public SaveResponse createStoppageApplcation(
        @Valid @RequestBody
        SaveStoppageApplcationReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wogdEinsrService.createStoppageApplcation(dto))
            .build();
    }

}
