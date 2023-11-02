package com.kyowon.sms.wells.web.competence.voc.rest;

import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.voc.service.WpshVocReceiptPsicMngtService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WPSH] VOC 접수담당자 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/vocPsic")
public class WpshVocReceiptPsicMngtController {

    private final WpshVocReceiptPsicMngtService service;

    @ApiOperation(value = "VOC 접수담당자 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getVocReceiptPsicMngtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getVocReceiptPsicMngtPages(dto, pageInfo);
    }

    @ApiOperation(value = "VOC 접수자 관리 접수자 등록", notes = "")
    @PostMapping
    public SaveResponse saveVocReceiptPsic(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) {
        return SaveResponse.builder().processCount(service.saveVocReceiptPsic(dto)).build();
    }
}
