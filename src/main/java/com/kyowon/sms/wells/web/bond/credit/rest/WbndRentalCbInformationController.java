package com.kyowon.sms.wells.web.bond.credit.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbInformationDto.SearchContractPresentStateReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbInformationDvo;
import com.kyowon.sms.wells.web.bond.credit.service.WbndRentalCbInformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBND] 렌탈CB 정보")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-cb-inf")
public class WbndRentalCbInformationController {

    private final WbndRentalCbInformationService service;

    @ApiOperation(value = "렌탈CB 정보 - 계약현황(미해지 계약) 조회", notes = "조회조건에 일치하는 렌탈CB 정보 - 계약현황(미해지 계약) 정보를 조회한다.")
    @GetMapping("/contract-present-state")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "keyDiv", value = "식별자구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsdnNo", value = "식별번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "inqRsnCd", value = "조회사유", paramType = "query", required = true),
        @ApiImplicitParam(name = "inqwtcnRsnCd", value = "조회동의", paramType = "query", required = true),
        @ApiImplicitParam(name = "InsAdrZP1", value = "우편번호1", paramType = "query", required = true),
        @ApiImplicitParam(name = "InsAdrZP2", value = "우편번호2", paramType = "query", required = true),
        @ApiImplicitParam(name = "InsAdrWAD1", value = "주소", paramType = "query", required = true),
        @ApiImplicitParam(name = "InsAdrWAD2", value = "상세주소1", paramType = "query", required = true),
        @ApiImplicitParam(name = "InsAdrWAD3", value = "상세주소2", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHpNo1", value = "핸드폰번호1", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHpNo2", value = "핸드폰번호2", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHpNo3", value = "핸드폰번호3", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHomNo1", value = "전화번호1", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHomNo2", value = "전화번호2", paramType = "query", required = true),
        @ApiImplicitParam(name = "insHomNo3", value = "전화번호3", paramType = "query", required = true),
    })
    public List<WbndRentalCbInformationDvo> getContractPresentStates(SearchContractPresentStateReq dto)
        throws Exception {
        return service.getContractPresentStates(dto);
    }
}
