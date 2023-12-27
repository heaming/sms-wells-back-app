package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.service.WsnyPaidAsCostMgtService;
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
 * W-SV-U-0159M01 유상A/S 서비스비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-03-08
 */
@Api(tags = "[WSNY] 유상 A/S 서비스비용 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/paid-as-costs")
public class WsnyPaidAsCostMgtController {
    private final WsnyPaidAsCostMgtService service;

    @ApiOperation(value = "유상 A/S 서비스비용 관리 화면",notes = "유상 A/S 서비스 수행시 품목별 비용(소비자가, 도매단가, 내부단가, 기술료 등)을 관리(조회/저장)하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name ="hgrPdCd" , value = "상위상품코드", paramType = "query"),
        @ApiImplicitParam(name ="pdCd" , value = "품목코드", paramType = "query"),
        @ApiImplicitParam(name ="cmnPartDbCdChk" , value = "공통부품 체크 여부", paramType = "query"),
        @ApiImplicitParam(name ="apyMtrChk" , value = "현재적용자료 체크 여부", paramType = "query")
    })
    @GetMapping(value = "/paging")
    public PagingResult<SearchRes> getPaidAsCostMgts(
        SearchReq dto, PageInfo pageInfo
    ){
        return service.getPaidAsCostMgts(dto, pageInfo);
    }

    @ApiOperation(value ="유상 A/S 서비스비용 관리목록 엑셀 다운로드", notes = "검색조건을 입력받아 엑셀 다운로드용 유상 A/S 서비스비용 관리 목록을 조회.")
    @GetMapping("excel-download")
    public List<SearchRes> getPaidAsCostMgtsExcelDownload(SearchReq dto) throws Exception{
        return this.service.getPaidAsCostMgtsExcelDownload(dto);
    }

    @ApiOperation(value="유상 A/S 서비스비용 관리 저장", notes="유상 A/S 서비스 수행시 품목별 비용(소비자가, 도매단가, 내부단가, 기술료 등)을 저장")
    @PostMapping
    public SaveResponse savePaidAsCostMgts (
        @Valid
        @RequestBody
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.savePaidAsCostMgts(dtos)).build();
    }
}
