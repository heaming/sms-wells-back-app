package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaQomAsnService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSNA] 물량배정")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/qom-asn")
public class WsnaQomAsnController {

    private final WsnaQomAsnService service;

    @ApiOperation(value = "", notes = "")
    @GetMapping("/independence-warehouse")
    public PagingResult<SearchRes> getIndependenceWareQomAsns(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ){
        return service.getIndependenceWareQomAsns(dto, pageInfo);
    }

    @GetMapping("/individual-warehouse")
    public PagingResult<SearchRes> getIndividualWareQomAsns(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ){
        return service.getIndividualWareQomAsns(dto, pageInfo);
    }

    @GetMapping("/count")
    public int getCountQomAsn(SearchReq dto){
        return service.getCountQomAsn(dto);
    }

    @GetMapping("/ostr-ware")
    public List<WareRes> getOstrWarehouse(SearchReq dto){
        return service.getOstrWarehouse(dto);
    }
    @GetMapping("/str-ware")
    public List<WareRes> getStrWarehouse(SearchReq dto){
        return service.getStrWarehouse(dto);
    }

    @PutMapping("/warehouse-renewals")
    public SaveResponse editWarehouseRenewals(
        @RequestBody
        SearchReq dto
    ){
        return SaveResponse.builder()
            .processCount(service.editWarehouseRenewals(dto))
            .build();
    }

    @PostMapping("/independence-warehouse")
    public SaveResponse createIndependenceWareQomAsns(
        @Valid
        CreateIndependenceWareReq dto
    ){
        return SaveResponse.builder()
            .processCount(service.createIndependenceWareQomAsns(dto))
            .build();
    }
    @PostMapping("/individual-warehouse")
    public SaveResponse createIndividualWareQomAsns(
        @Valid
        CreateIndividualWareReq dto
    ){
        return SaveResponse.builder()
            .processCount(service.createIndividualWareQomAsns(dto))
            .build();
    }
}
