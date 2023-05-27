package com.kyowon.sms.wells.web.deduction.redf.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchAwRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.redf.service.WdeaAllowanceRedfMgtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEA] 수당(실적) 되물림 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/redf/allowance-report")
public class WdeaAllowanceRedfMgtController {

    private final WdeaAllowanceRedfMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmFrom", value = "매출년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmTo", value = "매출년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "상품구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNoFrom", value = "파트너번호(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNoTo", value = "파트너번호(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "envrYn", value = "환경여부", paramType = "query", required = false),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록조회", notes = "수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchAwRedfRes> getAwRedfMgtPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getAwRedfMgtPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmFrom", value = "매출년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmTo", value = "매출년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "상품구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNoFrom", value = "파트너번호(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNoTo", value = "파트너번호(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "envrYn", value = "환경여부", paramType = "query", required = false),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록 엑셀다운로드", notes = "수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록을 조회하고 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchAwRedfRes> getAwRedfMgtForExcelDownload(SearchReq dto) throws Exception {

        return service.getAwRedfMgtForExcelDownload(dto);

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmFrom", value = "매출년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmTo", value = "매출년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "상품구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "envrYn", value = "환경여부", paramType = "query", required = false),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - B2B/총판 목록조회", notes = "수당(실적) 되물림 관리 - B2B/총판 목록을 조회한다.")
    @GetMapping("/b2b-paging")
    public PagingResult<SearchRedfRes> getRedfMgtPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getRedfMgtPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmFrom", value = "매출년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "slYmTo", value = "매출년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "상품구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogCdFrom", value = "소속코드(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCdTo", value = "소속코드(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "envrYn", value = "환경여부", paramType = "query", required = false),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - B2B/총판 목록 엑셀다운로드", notes = "수당(실적) 되물림 관리 - B2B/총판 목록을 조회하고 엑셀다운로드 한다.")
    @GetMapping("/b2b-excel-download")
    public List<SearchRedfRes> getRedfMgtForExcelDownload(SearchReq dto) throws Exception {

        return service.getRedfMgtForExcelDownload(dto);

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회", notes = "수당(실적) 되물림 관리 - 영업부 되물림 생성 목록을 조회한다.")
    @GetMapping("/bizd-paging")
    public PagingResult<SearchRedfBizdRes> getRedfBizdMgt(SearchRedfBizdReq dto, PageInfo pageInfo) throws Exception {

        return service.getRedfBizdMgt(dto, pageInfo);

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "redfAdsbOcYmFrom", value = "발생년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbOcYmTo", value = "발생년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmFrom", value = "실적년월(from)", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYmTo", value = "실적년월(to)", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 엑셀다운로드", notes = "수당(실적) 되물림 관리 - 영업부 되물림 생성 목록을 엑셀다운로드 한다.")
    @GetMapping("/bizd-excel-download")
    public List<SearchRedfBizdRes> getRedfBizdMgtForExcelDownload(SearchRedfBizdReq dto) throws Exception {

        return service.getRedfBizdMgtForExcelDownload(dto);

    }

}
