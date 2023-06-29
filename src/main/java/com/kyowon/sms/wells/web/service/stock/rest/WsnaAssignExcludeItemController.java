package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.WareRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAssignExcludeItemService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 배정제외품목 등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/assign-exclude-items")
public class WsnaAssignExcludeItemController {
    private final WsnaAssignExcludeItemService service;

    @GetMapping
    public PagingResult<SearchRes> getAssignExcludeItems(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAssignExcludeItems(dto, pageInfo);
    }

    @GetMapping("/warehouse")
    public List<WareRes> getWarehouse(SearchReq dto){
        return service.getWarehouse(dto);
    }
    @PutMapping
    public int saveAssignExcludeItems(
        @RequestBody
        List<SaveReq> list
    ) {
        return service.saveAssignExcludeItems(list);
    }
}
