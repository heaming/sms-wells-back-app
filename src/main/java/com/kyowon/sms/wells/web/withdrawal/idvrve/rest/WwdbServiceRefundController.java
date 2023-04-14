package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbServiceRefundService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 서비스 환불 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/service-refund")
public class WwdbServiceRefundController {

    private final WwdbServiceRefundService service;

    @ApiOperation(value = "고객 정보 조회", notes = "서비스 환불 등록되는 고객 정보 조회")
    @GetMapping
    public SearchServiceRefundRes getServiceRefund(
        @ApiParam
        @Valid
        SearchServiceRefundReq req
    ) {
        return service.getServiceRefund(req);
    }

    @ApiOperation(value = "환불 은행사 조회", notes = "지급 구분에 따른 환불 카드사 조회")
    @GetMapping("/card")
    public List<SearchCardRes> getServiceRefundCard() {
        return service.getServiceRefundCard();
    }

    @ApiOperation(value = "환불 카드사 조회", notes = "지급 구분에 따른 환불 은행사 조회")
    @GetMapping("/bank")
    public List<SearchBankRes> getServiceRefundBank() {
        return service.getServiceRefundBank();
    }

    @ApiOperation(value = "환불 정보 수정", notes = "서비스 환불 등록되는 고객 정보 수정")
    @PostMapping
    public SaveResponse saveServiceRefund(
        @RequestBody
        @Valid
        SaveReq req
    ) throws Exception {
        log.info("환불 정보 수정");
        log.info(req.toString());
        return SaveResponse.builder()
            .processCount(service.saveServiceRefund(req))
            .build();
    }

}
