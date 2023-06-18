package com.kyowon.sms.wells.web.deduction.rds.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.common.web.deduction.rds.dto.ZdecRdsAnAccountErrorMgtDto.SearchRdsAnAccountErrorChkReq;
import com.kyowon.sms.common.web.deduction.rds.dvo.ZdecRdsAnAccountErrorMgtDvo;
import com.kyowon.sms.wells.web.deduction.rds.service.WdecRdsAnAccountErrorMgtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WDEC] RDS 계좌오류 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/rds")
public class WdecRdsAnAccountErrorMgtController {

    private final WdecRdsAnAccountErrorMgtService wdecRdsAnAccountErrorMgtService;

    @ApiOperation(value = "RDS 계좌오류 체크 조회", notes = " 검색조건을 받아 RDS 계좌오류 여부를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "fnitCd", value = "은행구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "acnoEncr", value = "계좌번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrAcUswyCd", value = "계좌용도코드", paramType = "query", example = ""),
    })
    @GetMapping("/an-account-error-chk")
    public ZdecRdsAnAccountErrorMgtDvo getRdsAnAccountErrorChk(SearchRdsAnAccountErrorChkReq dto) {
        return wdecRdsAnAccountErrorMgtService.getRdsAnAccountErrorChk(dto);
    }
}
