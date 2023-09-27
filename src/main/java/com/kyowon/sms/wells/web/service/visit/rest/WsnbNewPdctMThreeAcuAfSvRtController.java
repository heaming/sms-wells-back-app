package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbNewPdctMThreeAcuAfSvRtDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbNewPdctMThreeAcuAfSvRtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Slf4j
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/newpd-m-three-acu-af-sv-rt")
@Api(tags = "[WSNB] 실적_신제품 M+3 누적 A/S율")
@RequiredArgsConstructor
@Validated
public class WsnbNewPdctMThreeAcuAfSvRtController {
    private final WsnbNewPdctMThreeAcuAfSvRtService service;

    @ApiOperation(value = "실적_신제품 M+3 누적 A/S율")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseY", value = "기준년월", paramType = "query", example = "2023"),
        @ApiImplicitParam(name = "svType", value = "서비스유형", paramType = "query", example = "3110"),
        @ApiImplicitParam(name = "badDivide", value = "불량구분", paramType = "query", example = "500R"),
        @ApiImplicitParam(name = "pdGrp", value = "상품그룹", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM04100162")
    })
    @GetMapping
    public List<SearchRes> getNewPdctMThreeAcuAfSvRtList(SearchReq dto){
        return this.service.getNewPdctMThreeAcuAfSvRtList(dto);
    }

    @ApiOperation(value = "상품 목록 조회", notes = "신제품 M+3 누적 A/S율 > M+3 출시일 등록 팝업화면에 출력되는 목록을 조회한다.")
    @GetMapping("/pd-result/paging")
    public PagingResult<PdListRes> getPdResultPages(PdListReq dto, @Valid PageInfo pageInfo){
        return service.getPdResultPages(dto, pageInfo);
    }

    @ApiOperation(value = "M+3 출시일 등록 내역 삭제", notes = "M+3 출시일 등록 내역을 삭제한다.")
    @DeleteMapping("/pd-result-changes")
    public SaveResponse removeNewPdctMThreeAcuAfSvRtInfos(
        @Valid
        @RequestParam
        @NotEmpty
        List<String> pdCds
    ){
        return SaveResponse.builder()
            .processCount(this.service.removeNewPdctMThreeAcuAfSvRtInfos(pdCds))
            .build();
    }

    @ApiOperation(value = "M+3 출시일 등록 내역 등록/수정", notes = "M+3 출시일 등록 내역을 등록/수정한다.")
    @PostMapping("/pd-result-changes")
    public SaveResponse saveNewPdctMThreeAcuAfSvRtInfos(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ){
        return SaveResponse.builder()
            .processCount(this.service.saveNewPdctMThreeAcuAfSvRtInfos(dtos))
            .build();
    }

    @ApiOperation(value = "M+3 출시일 등록 화면용 상품 리스트 조회", notes = "M+3 출시일 등록 화면용 상품 리스트를 조회한다.")
    @GetMapping("/pd-dtl-list")
    public List<PdDtlListRes> getPdDtlList(){
        return service.getPdDtlList();
    }
}
