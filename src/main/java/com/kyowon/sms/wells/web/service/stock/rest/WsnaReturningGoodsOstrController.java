package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsOstrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.14
 */

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
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "212"),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "200005"),
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230302"),
        @ApiImplicitParam(name = "itmOstrNo", value = "품목출고번호", paramType = "query", example = "221202302160000001")
    })
    @GetMapping
    public SearchRes getReturningGoodsOstrs(SearchReq dto) {
        return this.service.getReturningGoodsOstrs(dto);
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
        List<RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.removeReturningGoodsOstrs(dtos)).build();
    }

    @ApiOperation(value = "시점 재고 조회", notes = "출고창고에 있는 상품의 시점재고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareNo", value = "출고창고번호", paramType = "query", example = "200005"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드", paramType = "query", example = "'WM07102143','WM07105938'"),
        @ApiImplicitParam(name = "itmGdCd", value = "품목등급코드", paramType = "query", example = "A")
    })
    @PostMapping("/{wareNo}")
    public List<SearchPitmStockRes> getPitmStocks(
        @PathVariable
        String wareNo,
        @Valid
        @RequestBody
        SearchPitmStockReq dto
    ) {
        return this.service.getPitmStocks(wareNo, dto);
    }

}
