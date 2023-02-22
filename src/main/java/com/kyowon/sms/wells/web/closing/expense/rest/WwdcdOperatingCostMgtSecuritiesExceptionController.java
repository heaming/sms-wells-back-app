package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.service.WwdcdOperatingCostMgtSecuritiesExceptionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "운영비 등록 관리 - 유가증권 제외")
@RequiredArgsConstructor
@RestController
@Slf4j
//@RequestMapping(CtContractConst.REST_URL_V1 + "/operationgCost")
@RequestMapping("/api/v1/sms/wells/expense/operating-cost/marketable-securities-excd")
public class WwdcdOperatingCostMgtSecuritiesExceptionController {

    private final WwdcdOperatingCostMgtSecuritiesExceptionService service;
}
