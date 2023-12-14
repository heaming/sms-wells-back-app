package com.kyowon.sms.wells.web.fee.confirm.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeHomeMasterDto;
import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeePlannerDto.*;
import com.kyowon.sms.wells.web.fee.confirm.service.WfeeIndividualFeeHomeMasterService;
import com.kyowon.sms.wells.web.fee.confirm.service.WfeeIndividualFeePlannerService;
import com.kyowon.sms.wells.web.fee.confirm.service.WfeeIndividualFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/individual-fees")
@Api(tags = "[WFEE] 수수료 개인 상세")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeeIndividualFeeController {
    private final WfeeIndividualFeeService service;
    private final WfeeIndividualFeePlannerService plannerService;
    private final WfeeIndividualFeeHomeMasterService hmstService;

    @ApiOperation(value = "수수료 개인별 실적 상세 파트너 직책 조회", notes = "조회조건 실적년월에 해당하는 사번의 직책 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/prtnr-rsb")
    public SearchPrtnrRsbRes getIndividualPerformancePrtnrRsb(
        SearchReq dto
    ) {
        return service.getIndividualPerformancePrtnrRsb(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 개인별 상세 실적 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-details")
    public List<SearchPlarRes> getIndividualPerformancePlarDetails(
        SearchReq dto
    ) {
        return plannerService.getIndividualPerformancePlarDetails(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 개인별 상세 실적 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-details")
    public List<SearchMngerRes> getIndividualPerformanceMngerDetails(
        SearchReq dto
    ) {
        return service.getIndividualPerformanceMngerDetails(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 개인별 상세 실적 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-details")
    public List<WfeeIndividualFeeHomeMasterDto.SearchHmstRes> getIndividualPerformanceHmstDetails(
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getIndividualPerformanceHmstDetails(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/hmst-informations")
    public WfeeIndividualFeeHomeMasterDto.FindHmstRes getHmst(
        @Valid
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getHmst(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-etcs")
    public List<WfeeIndividualFeeHomeMasterDto.SearchHmstEtcRes> getHmstEtcs(
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstEtcs(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-fees")
    public List<WfeeIndividualFeeHomeMasterDto.SearchHmstFeeRes> getHmstFees(
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-deductions")
    public List<WfeeIndividualFeeHomeMasterDto.FindHmstDeductionRes> getHmstDeductions(
        @Valid
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstDeductions(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/hmst-pnpyam")
    public List<WfeeIndividualFeeHomeMasterDto.SearchHmstPnpyamRes> getHmstPnpyams(
        WfeeIndividualFeeHomeMasterDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstPnpyams(dto);
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
        return plannerService.getPlar(dto);
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
        return plannerService.getPlarEtcs(dto);
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
        return plannerService.getPlarFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/plar-deductions")
    public List<FindPlarDeductionRes> getPlarDeduction(
        @Valid
        SearchPlarReq dto
    ) {
        return plannerService.getPlarDeduction(dto);
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
        return this.plannerService.getPlarPnpyams(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기본정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기본정보 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-basic")
    public FindMngerBasicRes getMngerBasic(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerBasic(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 기타내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 기타내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-selletcs")
    public List<SearchMngerSellEtcsRes> getMngerSellEtcs(
        SearchMngerReq dto
    ) {
        return this.service.getMngerSellEtcs(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 BS내역 목록 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 실적 수수료 BS내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-before-services")
    public List<SearchMngerBeforeServiceRes> getMngerBeforeServices(
        SearchMngerReq dto
    ) {
        return this.service.getMngerBeforeServices(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 수수료내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-fees")
    public List<HashMap<String, Object>> getMngerFees(
        SearchMngerReq dto
    ) {
        return this.service.getMngerFees(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 공제내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 공제 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-deductions")
    public List<SearchMngerDeductionRes> getMngerDeduction(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerDeduction(dto);
    }

    @ApiOperation(value = "수수료 개인별 실적 상세 가지급금 세부내역 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 상세 실적 가지급금 세부 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419", required = true),
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
    @GetMapping("/feeLists")
    public List<SearchFeeRes> getFees(
        SearchFeeReq dto
    ) {
        return this.service.getFees(dto);
    }

    //    @ApiOperation(value = "수수료 조회 사용자 고용정보 조회", notes = "로그인한 사용자의 고용정보를 조회한다.")
    //    @ApiImplicitParams(value = {
    //        @ApiImplicitParam(name = "userEmpID", value = "사용자ID", paramType = "query", required = false),
    //        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
    //    })
    //    @GetMapping("/userInfo")
    //    public SearchUserInfoRes getUserInfo(
    //        SearchFeeReq dto
    //    ) {
    //        return this.service.getUserInfo(dto);
    //    }

}
