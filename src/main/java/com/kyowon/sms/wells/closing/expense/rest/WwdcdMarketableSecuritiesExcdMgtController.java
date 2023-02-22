package com.kyowon.sms.wells.closing.expense.rest;

import com.kyowon.sms.wells.closing.expense.dto.WOpcsWhtxAdjMscrExcdDto;
import com.kyowon.sms.wells.closing.expense.service.WOpcsWhtxAdjMscrExcdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "운영비 원천세 정산(유가증권 제외)")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/sms/wells/closing/operating-cost-excd")
public class WOpcsWhtxAdjMscrExcdController {

    private final WOpcsWhtxAdjMscrExcdService service;

    @ApiOperation(value = "운영비 등록 관리", notes = "운영비 등록 관리")
    @GetMapping
    public String getWellsOrtCsRgstMngts(@Valid
                                         WOpcsWhtxAdjMscrExcdDto.SearchReq dto) {
        //운영비 금액현황
        //운영비 적요 현황

        return "";
        //return service.getWellsOrtCsRgstMngts(dto);
    }
}
