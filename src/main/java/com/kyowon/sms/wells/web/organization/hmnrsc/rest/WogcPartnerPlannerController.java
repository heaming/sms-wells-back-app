package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SaveQulificationReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseDetailRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerPlannerService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WOGC] 플래너 관리 REST API")
@Validated
@RequiredArgsConstructor
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/partner")
public class WogcPartnerPlannerController {

    private final WogcPartnerPlannerService service;

    @ApiOperation(value = "수석플래너 신청관리 페이징 조회", notes = "조회 조건에 일치하는 수석플래너 신청관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prntrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "prntrKnm", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "mOgYn", value = "", paramType = "query", required = false),

    })
    @GetMapping("/paging")
    public PagingResult<WogcPartnerPlannerDto.SearchRes> getTopPlannerPages(
        WogcPartnerPlannerDto.SearchReq dto,
        @Valid
        PageInfo pageinfo
    ) {
        return service.getTopPlannerPages(dto, pageinfo);
    }

    @ApiOperation(value = "수석플래너 신청관리 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 수석플래너 신청관리를 조회한다.")
    @GetMapping("/excel-download")
    public List<WogcPartnerPlannerDto.SearchRes> getTopPlannerForExcelDownload(WogcPartnerPlannerDto.SearchReq dto) {
        return service.getTopPlannerForExcelDownload(dto);
    }

    @ApiOperation(value = "수석플래너 신청관리 삭제", notes = "수석플래너 신청관리를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeTopPlanner(
        @RequestBody
        WogcPartnerPlannerDto.DeleteReq dto
    ) throws Exception {
        this.service.removeTopPlanner(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "자격생성", notes = "자격생성을 통해 자격을 변경한다.")
    @PutMapping
    public SaveResponse saveTopPlanner(
        @Valid @RequestBody
        WogcPartnerPlannerDto.SaveReq dto
    ) throws Exception {
        this.service.saveTopPlanner(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "수석자격조정 팝업 조회", notes = "수석자격조정 팝업을 조회한다.")
    @GetMapping("/{bldCd}/{gridOgTpCd}")
    public WogcPartnerPlannerDto.FindRes getTopPlanner(@PathVariable
    String bldCd, @PathVariable
    String gridOgTpCd) {
        return this.service.getTopPlanner(bldCd, gridOgTpCd);
    }

    @ApiOperation(value = "자격조정", notes = "자격생성을 통해 자격을 변경한다.")
    @PutMapping("/{attOjsn}")
    public SaveResponse savePlanner(
        @Valid @RequestBody
        WogcPartnerPlannerDto.EditReq dto
    ) throws Exception {
        this.service.saveBuilding(dto);
        return SaveResponse.builder().processCount(1).build();
    }

    @ApiOperation(value = "매니저 자격관리 페이징 조회", notes = "조회 조건에 일치하는 매니저 자격관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "성명", paramType = "query", required = false),
        @ApiImplicitParam(name = "qlfDvCd", value = "자격구분코드", paramType = "query", required = false)

    })
    @GetMapping("/planner-license/paging")
    public PagingResult<SearchLicenseRes> getLicensePages(
        SearchLicenseReq dto,
        @Valid
        PageInfo pageinfo
    ) {
        return service.getPlannerLicensePages(dto, pageinfo);
    }

    @ApiOperation(value = "매니저 자격관리 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 매니저 자격관리를 조회한다.")
    @GetMapping("/planner-license/excel-download")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "성명", paramType = "query", required = false),
        @ApiImplicitParam(name = "qlfDvCd", value = "자격구분코드", paramType = "query", required = false)

    })
    public List<SearchLicenseRes> getPlannerLicenseForExcelDownload(SearchLicenseReq dto) {
        return service.getPlannerLicenseForExcelDownload(dto);
    }

    @ApiOperation(value = "매니저 자격관리 상세현황 페이징 조회", notes = "조회 조건에 일치하는 매니저 자격관리 상세현황을 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "path", required = true)
    })
    @GetMapping("/planner-license/{prtnrNo}/paging")
    public PagingResult<SearchLicenseDetailRes> getLicenseDetailPages(
        @PathVariable
        String prtnrNo,
        PageInfo pageinfo
    ) {
        return service.getPlannerLicenseDetailPages(prtnrNo, pageinfo);
    }

    @ApiOperation(value = "매니저 자격관리 보류, 개시 저장", notes = "매니저 자격정보를 보류, 개시 처리 한다.")
    @PostMapping("/planner-qualification-change")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "body", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "body", required = true),
        @ApiImplicitParam(name = "qlfDvCd", value = "자격구분코드", paramType = "body", required = true),
        @ApiImplicitParam(name = "strtdt", value = "시작일자", paramType = "body", required = true),
        @ApiImplicitParam(name = "cvDt", value = "전환일자", paramType = "body", required = false),
        @ApiImplicitParam(name = "qlfAplcDvCd", value = "자격신청구분코드", paramType = "body", required = false)
    })
    public SaveResponse createPlannerQualificationChange(
        @Valid @RequestBody
        SaveQulificationReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.createPlannerQualificationChange(dto)).build();
    }

    @ApiOperation(value = "매니저 자격관리 해약 저장", notes = "매니저 자격정보를 해약 처리 한다.")
    @PutMapping("/planner-qualification-cancel")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "body", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "body", required = true),
        @ApiImplicitParam(name = "qlfDvCd", value = "자격구분코드", paramType = "body", required = true),
        @ApiImplicitParam(name = "strtdt", value = "시작일자", paramType = "body", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일자", paramType = "body", required = true)
    })
    public SaveResponse createPlannerQualificationCancel(
        @Valid @RequestBody
        SaveQulificationReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.updatePlannerQualificationCancel(dto)).build();
    }
}
