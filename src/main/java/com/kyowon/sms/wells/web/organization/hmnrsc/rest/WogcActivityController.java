package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcActivityService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcActivityDto.*;

@RestController
@Api(tags = "[WOGC] 활동 현황 REST API")
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/activity")
@RequiredArgsConstructor
public class WogcActivityController {
    private final WogcActivityService wogcActivityService;

    @ApiOperation(value = "월별 활동 현황 정보 조회", notes = "월별 활동 현황 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "3차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "rolDvCd", value = "직무구분", paramType = "query", required = false)
    })
    @GetMapping({"/monthly"})
    public List<SearchMonthlyActivityRes> SearchMonthlyActivities(SearchMonthlyActivityReq reqDto) {
        return wogcActivityService.searchMonthlyActivities(reqDto);
    }

    @ApiOperation(value = "월별 활동 현황 정보 조회", notes = "월별 활동 현황 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "3차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "rolDvCd", value = "직무구분", paramType = "query", required = false)
    })
    @GetMapping({"/monthly/paging"})
    public PagingResult<SearchMonthlyActivityRes> SearchMonthlyActivitiesPages(SearchMonthlyActivityReq reqDto, @Valid PageInfo pageInfo) {
        return wogcActivityService.searchMonthlyActivitiesPages(reqDto, pageInfo);
    }

    @ApiOperation(value = "월별 활동 현황 정보 엑셀다운로드", notes = "월별 활동 현황 엑셀다운로드용 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "3차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "rolDvCd", value = "직무구분", paramType = "query", required = false)
    })
    @GetMapping({"/monthly/excel-download"})
    public List<SearchMonthlyActivityRes> SearchMonthlyActivitiesForExcelDownload(SearchMonthlyActivityReq reqDto) {
        return wogcActivityService.searchMonthlyActivities(reqDto);
    }

    @ApiOperation(value = "누적 활동 현황 정보 조회", notes = "누적 활동 현황 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "3차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "rolDvCd", value = "직무구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfCat", value = "실적구분", paramType = "query", required = false)
    })
    @GetMapping({"/accure"})
    public List<SearchAccureActivityRes> SearchAccureActivities(SearchAccureActivityReq reqDto) {
        return wogcActivityService.searchAccureActivities(reqDto);
    }

    @ApiOperation(value = "월별 활동 현황 정보 조회", notes = "월별 활동 현황 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "3차레벨조직ID", paramType = "query", example = "", required = false),
        @ApiImplicitParam(name = "rolDvCd", value = "직무구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfCat", value = "실적구분", paramType = "query", required = false)
    })
    @GetMapping({"/accure/paging"})
    public PagingResult<SearchAccureActivityRes> SearchAccureActivitiesPages(SearchAccureActivityReq reqDto, @Valid PageInfo pageInfo) {
        return wogcActivityService.searchAccureActivitiesPages(reqDto, pageInfo);
    }
}
