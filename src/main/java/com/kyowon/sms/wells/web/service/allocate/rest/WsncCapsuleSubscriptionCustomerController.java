package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCapsuleSubscriptionCustomerDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncCapsuleSubscriptionCustomerService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@InterfaceController
@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/capsule-subscription-customers")
@Api(tags = "[WSNC] W-SV-S-0049 홈카페 캡슐 정기구매 고객 스케쥴 인서트/업데이트 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncCapsuleSubscriptionCustomerController {

    private final WsncCapsuleSubscriptionCustomerService service;

    /*only test*/
    /*only test*/
    /*only test*/
    /*only test*/
    /*only test*/
    /*only test*/

    @ApiOperation(value = "홈카페 캡슐 정기구매 고객의 스케쥴 정보를 insert/update한다.")
    @GetMapping
    public SaveRes saveCapsuleSubscriptionCustomer(
        SaveReq dto
    ) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("PARAM1", DateUtil.getNowDayString());
        service.saveCapsuleSubscriptionCustomer(map);
        return new SaveRes("S001", "");
    }

}
