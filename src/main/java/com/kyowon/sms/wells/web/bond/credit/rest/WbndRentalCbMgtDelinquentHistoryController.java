package com.kyowon.sms.wells.web.bond.credit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SendReq;
import com.kyowon.sms.wells.web.bond.credit.service.WbndRentalCbMgtDelinquentHistoryService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBND] 렌탈CB 관리 - 연체이력 조회 ")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-cb-mgt/histories")
public class WbndRentalCbMgtDelinquentHistoryController {

    private final WbndRentalCbMgtDelinquentHistoryService service;

    @ApiOperation(value = "렌탈CB 관리 연체이력 조회", notes = "렌탈CB 연체대상 이력을 조회한다.")
    @ApiImplicitParams(value = {
    })
    @GetMapping
    public List<SearchRes> getRentalCbMgtDelinquentHistories(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalCbMgtDelinquentHistories(dto);
    }

    @ApiOperation(value = "렌탈CB 관리 연체이력 save", notes = "렌탈CB 관리 연체이력의 Nice 제외여부를 save 한다.")
    @PutMapping
    public SaveResponse saveRentalCbMgtDelinquentHistories(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.saveRentalCbMgtDelinquentHistories(dtos)).build();
    }

    @ApiOperation(value = "렌탈CB 관리 연체이력 알림톡 발송", notes = "렌탈CB 이력 알림톡 발송한다.")
    @PostMapping
    public SaveResponse sendRentalCbMgtDelinquentHistories(
        @RequestBody
        @Valid
        @NotEmpty
        List<SendReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.sendRentalCbMgtDelinquentHistories(dtos)).build();
    }
}
