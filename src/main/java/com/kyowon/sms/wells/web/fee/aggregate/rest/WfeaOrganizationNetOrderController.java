package com.kyowon.sms.wells.web.fee.aggregate.rest;

import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaOrganizationNetOrderService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    public SaveResponse saveOrganizationAggregates(
        @RequestBody
        @Valid
        WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveOrganizationAggregates(dto))
            .build();
    }

    @ApiOperation(value = "BS 실적 집계", notes = "실적년월의 BS실적을 집계한다.")
    @PostMapping("/bs-aggregates")
    public SaveResponse saveBsPerformances(
        @RequestBody
        @Valid
        WfeaOrganizationNetOrderDto.SaveBsReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveBsPerformances(dto))
            .build();
    }

    @ApiOperation(value = "조직별 실적 확정", notes = "실적년월의 조직별 실적을 확정한다.")
    @PutMapping
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
        @ApiImplicitParam(name = "schTcnt", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtStrt", value = "접수일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtEnd", value = "접수일자종료", paramType = "query", required = false),
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
        @ApiImplicitParam(name = "schTcnt", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtStrt", value = "접수일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtEnd", value = "접수일자종료", paramType = "query", required = false),
    })
    @GetMapping("hmst-sell-fees")
    public List<WfeaOrganizationNetOrderDto.SearchHmstSellFeeRes> getHomeMasterSellFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.service.getHomeMasterSellFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리 목록 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBizDv", value = "업무구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtStrt", value = "접수일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtEnd", value = "접수일자종료", paramType = "query", required = false),
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
        @ApiImplicitParam(name = "schBizDv", value = "업무구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtStrt", value = "접수일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRcpDtEnd", value = "접수일자종료", paramType = "query", required = false),
    })
    @GetMapping("mnger-sell-fees")
    public List<WfeaOrganizationNetOrderDto.SearchMngerSellFeeRes> getManagerSellFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.service.getManagerSellFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 순주문 관리(BS) 목록 조회", notes = "조회조건에 따른 M조직 수수료 순주문 관리(BS) 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBizDv", value = "업무구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPerfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schVstDtStrt", value = "방문일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schVstDtEnd", value = "방문일자종료", paramType = "query", required = false),
    })
    @GetMapping("mnger-before-services")
    public List<WfeaOrganizationNetOrderDto.SearchMngerBsRes> getManagerBeforeServices(
        @Valid
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.service.getManagerBeforeServices(dto);
    }

    @ApiOperation(value = "P조직 수수료 순주문 관리 목록 조회", notes = "조회조건에 따른 P조직 수수료 순주문 관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
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
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자종료", paramType = "query", required = false),
    })
    @GetMapping("plar-sell-fees")
    public List<WfeaOrganizationNetOrderDto.SearchPlarSellFeeRes> getPlannerFees(
        @Valid
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.service.getPlannerSellFees(dto);
    }
}
