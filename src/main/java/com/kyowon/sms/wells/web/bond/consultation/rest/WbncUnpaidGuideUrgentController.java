package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentDto.*;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncUnpaidGuideUrgentService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 미납요금 안내/촉구 대상")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/unpaid-guide-urgents")
public class WbncUnpaidGuideUrgentController {

    private final WbncUnpaidGuideUrgentService service;

    @ApiOperation(value = "미납요금 안내/촉구 대상 페이징 조회", notes = "검색 조건에 따른 대상 List 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ucAmtFwTpCd", value = "미수금액발송유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "stateDvCd", value = "진행상태", paramType = "query", required = true),
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "bndBizDvCd", value = "채권업무구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "fromDlqMcn", value = "시작연체개월", paramType = "query", required = true),
        @ApiImplicitParam(name = "toDlqMcn", value = "종료연체개월", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "fromTotNpdAmt", value = "시작총미납액", paramType = "query", required = true),
        @ApiImplicitParam(name = "toTotNpdAmt", value = "종료총미납액", paramType = "query", required = true),
        @ApiImplicitParam(name = "ojWkDt", value = "자료생성 작업일자", paramType = "query"),
        @ApiImplicitParam(name = "ojPyTmlmDt", value = "자료생성 납입기한", paramType = "query"),
        @ApiImplicitParam(name = "wkDt", value = "작업일자", paramType = "query"),
        @ApiImplicitParam(name = "pyTmlmDt", value = "납입기한", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getUnpaidGuideUrgentPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getUnpaidGuideUrgentPages(dto, pageInfo);
    }

    @ApiOperation(value = "미납요금 안내/촉구 대상 엑셀 다운로드", notes = "엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getUnpaidGuideUrgentsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getUnpaidGuideUrgentsForExcelDownload(dto);
    }

    @ApiOperation(value = "자료생성 데이터 체크", notes = "자료생성 버튼 클릭 시 생성된 데이터 있는지 체크한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ucAmtFwTpCd", value = "미수금액발송유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ojWkDt", value = "자료생성 작업일자", paramType = "query", required = true),
    })
    @GetMapping("/check-object")
    public CheckRes checkUnpaidGuideUrgentObjects(
        @Valid
        CheckReq dto
    ) {
        return this.service.checkUnpaidGuideUrgentObjects(dto);
    }

    @ApiOperation(value = "고객번호기준생성 데이터 체크", notes = "고객번호기준생성 버튼 클릭 시 생성된 데이터 있는지 체크한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ucAmtFwTpCd", value = "미수금액발송유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ojWkDt", value = "자료생성 작업일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkDt", value = "작업일자", paramType = "query", required = true),
    })
    @GetMapping("/check-customer")
    public CheckRes checkUnpaidGuideUrgentCustomers(
        @Valid
        CheckReq dto
    ) {
        return this.service.checkUnpaidGuideUrgentCustomers(dto);
    }

    @ApiOperation(value = "자료생성 확정여부 저장", notes = "저장 버튼 클릭 시 확정여부를 저장한다.")
    @PostMapping
    public SaveResponse saveUnpaidGuideUrgentObjects(
        @RequestBody
        @Valid
        SaveObjectReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveUnpaidGuideUrgentObjects(dto)).build();
    }

    @ApiOperation(value = "자료생성 데이터 생성", notes = "자료생성 버튼 클릭 시 생성된 데이터 있는지 체크 완료 후 SELECT INSERT 한다.")
    @PostMapping("/object")
    public SaveResponse createUnpaidGuideUrgentObjects(
        @RequestBody
        @Valid
        CreateObjectReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.createUnpaidGuideUrgentObjects(dto)).build();
    }

    @ApiOperation(value = "고객번호기준생성 데이터 생성", notes = "고객번호기준생성 버튼 클릭 시 생성된 데이터 있는지 체크 완료 후 SELECT INSERT 한다.")
    @PostMapping("/customer")
    public SaveResponse createUnpaidGuideUrgentCustomers(
        @RequestBody
        @Valid
        CreateCustomerReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.createUnpaidGuideUrgentCustomers(dto)).build();
    }
}
