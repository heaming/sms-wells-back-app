package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccEtcPerformanceInterfaceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
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
@Api(tags = "[WDCC] wells일시불외(할부)-실적정보조회 I/F")
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performances/etc")
@RequiredArgsConstructor
@Validated
public class WdccEtcPerformanceInterfaceController {
    private final WdccEtcPerformanceInterfaceService service;

    @ApiOperation(value = "[EAI_WCLI0004] wells일시불외-실적정보조회", notes = "고객응대를 위한 고객센터 wells일시불외-실적정보조회 Interface에 대한 결과를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrtNm", value = "계약자명", paramType = "query", required = false),
        @ApiImplicitParam(name = "slClYm1", value = " 매출마감년월From", paramType = "query", required = false),
        @ApiImplicitParam(name = "slClYm2", value = " 매출마감년월To", paramType = "query", required = false),
    })
    @PostMapping
    public EaiWrapper getOtherLumpSumPerformance(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<FindRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<FindRes> res = service.getOtherLumpSumPerformance(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
