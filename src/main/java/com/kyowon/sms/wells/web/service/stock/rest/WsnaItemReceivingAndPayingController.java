package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemReceivingAndPayingService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 품목별수불현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/receipts-and-payments")
public class WsnaItemReceivingAndPayingController {

    private final WsnaItemReceivingAndPayingService service;

    @ApiOperation(value = "품목별수불현황 조회", notes = "조회조건에 해당하는 품목별 수불현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getReceiptsAndPaymentsPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getReceiptsAndPaymentsPages(dto, pageInfo);
    }

    @ApiOperation(value = "품목별수불현황 조회 엑셀 다운로드", notes = "조회조건에 해당하는 품목별 수불현황을 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getReceiptsAndPaymentssForExcelDownload(dto);
    }
}
