package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.io.IOException;
import java.util.List;

import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbDepositDetailService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "[WWDB] 입금내역조회(웰스입금상세)")
@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_IDVRVE + "/deposit-detail")
public class WwdbDepositDetailController {

    private final WwdbDepositDetailService service;

    /**
     * 입금내역 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @ApiOperation(value = "입금내역 조회", notes = "웰스 입금내역 정보를 검색한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getDepositDetailPages(SearchReq dto, PageInfo pageInfo) {
        return service.getDepositDetailPages(dto, pageInfo);
    }

    /**
     * 입금내역 대용량 엑셀 다운로드
     * @param req
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "입금내역 bulk 엑셀다운로드", notes = "웰스 입금내역 정보를 검색하여 엑셀다운로드한다.")
    @PostMapping("/excel-download")
    public void getProductsForBulkExcelDownload(
        @RequestBody
        ExcelBulkDownloadDto.DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getDepositDetailExcels(req, response);
    }
}
