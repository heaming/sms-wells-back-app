package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaStockStatusControlService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0134M01 재고상태조정 관리 Controller
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.11
 */
@Api(tags = "[WSNA] 재고상태조정 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-status-control")
public class WsnaStockStatusControlController {

    private final WsnaStockStatusControlService service;

    @ApiOperation(value = "재고상태조정 관리 페이징 조회", notes = "재고상태조정 관리 페이지를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getStockStatusControlPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getStockStatusControlPages(dto, pageInfo);
    }

    @ApiOperation(value = "재고상태조정 관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getStockStatusControlsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getStockStatusControlsForExcelDownload(dto);
    }

    @ApiOperation(value = "재고상태조정 관리 창고조회", notes = "재고상태조정 관리에 조회조건에 있는 조직창고를 조회한다")
    @GetMapping("/stocks")
    public List<SearchWarehouseRes> getStockStatusControlsWarehouse(
        SearchWarehouseReq dto
    ) {
        return service.getStockStatusControlsWarehouse(dto);
    }

    @ApiOperation(value = "재고상태조정 관리 관리부서조회", notes = "재고상태조정 관리에 조회조건에 있는 관리부서를 조회한다")
    @GetMapping("/organization/{wareNo}")
    public FindOgNmRes getOrganizationDeptName(
        @PathVariable
        String wareNo
    ) {
        return service.getOrganizationDeptName(wareNo);
    }

    @ApiOperation(value = "재고상태조정 관리 상품조회.", notes = "재고상태조정 관리 품목종류에 해당하는 상품을 조회한다.")
    @GetMapping("/product")
    public List<SearchItmPdCdRes> getStockStatusItmPdCd(
        SearchItmPdCdReq dto
    ) {
        return service.getStockStatusItmPdCd(dto);
    }

    @ApiOperation(value = "재고상태조정 상품조회", notes = "재고상태조정 관리 품목종류에 해당하는 상품을 조회한다.")
    @GetMapping("/status-product")
    public List<SearchWarehouseItmPdCdRes> getStatusProductItmPdCd(
        SearchStatusProductReq dto
    ) {
        return service.getStatusProductItmPdCd(dto);
    }

    @ApiOperation(value = "재고상태조정 상품에 대한 수량을 조회", notes = "재고상태조정 관리 품목종류와 상품코드에 해당하는 수량을 조회한다")
    @GetMapping("/product-qty")
    public SearchPdCdQtyRes getItmPdCdQty(
        SearchPdCdQtyReq dto
    ) {
        return service.getItmPdCdQty(dto);
    }

    @ApiOperation(value = "재고상태조정 관리 창고관련 상품조회.", notes = "재고상태조정 관리 품목종류에 해당하는 상품을 창고별로 조회한다.")
    @GetMapping("/product-warehouse")
    public List<SearchWarehouseItmPdCdRes> getStockStatusWarehouseItmPdCd(
        SearchWarehouseItmPdCdReq dto
    ) {
        return service.getStockStatusWarehouseItmPdCd(dto);
    }

    @ApiOperation(value = "재고상태 조정관리 저장", notes = "재고상태 조정관리 데이터를 저장한다.")
    @PostMapping
    public SaveResponse saveStockStatusControls(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveStockStatusControls(dtos)).build();
    }

    @ApiOperation(value = "산출 제외품목 삭제", notes = "산출 제외품목 데이터를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeStockStatusControls(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.updateStockStatusControlsForRemove(dtos)).build();
    }
}
