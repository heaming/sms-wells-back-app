package com.kyowon.sms.wells.web.bond.credit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SaveTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkRes;
import com.kyowon.sms.wells.web.bond.credit.service.WbndRentalCbMgtService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBND] 렌탈 CB 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-cb")
public class WbndRentalCbMgtController {

    private final WbndRentalCbMgtService service;

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 페이징 조회", notes = "렌탈CB의 연체에 대한 알림톡 발송 제외 대상을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", example = "123456"),
        @ApiImplicitParam(name = "ctntExcdBndBizCd", value = "연락제외채권업무코드", paramType = "query", required = true, example = "02"),
        @ApiImplicitParam(name = "ctntExcdOjTpCd", value = "연락제외대상유형코드", paramType = "query", required = true, example = "01"),
        @ApiImplicitParam(name = "ctntExcdMediTpCd", value = "연락제외매체유형코드", paramType = "query", required = true, example = "03"),
    })
    @GetMapping("/notification-talks/paging")
    public PagingResult<SearchTalkRes> getNotificationTalkPages(
        @Valid
        SearchTalkReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getNotificationTalkPages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 엑셀 다운로드", notes = "렌탈CB의 연체에 대한 알림톡 발송 제외 대상 엑셀 다운로드")
    @GetMapping("/notification-talks/excel-download")
    public List<SearchTalkRes> getNotificationTalksForExcelDownload(
        @Valid
        SearchTalkReq dto
    ) {
        return service.getNotificationTalksForExcelDownload(dto);
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PutMapping("/notification-talks")
    public SaveResponse saveNotificationTalks(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveTalkReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveNotificationTalks(dtos)).build();
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 자료 삭제", notes = "선택한 알림톡 발송 제외 내역을 일괄 삭제한다.")
    @DeleteMapping("/notification-talks")
    public SaveResponse removeNotificationTalks(
        @Valid
        @RequestBody
        @NotEmpty
        List<String> bndCntcExcdOjIds
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.removeNotificationTalks(bndCntcExcdOjIds)).build();
    }

    @ApiOperation(value = "렌탈CB 알림톡 발송 제외 엑셀 업로드", notes = "엑셀 업로드")
    @PostMapping("/notification-talks/excel-upload")
    public UploadRes saveNotificationTalksExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveNotificationTalksExcelUpload(file);
    }
}
