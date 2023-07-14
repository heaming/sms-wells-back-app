package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchRecentContractReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchRecentContractRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerSellerService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@InterfaceController
@Api(tags = "[WOGC] 판매자 정보 관리 EAI INTERFACE API")
@Validated
@RequiredArgsConstructor
@RequestMapping(OgConst.REST_PREFIX_INTERFACE_SMS_WELLS + "/partner-seller")
public class WogcPartnerSellerInterfaceController {

    private final WogcPartnerSellerService wogcPartnerSellerService;

    @ApiOperation(value = "[EAI]웰스_WM 정보 조회", notes = "조회 조건에 일치하는 웰스 WM 정보 조회한다.")
    @PostMapping("/wm")
    public EaiWrapper getWM(
        @RequestBody
        EaiWrapper<SearchWMReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchWMRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchWMRes> res = wogcPartnerSellerService.getWM(reqWrapper.getBody());
        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI]최근 계약 판매자 조회", notes = "조회 조건에 일치하는 최근 계약 판매자를 조회한다.")
    @PostMapping("/recent-contract")
    public EaiWrapper getRecentContracts(
        @RequestBody
        EaiWrapper<SearchRecentContractReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRecentContractRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchRecentContractRes> res = wogcPartnerSellerService.getSearchRecentContracts(reqWrapper.getBody());
        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
