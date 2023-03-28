package com.kyowon.sms.wells.web.fee.confirm.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.service.WfeeIndividualFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/individual-fees")
@Api(tags = "[WFEE] 수수료 개인 상세")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeeIndividualFeeController {
    private final WfeeIndividualFeeService service;

    @ApiOperation(value = "수수료 개인별 실적 상세 조회(공통)", notes = "조회조건 실적년월에 해당하는 사번의 개인별 상세 실적 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/details")
    public List<SearchRes> getIndividualPerformanceDetails(
        SearchReq dto
    ) {
        return this.service.getIndividualPerformanceDetails(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-informations")
    public FindHmstRes getHmst(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmst(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-etcs")
    public List<SearchHmstEtcRes> getHmstEtcs(
        SearchHmstReq dto
    ) {
        return this.service.getHmstEtcs(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-fees")
    public List<SearchHmstFeeRes> getHmstFees(
        SearchHmstReq dto
    ) {
        return this.service.getHmstFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-deductions")
    public FindHmstDeductionRes getHmstDeductions(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstDeductions(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-pnpyam")
    public List<SearchHmstPnpyamRes> getHmstPnpyams(
        SearchHmstReq dto
    ) {
        return this.service.getHmstPnpyams(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-informations")
    public FindPlarRes getPlar(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlar(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-etcs")
    public List<SearchPlarEtcRes> getPlarEtcs(
        SearchPlarReq dto
    ) {
        return this.service.getPlarEtcs(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-fees")
    public List<SearchPlarFeeRes> getPlarFees(
        SearchPlarReq dto
    ) {
        return this.service.getPlarFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-deductions")
    public FindPlarDeductionRes getPlarDeduction(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarDeduction(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-pnpyam")
    public List<SearchPlarPnpyamRes> getPlarPnpyams(
        SearchPlarReq dto
    ) {
        return this.service.getPlarPnpyams(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-informations")
    public FindMngerRes getMnger(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMnger(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-etcs")
    public List<SearchMngerEtcRes> getMngerEtcs(
        SearchMngerReq dto
    ) {
        return this.service.getMngerEtcs(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-fees")
    public List<SearchMngerFeeRes> getMngerFees(
        SearchMngerReq dto
    ) {
        return this.service.getMngerFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-deductions")
    public FindMngerDeductionRes getMngerDeduction(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerDeduction(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-pnpyam")
    public List<SearchMngerPnpyamRes> getMngerPnpyams(
        SearchMngerReq dto
    ) {
        return this.service.getMngerPnpyams(dto);
    }

    @ApiOperation(value = "수수료 조회(추진단)", notes = "조회조건에 해당하는 추진단의 개인별 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTp", value = "조직유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTp", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "feeDsbYn", value = "수수료지급여부", paramType = "query", required = false),
    })
    @GetMapping("/manager-planers")
    public List<SearchFeeRes> getFees(
        SearchFeeReq dto
    ) {
        return this.service.getFees(dto);
    }

    @ApiOperation(value = "수수료 조회(홈마스터)", notes = "조회조건에 해당하는 홈마스터의 개인별 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTp", value = "조직유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTp", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "feeDsbYn", value = "수수료지급여부", paramType = "query", required = false),
    })
    @GetMapping("/home-masters")
    public List<SearchFeeHmstRes> getFeeHmsts(
        SearchFeeHmstReq dto
    ) {
        return this.service.getFeeHmsts(dto);
    }

}
