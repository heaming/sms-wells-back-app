package com.kyowon.sms.wells.web.withdrawal.pchssl.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SaveSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.service.WwdcSalesConfirmService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDC] 매출확정관리")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(WdWithdrawalConst.REST_URL_PCHSSL + "/sales-confirm")
public class WwdcSalesConfirmController {
    private final WwdcSalesConfirmService service;

    @ApiOperation(value = "[WDC] 매출확정관리 조회", notes = "매출확정 내역을 조회합니다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직레벨1", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직레벨2", paramType = "query"),
        @ApiImplicitParam(name = "dtFrom", value = "매출인식일From", paramType = "query"),
        @ApiImplicitParam(name = "dtTo", value = "매출인식일To", paramType = "query"),
        @ApiImplicitParam(name = "sellChnl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "slRcogDv", value = "판매인식", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchSalesConfirmRes> getSalesConfirm(
        @Valid
        SearchSalesConfirmReq dto, PageInfo pageInfo
    ) {
        return service.getSalesConfirm(dto, pageInfo);
    }

    @ApiOperation(value = "[WDC] 매출확정관리 엑셀다운로드 ", notes = "매출확정 조회 데이터를 엑셀 다운로드합니다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직레벨1", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직레벨2", paramType = "query"),
        @ApiImplicitParam(name = "dtFrom", value = "매출인식일From", paramType = "query"),
        @ApiImplicitParam(name = "dtTo", value = "매출인식일To", paramType = "query"),
        @ApiImplicitParam(name = "sellChnl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "slRcogDv", value = "판매인식", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchSalesConfirmRes> getSalesConfirmForExcelDownload(
        @Valid
        SearchSalesConfirmReq dto
    ) {
        return service.getSalesConfirmForExcelDownload(dto);
    }

    @ApiOperation(value = "[WDC] 매출확정관리 인식상태변경", notes = "매출확정 인식상태를 변경합니다.")
    @PostMapping("/change-state")
    public SaveResponse getSalesConfirmChangeState(
        @RequestBody
        @Valid
        List<SaveSalesConfirmReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.getSalesConfirmChangeState(req))
            .build();
    }
}
