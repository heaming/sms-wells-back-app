package com.kyowon.sms.wells.web.fee.confirm.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;
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
    public List<WfeeIndividualFeeDto.SearchRes> getIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    ) {
        return this.service.getIndividualPerformanceDetails(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-informations")
    public WfeeIndividualFeeDto.FindHmstInformationRes getHmstInformation(
        @Valid
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return service.getHmstInformation(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-etcs")
    public List<WfeeIndividualFeeDto.SearchHmstEtcRes> getHmstEtcInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.service.getHmstEtcInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-fees")
    public List<WfeeIndividualFeeDto.SearchHmstFeeRes> getHmstFeeInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.service.getHmstFeeInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-deductions")
    public WfeeIndividualFeeDto.FindHmstDeductionRes getHmstDeductions(
        @Valid
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return service.getHmstDeductions(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-pnpyam")
    public List<WfeeIndividualFeeDto.SearchHmstPnpyamRes> getHmstPnpyamInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.service.getHmstPnpyamInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-informations")
    public WfeeIndividualFeeDto.FindPlarInformationRes getPlarInformation(
        @Valid
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return service.getPlarInformation(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-etcs")
    public List<WfeeIndividualFeeDto.SearchPlarEtcRes> getPlarEtcInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.service.getPlarEtcInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-fees")
    public List<WfeeIndividualFeeDto.SearchPlarFeeRes> getPlarFeeInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.service.getPlarFeeInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-deductions")
    public WfeeIndividualFeeDto.FindPlarDeductionRes getPlarDeduction(
        @Valid
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return service.getPlarDeduction(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-pnpyam")
    public List<WfeeIndividualFeeDto.SearchPlarPnpyamRes> getPlarPnpyamInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.service.getPlarPnpyamInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-informations")
    public WfeeIndividualFeeDto.FindMngerInformationRes getMngerInformation(
        @Valid
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return service.getMngerInformation(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-etcs")
    public List<WfeeIndividualFeeDto.SearchMngerEtcRes> getMngerEtcInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.service.getMngerEtcInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-fees")
    public List<WfeeIndividualFeeDto.SearchMngerFeeRes> getMngerFeeInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.service.getMngerFeeInformations(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-deductions")
    public WfeeIndividualFeeDto.FindMngerDeductionRes getMngerDeduction(
        @Valid
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return service.getMngerDeduction(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-pnpyam")
    public List<WfeeIndividualFeeDto.SearchMngerPnpyamRes> getMngerPnpyamInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.service.getMngerPnpyamInformations(dto);
    }
}
