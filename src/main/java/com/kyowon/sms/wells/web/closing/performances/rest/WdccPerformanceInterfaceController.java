package com.kyowon.sms.wells.web.closing.performances.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.performances.service.WdccPerformanceInterfaceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WDCC] wells일시불(할부)-실적정보조회 I/F : W-CL-I-0003")
@RequestMapping(DcClosingConst.INTERFACE_URL_V1 + "/performances/singlePayment")
@RequiredArgsConstructor
@Validated
public class WdccPerformanceInterfaceController {
    private final WdccPerformanceInterfaceService service;

    @ApiOperation(value = "[EAI_WCLI0003] wells일시불(할부)-실적정보조회", notes = "고객응대를 위한 고객센터 wells일시불(할부)-실적정보조회 Interface에 대한 결과를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "CNTR_NO", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "CNTR_SN", value = "계약일련번호", paramType = "query"),
    })
    @PostMapping
    public EaiWrapper getLumpSumPerformances(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        FindRes res = service.getLumpSumPerformance(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
