package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchItemsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbDeliveryBaseService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] BS소모품 배부기준")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/delivery-bases")
public class WsnaBsCsmbDeliveryBaseController {
    private final WsnaBsCsmbDeliveryBaseService service;

    @ApiOperation(value = "BS소모품 배부기준 목록 조회", notes = "입력된 데이터에 따른 BS소모품 배부기준 조회")
    @GetMapping
    public List<SearchRes> getDeliveryBases(SearchReq dto) {
        return service.getDeliveryBases(dto);
    }

    @ApiOperation(value = "BS소모품 배부기준 목록 조회", notes = "입력된 데이터에 따른 BS소모품 배부기준 조회")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getDeliveryBasesPages(SearchReq dto, PageInfo pageInfo) {
        return service.getDeliveryBasesPages(dto, pageInfo);
    }

    @ApiOperation(value = "BS소모품 배부기준 이월", notes = "당월 기준 직전월 배부기준 자료등록")
    @PostMapping("/next-month")
    public SaveResponse createDeliveryBasesNextMonth() {
        return SaveResponse.builder()
            .processCount(service.createDeliveryBasesNextMonth())
            .build();
    }

    @ApiOperation(value = "상품정보 조회", notes = "상품정보 조회")
    @GetMapping("/item-information")
    public List<SearchItemsRes> getAllItemInformation() {
        return service.getAllItemInformation();
    }

    @ApiOperation(value = "BS소모품 배부기준 등록", notes = "입력된 데이터에 따른 BS소모품 배부기준 등록")
    @PostMapping
    public SaveResponse createDeliveryBase(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createDeliveryBase(dtos))
            .build();
    }
}
