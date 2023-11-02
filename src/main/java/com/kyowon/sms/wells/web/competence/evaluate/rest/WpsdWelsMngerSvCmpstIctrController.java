package com.kyowon.sms.wells.web.competence.evaluate.rest;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdWelsMngerSvCmpstIctrService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
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

@Api(tags = "[WPSD] 웰스매니저 서비스종합지표")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/evaluate/wels-mnger-sv-cmpst-ictr")
public class WpsdWelsMngerSvCmpstIctrController {

    private final WpsdWelsMngerSvCmpstIctrService service;

    @ApiOperation(value = "웰스매니저 서비스종합지표 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWelsMngerSvCmpstIctrPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getWelsMngerSvCmpstIctrPages(dto, pageInfo);
    }

    @ApiOperation(value = "웰스매니저 서비스종합지표 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getWelsMngerSvCmpstIctrsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getWelsMngerSvCmpstIctrsForExcelDownload(dto);
    }
}
