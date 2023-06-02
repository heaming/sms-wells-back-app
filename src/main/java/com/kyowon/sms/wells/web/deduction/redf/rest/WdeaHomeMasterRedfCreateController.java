package com.kyowon.sms.wells.web.deduction.redf.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchDlqRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchRes;
import com.kyowon.sms.wells.web.deduction.redf.service.WdeaHomeMasterRedfCreateService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEA] 홈마스터 되물림생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/redf/home-master")
public class WdeaHomeMasterRedfCreateController {

    private final WdeaHomeMasterRedfCreateService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "홈마스터 되물림생성", notes = "홈마스터 되물림생성 데이터를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getHomeMasterRedfPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getHomeMasterRedfPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "홈마스터 되물림생성 엑셀 다운로드", notes = "홈마스터 되물림생성 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getHomeMasterRedfForExcelDownload(SearchReq dto) throws Exception {

        return service.getHomeMasterRedfForExcelDownload(dto);

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "홈마스터 되물림생성 - 연체", notes = "홈마스터 되물림생성 - 연체 데이터를 조회한다.")
    @GetMapping("/delinquent-paging")
    public PagingResult<SearchDlqRes> getHomeMasterDelinquentRedfPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getHomeMasterDelinquentRedfPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "홈마스터 되물림생성 - 연체 엑셀 다운로드", notes = "홈마스터 되물림생성 - 연체 엑셀 다운로드")
    @GetMapping("/delinquent-excel-download")
    public List<SearchDlqRes> getHomeMasterDelinquentRedfForExcelDownload(SearchReq dto) throws Exception {

        return service.getHomeMasterDelinquentRedfForExcelDownload(dto);

    }
}
