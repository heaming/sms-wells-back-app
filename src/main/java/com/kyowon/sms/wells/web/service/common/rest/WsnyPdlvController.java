package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPdlvDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyPdlvService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "[WSNY] 출고지 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/placeOfDelivery")
public class WsnyPdlvController {
    private final WsnyPdlvService service;

    @ApiOperation(value = "출고지 조회", notes = "출고지 조회한다.")
    @GetMapping(value = "/pdlv-list")
    public List<WsnyPdlvDto.SearchRes> getPlaceOfDeliveryList(){
        return service.getPlaceOfDeliveryList();
    }
}
