package com.kyowon.sms.wells.web.competence.voc.rest;

import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.competence.voc.service.WpshFalsehoodMngtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] wells 허위방문관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/voc-falsevisit")
public class WpshFalseVisitMngtController {

    private final WpshFalsehoodMngtService service;

    @ApiOperation(value = "wells 허위방문관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ocYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getFalsehoodMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFalsehoodMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 허위방문관리 저장", notes = "허위방문 목록을 저장한다.")
    @PostMapping
    public SaveResponse saveFalsevisit(
        @Valid
        @RequestBody
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveFalsevisit(dtos))
            .build();
    }

    @ApiOperation(value = "wells 허위방문관리 삭제", notes = "허위방문 목록을 삭제한다.")
    @DeleteMapping
    public SaveResponse removeFalsevisit(
        @Valid
        @RequestBody
        List<RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.removeFalsevisit(dtos)).build();
    }

    @ApiOperation(value = "wells 허위방문관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getFalsehoodMgtsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getFalsehoodMgtsForExcelDownload(dto);
    }

}
