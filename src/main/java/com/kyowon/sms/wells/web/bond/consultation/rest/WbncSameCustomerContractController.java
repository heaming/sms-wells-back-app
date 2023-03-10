package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindBreachOfPromiseRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindContractRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositDtlRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindSalesRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncSameCustomerContractService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(BnBondConst.REST_URL_V1 + "/same-customer-contracts")
@Api(tags = "[WBNC] 동일고객 계약내역 상세")
public class WbncSameCustomerContractController {
    private final WbncSameCustomerContractService service;

    @ApiOperation(value = "동일고객 계약내역 조회", notes = "동일고객 계약내역을 조회한다.")
    @GetMapping("/{cstNo}-{safeKey}-{clctamPrtnrNo}")
    public List<FindContractRes> getContracts(@PathVariable
    String cstNo, @PathVariable
    String safeKey, @PathVariable
    String clctamPrtnrNo) {
        return service.getContracts(cstNo, safeKey, clctamPrtnrNo);
    }

    @ApiOperation(value = "동일고객 계약 입금정보 조회", notes = "동일고객 계약번호에 대한 입금정보를 조회한다.")
    @GetMapping("/{bndCntrRefId}/deposits")
    public List<FindDepositRes> getContractDeposits(@PathVariable
    String bndCntrRefId) {
        return service.getContractDeposits(bndCntrRefId);
    }

    @ApiOperation(value = "동일고객 계약 입금정보 상세조회", notes = "동일고객 계약번호에 대한 입금 상세정보를 조회한다.")
    @GetMapping("/{bndCntrRefId}/deposit")
    public FindDepositDtlRes getContractDeposit(@PathVariable
    String bndCntrRefId) {
        return service.getContractDeposit(bndCntrRefId);
    }

    @ApiOperation(value = "동일고객 계약 위약정보 조회", notes = "동일고객 계약번호에 대한 위약정보를 조회한다.")
    @GetMapping("/{bndCntrRefId}/breach-of-promise")
    public FindBreachOfPromiseRes getBreachOfPromise(@PathVariable
    String bndCntrRefId) {
        return service.getBreachOfPromise(bndCntrRefId);
    }

    @ApiOperation(value = "동일고객 계약 매출정보 조회", notes = "동일고객 계약번호에 대한 매출정보를 조회한다.")
    @GetMapping("/{bndCntrRefId}/sales")
    public FindSalesRes getContractSales(@PathVariable
    String bndCntrRefId) {
        return service.getContractSales(bndCntrRefId);
    }
}
