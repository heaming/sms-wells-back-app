package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchRes;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncSeedingDeliveryListService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 모종 배송내역")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/seeding-delivery-list")
@Slf4j
public class WsncSeedingDeliveryListController {

    private final WsncSeedingDeliveryListService service;

    @ApiOperation(value = "모종 배송내역 조회", notes = "조회")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOtherRegionMgtState(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getSeedingDeliveryList(dto, pageInfo);
    }
}
