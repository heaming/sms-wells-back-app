package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundPresentStateDto;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundPresentStateDto.*;

import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundPresentStateService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import lombok.RequiredArgsConstructor;

@Api(tags = "[입금관리 - 개별수납] wells 환불현황")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/refund-present-state")
public class WwdbRefundPresentStateController {

    private final WwdbRefundPresentStateService service;

    @ApiOperation(value = "wells 환불현황 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "wkStartDtm", value = "작업일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkEndDtm", value = "작업일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "rfndDvCd", value = "환불구분", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<WwdbRefundPresentStateDto.SearchRes> getRefundPresentStatePages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getRefundPresentStatePages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 환불현황 엑셀 다운로드", notes = "")
    @GetMapping("/excel-download")
    public List<SearchRes> getRefundPresentStatesForExcelDownload(
        @ApiParam
        @Valid
        SearchReq dto
    ) {
        return service.getRefundPresentStatesForExcelDownload(dto);
    }
}
