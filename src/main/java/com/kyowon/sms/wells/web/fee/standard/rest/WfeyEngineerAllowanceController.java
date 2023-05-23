package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeyEngineerAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceReq;

@Api(tags = "[WFEY] 엔지니어 수당 지급단가")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/engineer-allowances")
@Slf4j
public class WfeyEngineerAllowanceController {

     private final WfeyEngineerAllowanceService engineerAllowanceService;

    @ApiOperation(value = "엔지니어 수당 단가 록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "regionLevelDivideCode", value = "급지구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "productGroupCode", value = "상품그룹코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "productGroupDetailCode", value = "상품그룹상세코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "currentlyApplyDataYn", value = "현재적용데이터여부", paramType = "query", required = true),
    })
    @GetMapping("/unit-prices")
    public List<SearchAllowanceUnitPriceRes> getEngineerAwUprcs(@ApiParam @Valid SearchAllowanceUnitPriceReq req) {
        return engineerAllowanceService.getEngineerAwUprcs(req);
    }

}
