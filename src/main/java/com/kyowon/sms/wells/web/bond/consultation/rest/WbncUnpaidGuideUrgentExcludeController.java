package com.kyowon.sms.wells.web.bond.consultation.rest;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncUnpaidGuideUrgentExcludeService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 미납요금 안내/촉구 대상 제외관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/unpaid-excludes")
public class WbncUnpaidGuideUrgentExcludeController {

    private final WbncUnpaidGuideUrgentExcludeService service;

    @ApiOperation(value = "미납요금 안내/촉구 대상 제외관리 조회", notes = "검색 조건에 따른 대상 List 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schFwDv", value = "발송구분", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getUnpaidGuideUrgentExcludes(
        @Valid
        SearchReq dto
    ) {
        return service.getUnpaidGuideUrgentExcludes(dto);
    }

    @ApiOperation(value = "미납요금 안내/촉구 대상 제외관리 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PutMapping
    public SaveResponse saveUnpaidGuideUrgentExcludes(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveUnpaidGuideUrgentExcludes(dtos)).build();
    }

    @ApiOperation(value = "미납요금 안내/촉구 대상 제외관리 엑셀 업로드", notes = "엑셀 업로드")
    @PostMapping("/excel-upload")
    public UploadRes saveUnpaidGuideUrgentExcludesExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveUnpaidGuideUrgentExcludesExcelUpload(file);
    }
}
