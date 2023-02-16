package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbGiroPlaceReceivedService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 지로 수신처 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/giro-placereceived")
public class WwdbGiroPlaceReceivedController {
    private final WwdbGiroPlaceReceivedService service;

    @ApiOperation(value = "지로 수신처 조회", notes = "일련번호에 해당 하는 지로 수신 상세정보를 조회한다.")
    @GetMapping()
    public FindRes getGiroPlaceReceived(FindReq dto) {
        return service.getGiroPlaceReceived(dto);
    }

    @ApiOperation(value = "지로 수신처 저장", notes = "지로 수신처 저장한다.")
    @PostMapping()
    public SaveResponse saveGiroPlaceReceived(
        @Valid
        @RequestBody
        SaveReq dto
    ) throws Exception {

        return SaveResponse.builder()
            .processCount(service.saveGiroPlaceReceived(dto))
            .build();
    }
}
