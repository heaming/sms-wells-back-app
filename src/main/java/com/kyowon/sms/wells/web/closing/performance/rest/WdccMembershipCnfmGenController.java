package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccMembershipCnfmGenService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 멤버십 확정 생성")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/membership-confirms")
public class WdccMembershipCnfmGenController {
    private final WdccMembershipCnfmGenService service;

    @ApiOperation(value = "멤버십 확정 대상건 조회", notes = "멤버십 확정 대상건 조회를 위한 조회 서비스 실행")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "조직선택 (판매유입채널상세코드)", paramType = "query", required = true),
        @ApiImplicitParam(name = "fromCntrRcpFshDtm", value = "시작접수년월 (계약접수완료일시)", paramType = "query", required = true),
        @ApiImplicitParam(name = "toCntrRcpFshDtm", value = "종료접수년월 (계약접수완료일시)", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형 (판매유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "멤버십구분 (판매유형상세코드)", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getMembershipConfirmGenPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getMembershipConfirmGenPages(dto, pageInfo);
    }

    @ApiOperation(value = "멤버십 확정 대상건 엑셀 다운로드", notes = "엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRes> getMembershipCnfmGensForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMembershipCnfmGensForExcelDownload(dto);
    }

    @ApiOperation(value = "멤버십 확정 대상건 - 확정", notes = "입력받은 파라미터값을 키로 TB_SSCT_CNTR_BAS(계약기본) 테이블의 CNTR_CNFM_DTM(계약확정일시) 컬럼을 sysdate 로 update 한다.")
    @PutMapping
    public SaveResponse editMembershipCnfmGen(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.editMembershipCnfmGen(dto)).build();
    }

}
