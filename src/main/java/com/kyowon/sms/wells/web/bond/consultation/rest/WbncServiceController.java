package com.kyowon.sms.wells.web.bond.consultation.rest;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncServiceDto.FindRes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.service.WbncServiceService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(BnBondConst.REST_URL_V1 + "/services")
@Api(tags = "[WBNC] 서비스 상세")
public class WbncServiceController {
    private final WbncServiceService service;

    @ApiOperation(value = "서비스 상세내역 조회", notes = "서비스 상세내역을 조회한다.")
    @GetMapping
    public List<FindRes> getServices(@RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getServices(cntrNo, cntrSn);
    }
}
