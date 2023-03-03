package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.control.service.WfedIndividualFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/individual-fee-mgts")
@Api(tags = "[WFED] 개인별 수수료 관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedIndividualFeeMgtController {
    private final WfedIndividualFeeMgtService service;

    @ApiOperation(value = "개인별 수수료 관리 사업자정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 사업자정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-entrepreneur")
    public FindHmstEntrpRes getHmstEntrp(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstEntrp(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 기본정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 기본정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-basic")
    public FindHmstBasicRes getHmstBasic(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstBasic(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 총계정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 총계정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-total-sum")
    public FindHmstTotalSumRes getHmstTotalSum(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstTotalSum(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-fee")
    public FindHmstFeeRes getHmstFee(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstFee(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 공제내역 정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 공제내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-deduction")
    public FindHmstDeductionRes getHmstDeduction(
        @Valid
        SearchHmstReq dto
    ) {
        return service.getHmstDeduction(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 조정내역 목록 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 조정내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-control")
    public List<SearchHmstControlRes> getHmstControls(
        SearchHmstReq dto
    ) {
        return this.service.getHmstControls(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 사업자정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 사업자정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-entrepreneur")
    public FindPlarEntrpRes getPlarEntrp(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarEntrp(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 기본정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 기본정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-basic")
    public FindPlarBasicRes getPlarBasic(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarBasic(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 총계정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 총계정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-total-sum")
    public FindPlarTotalSumRes getPlarTotalSum(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarTotalSum(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-fee")
    public FindPlarFeeRes getPlarFee(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarFee(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 공제내역 정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 공제내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-deduction")
    public FindPlarDeductionRes getPlarDeduction(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarDeduction(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 조정내역 목록 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 조정내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-control")
    public List<SearchPlarControlRes> getPlarControls(
        SearchPlarReq dto
    ) {
        return this.service.getPlarControls(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 사업자정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 사업자정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-entrepreneur")
    public FindMngerEntrpRes getMngerEntrp(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerEntrp(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 기본정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 기본정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-basic")
    public FindMngerBasicRes getMngerBasic(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerBasic(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 BS내역 목록 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 BS내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-before-services")
    public List<SearchMngerBeforeServiceRes> getMngerBeforeServices(
        SearchMngerReq dto
    ) {
        return this.service.getMngerBeforeServices(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 총계정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 총계정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-total-sum")
    public FindMngerTotalSumRes getMngerTotalSum(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerTotalSum(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-fee")
    public FindMngerFeeRes getMngerFee(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerFee(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 공제내역 정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 공제내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-deduction")
    public FindMngerDeductionRes getMngerDeduction(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerDeduction(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 조정내역 목록 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 조정내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-control")
    public List<SearchMngerControlRes> getMngerControls(
        SearchMngerReq dto
    ) {
        return this.service.getMngerControls(dto);
    }
}
