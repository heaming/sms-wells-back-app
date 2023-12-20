package com.kyowon.sms.wells.web.competence.evaluate.rest;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdExclDivAwdErnWhtxRgstService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[PSD] 우수사업부 시상소득원천세 등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/div-awd-ern-whtx")
public class WpsdExclDivAwdErnWhtxRgstController {

    private final WpsdExclDivAwdErnWhtxRgstService service;

    @ApiOperation(value = "우수사업부 시상소득원천세 등록 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getExclDivAwdErnWhtxRgstPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getExclDivAwdErnWhtxRgstPages(dto, pageInfo);
    }

    @ApiOperation(value = "우수사업부 시상소득원천세 등록 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getExclDivAwdErnWhtxRgstExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getExclDivAwdErnWhtxRgstExcelDownload(dto);
    }

    @ApiOperation(value = "우수사업부 시상소득원천세 등록 엑셀 업로드", notes = "")
    @PostMapping("/excel-upload")
    public ExcelUploadDto.UploadRes exclDivAwdErnWhtxRgstExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.exclDivAwdErnWhtxRgstExcelUpload(file);
    }
}
