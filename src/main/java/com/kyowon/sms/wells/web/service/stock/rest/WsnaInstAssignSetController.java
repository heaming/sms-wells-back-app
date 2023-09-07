package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstAssignSetDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaInstAssignSetService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[W-SV-U-0171P01] 설치배정세팅")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/Install-assign-set")
public class WsnaInstAssignSetController {

    private final WsnaInstAssignSetService service;

    @ApiOperation(value = "설치배정세팅 조회", notes = "설치배정 타임테이블 설정한 리스트 조회")
    @GetMapping("/paging")
    public PagingResult<WsnaInstAssignSetDto.SearchRes> getInstAssignSetPages(
        WsnaInstAssignSetDto.SearchReq dto, PageInfo pageInfo
    ) {
        return service.getInstAssignSetListPages(dto, pageInfo);
    }

    @ApiOperation(value = "설치배정세팅 저장", notes = "선택한 상품의 타임테이블 설치배정 가능일을 설정한다.")
    @PostMapping("/create-Inst-set")
    public SaveResponse createInstAssignSets(
        @RequestBody
        @Valid
        List<WsnaInstAssignSetDto.CreateReq> list
    ) {
        return SaveResponse.builder()
            .processCount(service.createInstAssignSetDatas(list))
            .build();

    }

    @ApiOperation(value = "설치배정세팅 삭제", notes = "선택한 상품의 타임테이블 설치배정 셋팅을 삭제한다.")
    @DeleteMapping("/delete-Inst-set")
    public SaveResponse removeInstAssignSets(
        @RequestBody
        @Valid
        List<WsnaInstAssignSetDto.RemoveReq> list
    ) {
        return SaveResponse.builder()
            .processCount(service.removeInstAssignSetDatas(list))
            .build();

    }

}
