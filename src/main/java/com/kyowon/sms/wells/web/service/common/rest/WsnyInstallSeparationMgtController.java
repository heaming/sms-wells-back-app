package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto.*;
import com.kyowon.sms.wells.web.service.common.service.WsnyInstallSeparationMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(tags = "[WSNY] 설치/분리 비용 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-separation-costs")
public class WsnyInstallSeparationMgtController {

    private final WsnyInstallSeparationMgtService service;

    @ApiOperation(value = "상품명 조회", notes = "특정 상품군에 속한 상품 목록을 조회한다.")
    @ApiImplicitParam(name = "pdCd", value = "상품그룹코드", paramType = "query", example = "1")
    @GetMapping("/filter-products")
    public List<FindProductsRes> getPdCd(String pdGr){
        return service.getPdCd(pdGr);
    }
    @ApiOperation(value = "설치/분리 비용 관리",notes = "고객서비스 수행시 사용되는 상품에 대한 상품 설치 및 분리 비용을 관리하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name ="pdCd" , value = "품목코드", paramType = "query"),
        @ApiImplicitParam(name ="apyMtrChk" , value = "현재적용자료 체크 여부", paramType = "query")
    })
    @GetMapping(value = "/paging")
    public PagingResult<SearchRes> getInstallSeparationCosts(
        SearchReq dto, PageInfo pageInfo
    ){
        return service.getInstallSeparationCosts(dto, pageInfo);
    }

    @ApiOperation(value = "설치/분리 비용 관리목록 엑셀 다운로드", notes = "고객서비스 수행시 사용되는 상품에 대한 상품 설치 및 분리 비용을 관리 목록을 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getInstallSeparationCostsExcelDownload(SearchReq dto) throws Exception{
        return this.service.getInstallSeparationCostsExcelDownload(dto);
    }

    @ApiOperation(value = "설치/분리비용 관리 - 등록, 수정, 삭제", notes = "설치/분리비용 관리 저장")
    @PostMapping
    public SaveResponse saveInstallSeparationCosts(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    )throws Exception{
        return SaveResponse.builder().processCount(service.saveInstallSeparationCosts(dtos)).build();
    }

    @ApiOperation(value = "설치/분리비용 관리 - 등록, 수정, 삭제", notes = "설치/분리비용 관리 삭제")
    @DeleteMapping
    public SaveResponse removeInstallSeparationCosts(
        @Valid
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    )throws Exception{
        return SaveResponse.builder().processCount(service.removeInstallSeparationCosts(dtos)).build();
    }
}
