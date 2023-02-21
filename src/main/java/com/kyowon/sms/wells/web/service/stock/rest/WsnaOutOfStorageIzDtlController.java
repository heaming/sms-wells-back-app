package com.kyowon.sms.wells.web.service.stock.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageIzDtlService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-iz-dtls")
@Api(tags = "[WSNA] 출고내역상세 관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageIzDtlController {

    private final WsnaOutOfStorageIzDtlService service;

    @ApiOperation(value = "출고내역상세 관리", notes = "출고된 상세내역을 조회한다")
    @GetMapping
    public PagingResult<SearchRes> getOutOfStorageIzDtls(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOutOfStorageIzDtls(dto, pageInfo);
    }
}
