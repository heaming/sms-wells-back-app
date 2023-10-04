package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;
import com.kyowon.sms.wells.web.service.visit.service.WsnbServiceProcDetailListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 서비스처리상세 내역 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-proc-detail-list")
public class WsnbServiceProcDetailListController {

    private final WsnbServiceProcDetailListService service;

    @ApiOperation(value = "서비스처리상세 내역 조회", notes = "조회조건에 일치하는 서비스처리상세 내역 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping
    public WsnbServiceProcDetailListDvo getServiceProcDetailList(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailList(dto);

    }

    @ApiOperation(value = "결제 내역 조회", notes = "조회조건에 일치하는 결제 내역 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping("/stlm-iz")
    public List<WsnbServiceProcDetailStlmIzDvo> getServiceProcDetailStlmIzs(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailStlmIzs(dto);

    }

    @ApiOperation(value = "청구 항목 조회", notes = "조회조건에 일치하는 청구 항목 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping("/bil-item")
    public List<WsnbServiceProcDetailBilItemDvo> getServiceProcDetailBilItems(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailBilItems(dto);

    }

    @ApiOperation(value = "투입 부품 조회", notes = "조회조건에 일치하는 투입 부품 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping("/pu-part")
    public List<WsnbServiceProcDetailPuPartDvo> getServiceProcDetailPuParts(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailPuParts(dto);

    }
}
