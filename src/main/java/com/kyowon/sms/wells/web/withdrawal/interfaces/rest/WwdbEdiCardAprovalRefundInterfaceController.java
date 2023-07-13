package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbEdiCardAprovalRefundInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbEdiCardAprovalRefundInterfaceDto.SearchReq;
import static com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbEdiCardAprovalRefundInterfaceDto.SearchRes;

@InterfaceController
@Api(tags = "[WWDB] wells EDI카드 승인/환불 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard")
@RequiredArgsConstructor
@Validated
public class WwdbEdiCardAprovalRefundInterfaceController {

    private final WwdbEdiCardAprovalRefundInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1009] wells EDI카드 승인/환불 내역 조회 - W-WD-I-0008")
    @PostMapping("/aproval-refunds")
    public EaiWrapper getAprovalRefunds(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchRes> res = service.getEdiCardAprovalRefunds(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
