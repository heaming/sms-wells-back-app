package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaAftnCheckListService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WithdrawalConst.REST_URL_V1)
@Api(tags = "[WDA] 자동이체 미수신 체크 목록 관리")
public class WwdaAftnCheckListController {

    private final WwdaAftnCheckListService service;

    @ApiOperation(value = "자동이체 미수신 체크 목록")
    @GetMapping("/w-aftn-nrcv-check-list") // url은 추후에 수정
    public PagingResult<SearchAftnBilNrcvListRes> getAftnBilNrcvListPages(
        @ApiParam
        SearchAftnBilNrcvListReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnBilNrcvListPages(req, pageInfo);
    }
}
