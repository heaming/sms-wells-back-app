package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbRentalMembershipPrepaymentInterfaceService;
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

import static com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoRes;

@InterfaceController
@Api(tags = "[WWDB] wells 렌탈/멤버십 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/rental-membership-prepayment")
@RequiredArgsConstructor
@Validated
public class WwdbRentalMembershipPrepaymentInterfaceController {

    private final WwdbRentalMembershipPrepaymentInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1006] wells 렌탈/멤버십 선납 정보 조회 - W-WD-I-0003")
    @PostMapping("/infos")
    public EaiWrapper getRentalMembershipPrepaymentInfos(
        @Valid
        @RequestBody
        EaiWrapper<SearchInfoReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchInfoRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchInfoRes> res = service.getRentalMembershipPrepaymentInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WDEI1007] wells 렌탈/멤버십 선납 예정정보 조회 - W-WD-I-0004")
    @PostMapping("/expectedinfos")
    public EaiWrapper getRentalMembershipPrepaymentExpectedInfos(
        @Valid
        @RequestBody
        EaiWrapper<SearchExpectedInfoReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchExpectedInfoRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchExpectedInfoRes> res = service.getRentalMembershipPrepaymentExpectedInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
