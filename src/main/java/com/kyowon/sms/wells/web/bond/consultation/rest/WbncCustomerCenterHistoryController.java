package com.kyowon.sms.wells.web.bond.consultation.rest;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerCenterHistoryDto.FindRes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.service.WbncCustomerCenterHistoryService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "[WBNC] 채권상담 고객센터 상담이력")
@RequestMapping(BnBondConst.REST_URL_V1 + "/customer-center-histories")
public class WbncCustomerCenterHistoryController {
    private final WbncCustomerCenterHistoryService service;

    @ApiOperation(value = "[EAI_WSVO1013] 고객센터 상담이력 조회", notes = "고객번호에 대한 고객센터 상담이력 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping
    public List<FindRes> getCustomerCenterHistories(
        @RequestParam
        String cstNo
    ) {
        return service.getCustomerCenterHistories(cstNo);
    }
}
