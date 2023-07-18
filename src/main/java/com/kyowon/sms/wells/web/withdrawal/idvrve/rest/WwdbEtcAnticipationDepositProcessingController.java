package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbEtcAnticipationDpProcsDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbEtcAnticipationDepositProcessingService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "[WDB] 기타선수금 입금처리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/etc-atam-deposits")
public class WwdbEtcAnticipationDepositProcessingController {

    private final WwdbEtcAnticipationDepositProcessingService service;

    @ApiOperation(value = "기타선수금 입금처리를 한다.")
    @PostMapping
    public SaveResponse saveDepositProcs(
        @RequestBody
        @Valid
        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveDepositProcs(dto))
            .build();
    }
}
