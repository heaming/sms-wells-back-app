package com.kyowon.sms.wells.closing.expense.rest;

import com.kyowon.sms.wells.closing.expense.service.WOpcsWhtxAdjMscrService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "운영비 원천세 정산(유가증권)")
@RequiredArgsConstructor
@RestController
@Slf4j
public class WOpcsWhtxAdjMscrController {

    private final WOpcsWhtxAdjMscrService wOpcsWhtxAdjMscrService;
}
