package com.kyowon.sms.wells.web.contract.risk.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.service.WctaContractService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 확정승인기준 리스트(팝업)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/approval-ask-divides")
public class WctaContractController {

    private final WctaContractService service;

    @ApiOperation(value = "승인 요청 구분 조회", notes = "기준일자로 유효시작, 종료일시 에 존재하며, 데이터삭제여부가 Y가 아닌확정승인기준 조회를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getApprovalAskDivides(
        @RequestParam
        String standardDt
    ) {
        return service.getApprovalAskDivides(standardDt);
    }

    @ApiOperation(value = "승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping
    public int removeApprovalAskDivides(List<RemoveReq> dtos) {
        return service.removeApprovalAskDivides(dtos);
    }

}
