package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.service.WwdcdMarketableSecuritiesMgtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "운영비 원천세 정산(유가증권)")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/sms/wells/expense/operating-cost/marketable-securities/Withholding-tax-adj")
public class WwdcdMarketableSecuritiesMgtController {

    private final WwdcdMarketableSecuritiesMgtService wOpcsWhtxAdjMscrService;
}
