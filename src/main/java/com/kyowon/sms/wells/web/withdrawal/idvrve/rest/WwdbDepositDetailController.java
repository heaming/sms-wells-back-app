package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbDepositDetailService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WWDB] 입금내역조회(웰스입금상세)")
@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_IDVRVE + "/deposit-detail")
public class WwdbDepositDetailController {

    private final WwdbDepositDetailService service;

    @ApiOperation(value = "입금내역 조회", notes = "웰스 입금내역 정보를 검색한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getDepositDetailPages(SearchReq dto, PageInfo pageInfo) {
        return service.getDepositDetailPages(dto, pageInfo);
    }

    @ApiOperation(value = "입금내역 엑셀다운로드", notes = "웰스 입금내역 정보를 검색하여 엑셀다운로드한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getDepositDetailExcels(SearchReq dto) {
        return service.getDepositDetailExcels(dto);
    }
}
