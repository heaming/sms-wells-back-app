package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SearchWarehouseReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.SearchWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsOstrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-out-of-storages")
@Api(tags = "[WSNA] 반품출고 등록 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaReturningGoodsOstrController {

    private final WsnaReturningGoodsOstrService service;

    @ApiOperation(value = "출고요청창고 조회", notes = "로그인한 사용자의 출고 요청 가능 창고 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "userId", value = "사용자ID", paramType = "query", example = "36631", required = true),
        @ApiImplicitParam(name = "apyYm", value = "적용년월", paramType = "query", example = "202301", required = true)
    })
    @GetMapping("/warehouses")
    public List<SearchWarehouseRes> getWareHouses(
        @Valid
        SearchWarehouseReq dto
    ) {
        return this.service.getWareHouses(dto);
    }

    @ApiOperation(value = "반품출고 조회", notes = "조회조건에 일치하는 반품출고 등록된 상품과 재고를 조회한다.")
    @ApiImplicitParam(name = "itmOstrNo", value = "품목출고", paramType = "path", example = "221202302160000001", required = true)
    @GetMapping("/{itmOstrNo}")
    public SearchRes getReturningGoodsOstrs(
        @PathVariable
        String itmOstrNo
    ) {
        return this.service.getReturningGoodsOstrs(itmOstrNo);
    }

    @ApiOperation(value = "입고마감 체크", notes = "품목출고내역에서 품목출고번호의 입고마감 여부를 체크한다.")
    @ApiImplicitParam(name = "itmOstrNo", value = "품목출고번호", paramType = "query", example = "221202302160000001", required = true)
    @GetMapping("/deadline-check")
    public Boolean isClosed(@RequestParam
    String itmOstrNo) {
        return this.service.isClosed(itmOstrNo);
    }

    @ApiOperation(value = "반품출고 등록", notes = "창고장이나 매니저/엔지니어가 물류센터/서비스센터/영업센터로 반품 출고요청을 등록한다.")
    @PostMapping
    public SaveResponse saveReturningGoodsOstrs(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveReturningGoodsOstrs(dtos)).build();
    }

    @ApiOperation(value = "반품출고 삭제", notes = "등록된 반품출고 요청 건을 삭제한다.")
    @DeleteMapping
    public SaveResponse removeReturningGoodsOstrs(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsnaReturningGoodsOstrDto.RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.removeReturningGoodsOstrs(dtos)).build();
    }

}
