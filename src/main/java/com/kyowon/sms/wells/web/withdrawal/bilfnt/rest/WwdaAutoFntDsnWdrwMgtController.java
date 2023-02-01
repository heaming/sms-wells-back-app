package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaAutoFntDsnWdrwMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WithdrawalConst;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WithdrawalConst.REST_URL_V1)
@Api(tags = "[WDA] 자동이체 지정 출금 고객 관리")
public class WwdaAutoFntDsnWdrwMgtController {

    private final WwdaAutoFntDsnWdrwMgtService service;

    @ApiOperation(value = "자동이체 지정 출금 고객 조회")
    @GetMapping("/w-aftn-dsn-wdrw-cst-inqr")
    public PagingResult<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrPages(
        @ApiParam
        @Valid
        SearchAutoFntDsnWdrwCstReq req
    ) {
        return service.getAftnDsnWdrwCstInqrPages(req);
    }

    @ApiOperation(value = "자동이체 지정 출금 고객 저장")
    @PostMapping("/w-aftn-dsn-wdrw-cst-rgst")
    public SaveResponse saveAutoFntDsnWdrwCst(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAutoFntDsnWdrwCst(req))
            .build();
    }
}
