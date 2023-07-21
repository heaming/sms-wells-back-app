package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.*;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncRentalResignExpectedMgtService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 직권해지관리 - 렌탈 해지예정")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-rsg-exp-mgts")
public class WbncRentalResignExpectedMgtController {
    private final WbncRentalResignExpectedMgtService service;

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 조회", notes = "직권해지관리 - 렌탈 해지예정을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDt", value = "직권해지일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "상품유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "authRsgCd", value = "확정구분", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getRentalResignExpecteds(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalResignExpecteds(dto);
    }

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 예정생성", notes = "예정생성 버튼 클릭 시 해지예정을 생성한다.")
    @PostMapping
    public SaveResponse createRentalResignExpecteds(
        @RequestBody
        @Valid
        CreateReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.createRentalResignExpecteds(dto)).build();
    }

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 저장", notes = "저장 버튼 클릭 시 해지예정 변경내용을 저장한다.")
    @PutMapping
    public SaveResponse editRentalResignExpecteds(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.editRentalResignExpecteds(dtos)).build();
    }

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 (예정,최종)확정", notes = "예정확정 버튼 클릭 시 예정생성된 직권해지 데이터를 (예정,최종)확정 처리 한다.")
    @PutMapping("/confirm")
    public SaveResponse saveRentalResignExpectedCnfms(
        @RequestBody
        @Valid
        SaveConfirmReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentalResignExpectedCnfms(dto)).build();
    }

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 취소자료등록", notes = "취소자료등록 버튼 클릭 시 등록된 직권해지 대상을 취소처리 한다.")
    @PutMapping("/cancel")
    public SaveResponse saveRentalResignExpectedCancels(
        @RequestBody
        @Valid
        SaveCancelReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentalResignExpectedCancels(dto)).build();
    }

    @ApiOperation(value = "직권해지관리 - 렌탈 해지예정 엑셀 업로드", notes = "예정확정된 대상을 기준으로 제외 대상을 엑셀파일로 업로드 한다.")
    @PostMapping("/excel-upload")
    public UploadRes saveRentalResignExpectedExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveRentalResignExpectedExcelUpload(file);
    }
}
