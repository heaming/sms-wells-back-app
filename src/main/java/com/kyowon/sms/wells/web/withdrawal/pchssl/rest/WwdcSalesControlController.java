package com.kyowon.sms.wells.web.withdrawal.pchssl.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.RemoveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SaveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.service.WwdcSalesControlService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDC] 매출조정 관리 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(WdWithdrawalConst.REST_URL_PCHSSL + "/sales-control")
public class WwdcSalesControlController {
    private final WwdcSalesControlService service;

    @ApiOperation(value = "[WDC] 매출조정조회", notes = "매출조정 금액을 조회합니다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "exmpYn", value = "방학면제여부", paramType = "query"),
        @ApiImplicitParam(name = "perfStrtDtm", value = "실적To", paramType = "query"),
        @ApiImplicitParam(name = "perfFshDtm", value = "실적From", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "ctrTp", value = "조정유형", paramType = "query"),
        @ApiImplicitParam(name = "mtrDv", value = "자료구분", paramType = "query"),
        @ApiImplicitParam(name = "ctrDv", value = "조정구분", paramType = "query"),
        @ApiImplicitParam(name = "dsc", value = "할인구분", paramType = "query"),
        @ApiImplicitParam(name = "fstRgstUsrId", value = "등록자ID", paramType = "query"),
        @ApiImplicitParam(name = "slCtrPrcsStrtDt", value = "등록일자From", paramType = "query"),
        @ApiImplicitParam(name = "slCtrPrcsFshDt", value = "등록일자To", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchSalesControlRes> getSalesControl(
        @Valid
        SearchSalesControlReq dto, PageInfo pageInfo
    ) {
        return service.getSalesControl(dto, pageInfo);
    }

    @ApiOperation(value = "[WDC] 매출조정조회 엑셀다운로드", notes = "매출조정 조회결과를 엑셀 다운로드합니다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTp", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "exmpYn", value = "방학면제여부", paramType = "query"),
        @ApiImplicitParam(name = "perfStrtDtm", value = "실적To", paramType = "query"),
        @ApiImplicitParam(name = "perfFshDtm", value = "실적From", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "ctrTp", value = "조정유형", paramType = "query"),
        @ApiImplicitParam(name = "mtrDv", value = "자료구분", paramType = "query"),
        @ApiImplicitParam(name = "ctrDv", value = "조정구분", paramType = "query"),
        @ApiImplicitParam(name = "dsc", value = "할인구분", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "등록자ID", paramType = "query"),
        @ApiImplicitParam(name = "slCtrPrcsStrtDt", value = "등록일자From", paramType = "query"),
        @ApiImplicitParam(name = "slCtrPrcsFshDt", value = "등록일자To", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchSalesControlRes> getSalesControlForExcelDownload(SearchSalesControlReq dto) {
        return service.getSalesControlForExcelDownload(dto);
    }

    @ApiOperation(value = "매출조정관리 저장", notes = "매출조정 데이터를 수정한다.")
    @PostMapping("/save")
    public SaveResponse saveSalesControl(
        @RequestBody
        @Valid
        List<SaveSalesControlReq> dto
    ) throws Exception {
        //        log.info("=========cont===============");
        //        log.info("=========cont===============");

        return SaveResponse.builder()
            .processCount(service.saveSalesControl(dto))
            .build();
    }

    @ApiOperation(value = "매출조정관리 삭제", notes = "매출조정 데이터를 삭제한다.")
    @DeleteMapping("/delete")
    public SaveResponse removeSalesControl(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveSalesControlReq> req
    ) throws Exception {
        return SaveResponse
            .builder()
            .processCount(service.removeSalesControl(req)).build();

    }

    @ApiOperation(value = "매출조정관리 엑셀 업로드", notes = "매출조정관리 데이터를 엑셀 업로드한다.")
    @PostMapping("/excel-upload")
    public UploadRes saveSalesControlExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveSalesControlExcelUpload(file);
    }

}