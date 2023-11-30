package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.ProdutCodeRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbGiveAOrderService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0014M01 BS소모품 발주수량 산출 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-30
 */

@Api(tags = "[WSNA] BS소모품 발주수량 산출")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-consumables")
public class WsnaBsCsmbGiveAOrderController {

    private final WsnaBsCsmbGiveAOrderService service;

    @GetMapping("/{itmKndCd}/product-codes")
    @ApiOperation(value = "품목 조회", notes = "품목종류에 해당하는 품목을 조회한다.")
    public List<ProdutCodeRes> getProductCodesByItmKndCd(
        @PathVariable
        String itmKndCd
    ) {
        return service.getProductCodesByItmKndCd(itmKndCd);
    }

    @GetMapping
    @ApiOperation(value = "BS소모품 발주수량 산출 조회", notes = "BS소모품 발주수량 산출 내역을 조회한다.")
    public List<WsnaBsCsmbGiveAOrderDvo> getBsCsmbGiveAOrderQty(@Valid
    SearchReq dto) {
        return service.getBsCsmbGiveAOrderQty(dto);
    }

    @PostMapping
    @ApiOperation(value = "BS소모품 발주수량 산출 저장", notes = "BS소모품 발주수량 산출 내역을 저장한다.")
    public SaveResponse createBsCsmbGiveAOrderQty(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createBsCsmbGiveAOrderQty(dtos))
            .build();
    }

}
