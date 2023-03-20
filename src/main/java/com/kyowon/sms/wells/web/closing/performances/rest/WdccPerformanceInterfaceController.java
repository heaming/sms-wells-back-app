package com.kyowon.sms.wells.web.closing.performances.rest;

import com.kyowon.sms.common.web.closing.zcommon.constants.DcClosingConst;
import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto;
import com.kyowon.sms.wells.web.closing.performances.service.WdccPerformanceInterfaceService;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@InterfaceController
@Api(tags = "[WDCC] wells일시불(할부)-실적정보조회 I/F")
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performances")
@RequiredArgsConstructor
@Validated
public class WdccPerformanceInterfaceController {
    private final WdccPerformanceInterfaceService service;

    @ApiOperation(value = "[EAI_WCLI0003] wells일시불(할부)-실적정보조회", notes = "고객응대를 위한 고객센터 wells일시불(할부)-실적정보조회 Interface에 대한 결과를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "CNTR_NO", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "CNTR_SN", value = "계약일련번호", paramType = "query", required = false),
    })
    @PostMapping
    public EaiWrapper getLumpSumPerformances(
        @Valid
        @RequestBody
        EaiWrapper<WdccPerformanceInterfaceDto.FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WdccPerformanceInterfaceDto.FindRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<WdccPerformanceInterfaceDto.FindRes> res = service.getLumpSumPerformance(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
