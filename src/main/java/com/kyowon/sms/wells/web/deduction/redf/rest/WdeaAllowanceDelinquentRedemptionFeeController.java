package com.kyowon.sms.wells.web.deduction.redf.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceDelinquentRedemptionFeeDto;
import com.kyowon.sms.wells.web.deduction.redf.service.WdeaAllowanceDelinquentRedemptionFeeService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEA] 수당/연체 되물림")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/redf/allowance-delinquent-redemption-fees")
public class WdeaAllowanceDelinquentRedemptionFeeController {

    private final WdeaAllowanceDelinquentRedemptionFeeService service;

    @ApiOperation(value = "수당/연체 되물림", notes = "수당/연체 되물림 데이터를 조회하고 결과를 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDvCd", value = "구분", paramType = "query"),
        @ApiImplicitParam(name = "whtxRepDvCd", value = "유형", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> getAllowanceDelinquentRedemptionFees(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    )
        throws Exception {

        return service.getAllowanceDelinquentRedemptionFees(dto, pageInfo);

    }

    @ApiOperation(value = "수당/연체 되물림 엑셀다운로드", notes = "수당 되물림 현황 목록을 조회하고 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> getAllowanceDelinquentRedemptionFeesForExcelDownload(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto
    ) throws Exception {

        return service.getAllowanceDelinquentRedemptionFeesForExcelDownload(dto);

    }
}
