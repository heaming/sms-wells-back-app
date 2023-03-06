package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbIntegrationDepositService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 통합입금목록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/integration-deposit")
public class WwdbIntegrationDepositController {
    private final WwdbIntegrationDepositService service;

    @ApiOperation(value = "통합입금목록 조회", notes = " 검색조건을 받아 통합입금목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getIntegrationDepositPages(SearchReq dto, PageInfo pageInfo) {
        return service.getIntegrationDepositPages(dto, pageInfo);
    }

    @ApiOperation(value = "통합입금목록 엑셀다운로드", notes = " 검색조건을 받아 통합입금목록을 엑셀다운로드한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getIntegrationDepositExcels(SearchReq dto) {
        return service.getIntegrationDepositExcels(dto);
    }
}
