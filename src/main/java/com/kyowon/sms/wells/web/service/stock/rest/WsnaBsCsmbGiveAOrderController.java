package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreatReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbGiveAOrderService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] BS소모품 발주수량 산출")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-consumables")
public class WsnaBsCsmbGiveAOrderController {

    private final WsnaBsCsmbGiveAOrderService service;

    @GetMapping
    public List<SearchRes> getBsCsmbGiveAOrderQty(SearchReq dto) {
        return service.getBsCsmbGiveAOrderQty(dto);
    }

    @PostMapping
    public SaveResponse createBsCsmbGiveAOrderQty(
        @RequestBody
        @Valid
        List<CreatReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createBsCsmbGiveAOrderQty(dtos))
            .build();
    }

    @GetMapping("/{itmKndCd}/product-codes")
    public List<WsnaBsCsmbGiveAOrderDto.ProdutCodeRes> getProductCodesByItmKndCd(
        @PathVariable
        String itmKndCd
    ) {
        return service.getProductCodesByItmKndCd(itmKndCd);
    }
}
