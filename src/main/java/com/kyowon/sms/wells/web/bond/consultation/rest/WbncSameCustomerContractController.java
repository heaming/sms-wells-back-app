package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindBreachOfPromiseRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindContractRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositDtlRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositInfoRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindSalesRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncSameCustomerContractService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(BnBondConst.REST_URL_V1 + "/same-customer-contracts")
@Api(tags = "[WBNC] 동일고객 계약내역 상세")
public class WbncSameCustomerContractController {
    private final WbncSameCustomerContractService service;

    @ApiOperation(value = "동일고객 계약내역 조회", notes = "동일고객 계약내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<FindContractRes> getContracts(@RequestParam
    String cstNo) {
        return service.getContracts(cstNo);
    }

    @ApiOperation(value = "동일고객 계약 입금정보 조회", notes = "동일고객 계약번호에 대한 입금정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/deposits")
    public List<FindDepositRes> getContractDeposits(@RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getContractDeposits(cntrNo, cntrSn);
    }

    @ApiOperation(value = "동일고객 계약 입금정보 상세조회", notes = "동일고객 계약번호에 대한 입금 상세정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bndBizDvCd", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/deposit")
    public FindDepositInfoRes getContractDeposit(@RequestParam
    String bndBizDvCd, @RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getContractDeposit(bndBizDvCd, cntrNo, cntrSn);
    }

    @ApiOperation(value = "동일고객 계약 위약정보 조회", notes = "동일고객 계약번호에 대한 위약정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bndBizDvCd", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/breach-of-promise")
    public FindBreachOfPromiseRes getBreachOfPromise(@RequestParam
    String bndBizDvCd, @RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getBreachOfPromise(bndBizDvCd, cntrNo, cntrSn);
    }

    @ApiOperation(value = "동일고객 계약 매출정보 조회", notes = "동일고객 계약번호에 대한 매출정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bndBizDvCd", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/sales")
    public FindSalesRes getContractSales(@RequestParam
    String bndBizDvCd, @RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getContractSales(bndBizDvCd, cntrNo, cntrSn);
    }

    @ApiOperation(value = "동일고객 계약 입금 상세조회", notes = "동일고객 계약번호에 대한 입금 상세정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/deposit-details")
    public List<FindDepositDtlRes> getDeposits(@RequestParam
    String cntrNo, @RequestParam
    int cntrSn) {
        return service.getDeposits(cntrNo, cntrSn);
    }
}
