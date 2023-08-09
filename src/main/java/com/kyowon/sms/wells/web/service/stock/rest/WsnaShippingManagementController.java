package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaShippingManagementService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0300M01 자가필터 배송관리
 * W-SV-U-0301M01 건식상품 배송관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.08.01
 */

@Api(tags = "[WSNA] (자가필터, 건식상품) 배송관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/shipping-management")
public class WsnaShippingManagementController {

    private final WsnaShippingManagementService service;

    @GetMapping("/products")
    @ApiOperation(value = "(자가필터,건식상품) 배송관리 조회 조건(상품 목록) 조회", notes = "자가필터,건식상품 배송관리 화면의 조회 조건인 상품 목록을 조회한다.")
    public List<SearchProductRes> getProducts(
        @Valid
        SearchProductReq dto
    ) {
        return this.service.getProducts(dto);
    }

    @GetMapping
    @ApiOperation(value = "(자가필터,건식상품) 목록 조회", notes = "선택한 조회 조건에 맞는 배송 목록을 조회한다.")
    public List<SearchRes> getShippingItems(
        @Valid
        SearchReq dto
    ) {
        return this.service.getShippingItems(dto);
    }

    @PostMapping
    @ApiOperation(value = "(자가필터,건식상품) 배송 관련 데이터 생성", notes = "(자가필터,건식상품) 배송 관련 데이터를 생성한다.")
    public SaveResponse saveShippingItems(
        @RequestBody
        @Valid
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.createShippingItems(dtos))
            .build();
    }
    //
    //    @PostMapping
    //    @ApiOperation(value = "AS자재 품목등급관리 데이터 저장", notes = "AS자재 품목등급 데이터를 저장한다.")
    //    public SaveResponse saveAsMaterialsItemGrades(
    //        @RequestBody
    //        @Valid
    //        @NotEmpty
    //        List<SaveReq> dtos
    //    ) throws Exception {
    //
    //        List<WsnaAsMaterialItemGradeDvo> dvos = this.converter.mapAllSaveReqToWsnaAsMaterialItemGradeDvo(dtos);
    //
    //        return SaveResponse.builder().processCount(this.service.saveAsMaterialItemGrades(dvos)).build();
    //    }

}
