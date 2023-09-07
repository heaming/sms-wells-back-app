package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccPressurePumpService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDCC] 가압펌프 설치/철거 관리")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/pressure-pump")
@RequiredArgsConstructor
@Validated
public class WdccPressurePumpController {
    private final WdccPressurePumpService service;

    @ApiOperation(value = "가압펌프 설치/철거 관리 - 가압펌프 확정관리", notes = "가압펌프 설치/철거 관리 - 가압펌프 확정관리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fstVstFshDtFrom", value = "작업일자 from", paramType = "query"),
        @ApiImplicitParam(name = "fstVstFshDtTo", value = "작업일자 to", paramType = "query"),
        @ApiImplicitParam(name = "cnfmYn", value = "확정여부", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCd", value = "제품코드", paramType = "query"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형", paramType = "query"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "사용/회수", paramType = "query"),
    })
    @GetMapping("/confirm-management")
    public List<SearchConfirmManagementRes> getConfirmManagement(
        @Valid
        SearchConfirmManagementReq dto
    ) {
        return service.getConfirmManagement(dto);
    }

    @ApiOperation(value = "가압펌프 설치/철거 관리 - 가압펌프 매출기준 자료", notes = "가압펌프 설치/철거 관리 - 가압펌프 매출기준 자료")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slYm", value = "매출년월", paramType = "query"),
    })
    @GetMapping("/sales-base")
    public List<SearchSalesBaseRes> getSalesBase(
        @Valid
        SearchSalesBaseReq dto
    ) {
        return service.getSalesBase(dto);
    }

    @ApiOperation(value = "가압펌프 설치/철거 관리 - 가압펌프 확정관리 선택 저장", notes = "가압펌프 설치/철거 관리 - 가압펌프 확정관리 선택 저장한다.")
    @PostMapping
    public SaveResponse saveConfirmManagement(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveConfirmManagement(dtos))
            .build();
    }

    @ApiOperation(value = "가압펌프 설치/철거 관리 - 가압펌프 확정관리 대상 삭제", notes = "가압펌프 설치/철거 관리 - 가압펌프 확정관리 대상을 삭제한다.")
    @DeleteMapping
    public SaveResponse removeConfirmManagement(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeConfirmManagement(dtos)).build();

    }
}
