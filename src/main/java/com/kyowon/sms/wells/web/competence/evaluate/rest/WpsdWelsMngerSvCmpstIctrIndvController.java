package com.kyowon.sms.wells.web.competence.evaluate.rest;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrIndvDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrIndvDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdWelsMngerSvCmpstIctrIndvService;
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

@Api(tags = "[WPSD] 웰스매니저 서비스종합지표(개인)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/evaluate/wels-mnger-sv-cmpst-ictr-indv")
public class WpsdWelsMngerSvCmpstIctrIndvController {

    private final WpsdWelsMngerSvCmpstIctrIndvService service;

    @ApiOperation(value = "웰스매니저 서비스종합지표(개인) 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngtYm", value = "배정년월", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "사업단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "총괄", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "매니저번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWelsMngerSvCmpstIctrIndvPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getWelsMngerSvCmpstIctrIndvPages(dto, pageInfo);
    }

    @ApiOperation(value = "웰스매니저 서비스종합지표(개인) 엑셀 다운로드", notes = "")
    @GetMapping("/excel-download")
    public List<SearchRes> getWelsMngerSvCmpstIctrIndvsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getWelsMngerSvCmpstIctrIndvsForExcelDownload(dto);
    }
}
