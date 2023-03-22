package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto;
import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsSvCsMngtDto.*;
import com.kyowon.sms.wells.web.service.common.service.WsnyRecapAsSvCsMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSNY] 유상 A/S 서비스비용 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/recap-as-sv-cs-mngt")
public class WsnyRecapAsSvCsMngtController {
    private final WsnyRecapAsSvCsMngtService service;

    @ApiOperation(value = "유상 A/S 서비스비용 관리 화면",notes = "유상 A/S 서비스 수행시 품목별 비용(소비자가, 도매단가, 내부단가, 기술료 등)을 관리(조회/저장)하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name ="hgrPdCd" , value = "상위상품코드", paramType = "query"),
        @ApiImplicitParam(name ="cmnPartDbCdChk" , value = "공통부품 체크 여부", paramType = "query"),
        @ApiImplicitParam(name ="apyMtrChk" , value = "현재적용자료 체크 여부", paramType = "query")
    })
    @GetMapping(value = "/paging")
    public PagingResult<SearchRes> getRecapAsSvCsMngts(
        SearchReq dto, PageInfo pageInfo
    ){
        return service.getRecapAsSvCsMngts(dto, pageInfo);
    }

    @ApiOperation(value = "유상 A/S 서비스비용 관리 상품 그룹 조회", notes = "조회조건인 상품그룹에 해당하는 상품을 조회.")
    @GetMapping("filter-products")
    public List<PdRes> getHgrPdCd(PdReq dto){
        return service.getHgrPdCd(dto);
    }

    @ApiOperation(value ="유상 A/S 서비스비용 관리목록 엑셀 다운로드", notes = "검색조건을 입력받아 엑셀 다운로드용 유상 A/S 서비스비용 관리 목록을 조회.")
    @GetMapping("excel-download")
    public List<SearchRes> getRecapAsSvCsMngtsExcelDownload(SearchReq dto) throws Exception{
        return this.service.getRecapAsSvCsMngtsExcelDownload(dto);
    }

    @ApiOperation(value="유상 A/S 서비스비용 관리 저장", notes="유상 A/S 서비스 수행시 품목별 비용(소비자가, 도매단가, 내부단가, 기술료 등)을 저장")
    @PostMapping
    public SaveResponse saveRecapAsSvCsMngts(
        @Valid
        @RequestBody
        List<SaveReq> dtos
    ){
        return SaveResponse.builder().processCount(this.service.saveRecapAsSvCsMngts(dtos)).build();
    }
}
