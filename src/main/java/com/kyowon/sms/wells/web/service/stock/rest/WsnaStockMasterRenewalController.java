package com.kyowon.sms.wells.web.service.stock.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockMasterRenewalDto.EditReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStockMasterRenewalService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-master-renewal")
@Api(tags = "[WSNA] 재고마스터 갱신 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaStockMasterRenewalController {

    private final WsnaStockMasterRenewalService service;

    @ApiOperation(value = "재고마스터 갱신", notes = "갱신 기준에 따라 재고내역을 업데이트 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "body", example = "202303", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "body", example = "1", required = true),
        @ApiImplicitParam(name = "rnwOjAcd", value = "갱신대상", paramType = "body", example = "1", required = true),
    })
    @PutMapping
    public SaveResponse editStockMaster(
        @Valid
        @RequestBody
        EditReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.editStockMaster(dto)).build();
    }

}
