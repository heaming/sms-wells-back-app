package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaItemReceivingAndPayingService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0252M01 품목별 수불현황 Controller
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.17
 */
@Api(tags = "[WSNA] 품목별수불현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/receipts-and-payments")
public class WsnaItemReceivingAndPayingController {

    private final WsnaItemReceivingAndPayingService service;

    @GetMapping
    @ApiOperation(value = "품목별수불현황 조회", notes = "조회조건에 해당하는 품목별 수불현황을 조회한다.")
    public List<SearchRes> getReceiptsAndPayments(
        SearchReq dto
    ) {
        return service.getReceiptsAndPaymentssForExcelDownload(dto);
    }

    @ApiOperation(value = "품목별수불현황 조회 엑셀 다운로드", notes = "조회조건에 해당하는 품목별 수불현황을 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getReceiptsAndPaymentssForExcelDownload(dto);
    }

    @ApiOperation(value = "일자별수불현황 페이징 조회", notes = "파라미터로 넘어온 데이터로 일자별 수불현황을 조회한다.")
    @GetMapping("/date")
    public List<SearchDateRes> getDateReceivingAndPayings(
        @Valid
        SearchDateReq dto
    ) {
        return service.getDateReceivingAndPayingsForExcelDownload(dto);
    }

    @ApiOperation(value = "일자별수불현황 엑셀 다운로드", notes = "파라미터로 넘어온 데이터로 일자별 수불현황을 엑셀다운로드한다.")
    @GetMapping("/date/excel-download")
    public List<SearchDateRes> getDateReceivingAndPayingsForExcelDownload(
        @Valid
        SearchDateReq dto
    ) {
        return service.getDateReceivingAndPayingsForExcelDownload(dto);
    }
}
