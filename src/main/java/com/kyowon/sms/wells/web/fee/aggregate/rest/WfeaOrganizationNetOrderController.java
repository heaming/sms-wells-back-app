package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaOrganizationNetOrderService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/organization-netorders")
@Api(tags = "[WFEA] 조직별 실적 집계")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaOrganizationNetOrderController {

    private final WfeaOrganizationNetOrderService service;

    @ApiOperation(value = "조직별 실적 집계", notes = "실적년월의 실적을 조직별로 집계한다.")
    @PostMapping("/aggregates")
    public String saveOrganizationAggregates(
        @RequestBody
        @Valid
        WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto
    ) throws Exception {
        return service.saveOrganizationAggregates(dto);
    }

    @ApiOperation(value = "조직별 실적 확정/확정취소", notes = "실적년월의 조직별 실적을 확정/확정취소한다.")
    @PutMapping()
    public SaveResponse editOrganizationAggregates(
        @RequestBody
        @Valid
        WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.editOrganizationAggregates(dto))
            .build();
    }

    @ApiOperation(value = "홈마스터 수수료 순주문 관리 목록 조회", notes = "조회조건에 따른 홈마스터 수수료 순주문 관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schFeePerf", value = "수수료실적유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSelType", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtStrt", value = "일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtEnd", value = "일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtStrt", value = "취소일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtEnd", value = "취소일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdStrt", value = "패키지코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdEnd", value = "패키지코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRsbDv", value = "직책구분", paramType = "query", required = false),
    })

    @GetMapping("hmsts")
    public List<WfeaOrganizationNetOrderDto.SearchHmstRes> getHomeMasters(
        @Valid
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.service.getHomeMasters(dto);
    }

    @ApiOperation(value = "홈마스터 수수료 순주문 관리(판매수수료) 목록 조회", notes = "조회조건에 따른 홈마스터 수수료 순주문 관리(판매수수료) 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping("hmst-fees")
    public List<WfeaOrganizationNetOrderDto.SearchHmstFeeRes> getHomeMasterFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.service.getHomeMasterFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리 목록 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schFeePerf", value = "수수료실적유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSelType", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtStrt", value = "일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtEnd", value = "일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtStrt", value = "취소일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtEnd", value = "취소일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdStrt", value = "패키지코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdEnd", value = "패키지코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRsbDv", value = "직책구분", paramType = "query", required = false),
    })

    @GetMapping("mngers")
    public List<WfeaOrganizationNetOrderDto.SearchMngerRes> getManagers(
        @Valid
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.service.getManagers(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리(판매수수료) 목록 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리(판매수수료) 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping("mnger-fees")
    public List<WfeaOrganizationNetOrderDto.SearchMngerSellFeeRes> getManagerFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.service.getManagerFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리 집계정보 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리 집계정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping("mnger-aggregation")
    public List<WfeaOrganizationNetOrderDto.SearchMngerAgrgRes> getManagerAggregation(
        @Valid
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.service.getManagerAggregation(dto);
    }

    @ApiOperation(value = "P조직 수수료 순주문 관리 목록 조회", notes = "조회조건에 따른 P조직 수수료 순주문 관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schFeePerf", value = "수수료실적유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSelType", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtStrt", value = "일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDtEnd", value = "일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtStrt", value = "취소일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schCancDtEnd", value = "취소일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdStrt", value = "패키지코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPkgCdEnd", value = "패키지코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRsbDv", value = "직책구분", paramType = "query", required = false),
    })

    @GetMapping("plars")
    public List<WfeaOrganizationNetOrderDto.SearchPlarRes> getPlanners(
        @Valid
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.service.getPlanners(dto);
    }

    @ApiOperation(value = "P조직 수수료 순주문 관리(판매수수료) 목록 조회", notes = "조회조건에 따른 P조직 수수료 순주문 관리(판매수수료) 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping("plar-fees")
    public List<WfeaOrganizationNetOrderDto.SearchPlarSellFeeRes> getPlannerFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.service.getPlannerSellFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리 집계정보 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리 집계정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schInqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schOrdr", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단총괄단ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점ID", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPrtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping("plar-aggregation")
    public List<WfeaOrganizationNetOrderDto.SearchPlarAgrgRes> getPlannerAggregation(
        @Valid
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.service.getPlannerAggregation(dto);
    }
}
