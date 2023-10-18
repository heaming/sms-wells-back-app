package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeHomeMasterMgtDto;
import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.control.service.WfedIndividualFeeHomeMasterMgtService;
import com.kyowon.sms.wells.web.fee.control.service.WfedIndividualFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/individual-fee")
@Api(tags = "[WFED] 개인별 수수료 관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedIndividualFeeMgtController {
    private final WfedIndividualFeeMgtService service;
    private final WfedIndividualFeeHomeMasterMgtService hmstService;

    @ApiOperation(value = "개인별 수수료 관리 사업자정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 사업자정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/home-master/entrepreneurs")
    public WfedIndividualFeeHomeMasterMgtDto.FindHmstEntrpRes getHmstEntrp(
        @Valid
        WfedIndividualFeeHomeMasterMgtDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstEntrp(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/home-master/fees")
    public List<WfedIndividualFeeHomeMasterMgtDto.SearchHmstFeeRes> getHmstFees(
        @Valid
        WfedIndividualFeeHomeMasterMgtDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstFees(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 공제내역 정보 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 공제내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/home-master/deduction")
    public WfedIndividualFeeHomeMasterMgtDto.FindHmstDeductionRes getHmstDeduction(
        @Valid
        WfedIndividualFeeHomeMasterMgtDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstDeduction(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 조정내역 목록 조회(홈마스터)", notes = "조회조건 실적년월에 해당하는 사번의 홈마스터 개인별 수수료 관리 조정내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/home-master/control")
    public List<WfedIndividualFeeHomeMasterMgtDto.SearchHmstControlRes> getHmstControls(
        WfedIndividualFeeHomeMasterMgtDto.SearchHmstReq dto
    ) {
        return hmstService.getHmstControls(dto);
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

    @ApiOperation(value = "개인별 수수료 관리 기본 정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 기본 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-basic")
    public List<SearchPlarEtcRes> getPlarEtcs(
        @Valid
        SearchPlarReq dto
    ) {
        return service.getPlarEtcs(dto);
    }

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(P조직)", notes = "조회조건 실적년월에 해당하는 사번의 P조직 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/plar-fee")
    public List<SearchPlarFeeRes> getPlarFee(
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

    @ApiOperation(value = "개인별 수수료 관리 기본내역 목록 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 기본내역 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/mnger-base-info")
    public List<SearchMngerBaseInfoRes> getMngerBaseInfo(
        SearchMngerReq dto
    ) {
        return this.service.getMngerBaseInfo(dto);
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

    @ApiOperation(value = "개인별 수수료 관리 수수료 내역 정보 조회(M조직)", notes = "조회조건 실적년월에 해당하는 사번의 M조직 개인별 수수료 관리 수수료 내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/mnger-fee")
    public List<SearchMngerFeeRes> getMngerFees(
        @Valid
        SearchMngerReq dto
    ) {
        return service.getMngerFees(dto);
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
