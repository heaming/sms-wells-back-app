package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWellsServiceCfdcService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wells-service-cfdc")
@Api(tags = "[WSNB] 웰스 서비스 확인서 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbWellsServiceCfdcController {
    private final WsnbWellsServiceCfdcService service;

    @ApiOperation(value = "웰스 서비스 확인서 발송을 위한 주문내역 조회", notes = "Input Parameter 값을 이용하여 웰스 서비스 확인서 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "toDate", value = "기간", paramType = "query", required = true, example = "20230601"),
        @ApiImplicitParam(name = "fromDate", value = "기간", paramType = "query", required = true, example = "20230601"),
        @ApiImplicitParam(name = "searchType", value = "조회유형", paramType = "query", required = true, example = "1,2,3,4,5,6,7"),
        @ApiImplicitParam(name = "cntrNo", value = "", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "", paramType = "query"),
        @ApiImplicitParam(name = "pno1", value = "", paramType = "query"),
        @ApiImplicitParam(name = "pno2", value = "", paramType = "query"),
        @ApiImplicitParam(name = "pno3", value = "", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "", paramType = "query"),
        @ApiImplicitParam(name = "fromOgCd", value = "", paramType = "query"),
        @ApiImplicitParam(name = "toOgCd", value = "", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWellsServiceConfirmations(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getWellsServiceConfirmations(dto, pageInfo);
    }

    @ApiOperation(value = "웰스 서비스 확인서 발송을 위한 주문내역 조회 (엑셀 다운로드)", notes = "Input Parameter 값을 이용하여 웰스 서비스 확인서 리스트를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getWellsServiceConfirmationsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getWellsServiceConfirmationsForExcelDownload(dto);
    }

    @ApiOperation(value = "웰스 서비스 확인서 출력", notes = "웰스 서비스 확인서 출력한다.")
    @PostMapping("/report")
    public int printReport(
        @Valid
        @RequestBody
        ReportReq dto
    ) throws Exception {
        return service.printWellsServiceConfirmationByReport(dto);
    }

    @ApiOperation(value = "웰스 서비스 확인서 발송 (카카오 알림톡)", notes = "웰스 서비스 확인서 발송한다.")
    @PostMapping("/kakao")
    public int sendKakao(
        @Valid
        @RequestBody
        KakaoReq dto
    ) throws Exception {
        return service.sendWellsServiceConfirmationByKakao(dto);
    }

    @ApiOperation(value = "웰스 서비스 확인서 발송 (이메일)", notes = "웰스 서비스 확인서 발송한다.")
    @PostMapping("/email")
    public int sendEmail(
        @Valid
        @RequestBody
        EmailReq dto
    ) throws Exception {
        return service.sendWellsServiceConfirmationByEmail(dto);
    }

    @ApiOperation(value = "웰스 서비스 확인서 (카카오 알림톡) 내역 조회", notes = "웰스 서비스 확인서 (카카오 알림톡) 내역 조회한다.")
    @GetMapping("/kakao/paging")
    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForKakao(
        @Valid
        HistoryReq dto,
        PageInfo pageInfo
    ) {
        return service.getWellsServiceConfirmationHistoriesForKakao(dto, pageInfo);
    }

    @ApiOperation(value = "웰스 서비스 확인서 (카카오 알림톡) 내역 조회", notes = "웰스 서비스 확인서 (카카오 알림톡) 내역 조회한다.")
    @GetMapping("/email/paging")
    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForEmail(
        @Valid
        HistoryReq dto,
        PageInfo pageInfo
    ) {
        return service.getWellsServiceConfirmationHistoriesForEmail(dto, pageInfo);
    }
}
