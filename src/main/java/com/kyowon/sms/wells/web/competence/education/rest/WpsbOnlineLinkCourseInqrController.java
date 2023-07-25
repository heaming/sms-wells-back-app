package com.kyowon.sms.wells.web.competence.education.rest;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.education.dto.WpsbOnlineLinkCourseInqrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.education.service.WpsbOnlineLinkCourseInqrService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSB] 온라인 연계 과정 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/online-link-course")
public class WpsbOnlineLinkCourseInqrController {

    private final WpsbOnlineLinkCourseInqrService service;

    @ApiOperation(value = "온라인 연계 과정 조회 - 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "educSchdYm", value = "교육년월"),
        @ApiImplicitParam(name = "educDvCd", value = "교육유형", defaultValue = "40"),
        @ApiImplicitParam(name = "educCrseNo", value = "교육코스번호"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너ID"),
        @ApiImplicitParam(name = "educCpcAckmtYn", value = "교육수료여부"),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "사업단", paramType = "query"),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "총괄", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOnlineLinkCourseInqrList(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOnlineLinkCourseInqrPages(dto, pageInfo);
    }

    @ApiOperation(value = "온라인 연계 과정 조회 - 엑셀 다운로드", notes = "")
    @GetMapping("/excel-download")
    public List<SearchRes> getOnlineLinkCourseInqrListForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getOnlineLinkCourseInqrsForExcelDownload(dto);
    }
}
