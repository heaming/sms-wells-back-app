package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicAksRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchConfirmAprPsicPrchssRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] Contract 공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaContractController {

    private final WctaContractService service;

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 구분 조회", notes = "기준일자로 유효시작, 종료일시 에 존재하며, 데이터삭제여부가 Y가 아닌확정승인기준 조회를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-requests-standards")
    public List<SearchRes> getApprovalAskDivides(
        @RequestParam
        String standardDt
    ) {
        return service.getApprovalAskDivides(standardDt);
    }

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping("/approval-requests-standards")
    public int removeApprovalAskDivides(
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return service.removeApprovalAskDivides(dtos);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 요청 내역", notes = "계약승인요청구분코드, 계약확정승인발송이력, ,계약확정승인내역 을 이용하여 확정승인 요청내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/approval-requests")
    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicAks(cntrNo);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 구매 내역", notes = "계약번호에 따른 교원키를 가지고 해당 교원키로 구매목록을 조회한다. 계약정보, 상품정보, 렌탈,  연체정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })

    @GetMapping("/approval-requests/order-histories")
    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicPrchss(cntrNo);
    }
}
