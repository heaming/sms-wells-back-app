package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundCurrentStatusService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 환불 현황")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/refund-status")
public class WwdbRefundCurrentStatusController {

    private final WwdbRefundCurrentStatusService service;

    /**
     * 환불 내역 목록 조회 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    @ApiOperation(value = "환불 내역", notes = "환불 내역 목록")
    @GetMapping("/paging")
    public PagingResult<SearchRefundHistoryRes> getRefundHistoryPages(
        @ApiParam @Valid
        SearchRefundHistoryReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundHistoryPages(req, pageInfo);
    }

    /**
     * 환불 내역 목록 조회 / 엑셀 다운로드
     * @param req
     * @return
     */
    @ApiOperation(value = "환불 내역 목록 엑셀 다운로드", notes = "환불 내역 목록 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRefundHistoryRes> getRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return service.getRefundHistoryExcels(req);
    }

    /**
     * 환불 내역 목록 조회 / 엑셀 다운로드
     * @param req
     * @return
     */
    @ApiOperation(value = "환불 내역 목록 합계", notes = "환불 내역 목록 합계를 구한다.")
    @GetMapping("/sum")
    public WwdbRefundCurrentStatusDto.SearchRefundHistoryTotalRes getRefundHistorySum(
        SearchRefundHistoryReq req
    ) {
        return service.getRefundHistorySum(req);
    }

    /**
     * 카드사별 환불내역 목록 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    @ApiOperation(value = "카드사별 환불내역 목록", notes = "카드사별 환불내역 목록")
    @GetMapping("/card/paging")
    public PagingResult<SearchCardRefundHistoryRes> getCardRefundHistoryPages(
        @ApiParam @Valid
        SearchRefundHistoryReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCardRefundHistoryPages(req, pageInfo);
    }

    /**
     * 카드사별 환불내역 목록 / 엑셀 다운로드
     * @param req
     * @return
     */
    @ApiOperation(value = "카드사별 환불내역 목록 엑셀 다운로드", notes = "카드사별 환불내역 목록 엑셀 다운로드")
    @GetMapping("/card/excel-download")
    public List<SearchCardRefundHistoryRes> getCardRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return service.getCardRefundHistoryExcels(req);
    }

    /**
     * 전금내역 목록 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    @ApiOperation(value = "전금내역 목록", notes = "전금내역 목록")
    @GetMapping("/balance-transfer/paging")
    public PagingResult<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryPages(
        @ApiParam @Valid
        SearchRefundHistoryReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBalanceTransferRefundHistoryPages(req, pageInfo);
    }

    /**
     * 전금내역 목록 / 엑셀 다운로드
     * @param req
     * @return
     */
    @ApiOperation(value = "전금내역 목록 엑셀 다운로드", notes = "전금내역 목록 엑셀 다운로드")
    @GetMapping("/balance-transfer/excel-download")
    public List<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return service.getBalanceTransferRefundHistoryExcels(req);
    }

}
