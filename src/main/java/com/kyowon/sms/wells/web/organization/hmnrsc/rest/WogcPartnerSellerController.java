package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerSellerService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "[WOGC] 판매자 정보 관리 REST API")
@Validated
@RequiredArgsConstructor
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/partner-seller")
public class WogcPartnerSellerController {

    private final WogcPartnerSellerService service;

    @ApiOperation(value = "Wells 주문의 판매자 정보 확인 조회", notes = "조회 조건에 일치하는 Wells 주문의 판매자 정보 확인 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrKnm", value = "성명", paramType = "query", required = false),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query", required = false),
        @ApiImplicitParam(name = "sexDvCd", value = "성별", paramType = "query", required = false),
        @ApiImplicitParam(name = "ymd", value = "접수일자", paramType = "query", required = false),

    })
    @GetMapping("/information-confirm")
    public SearchInformationConfirmRes getInformationConfirm(
        SearchInformationConfirmReq dto) {
        return service.getInformationConfirm(dto);
    }

}
