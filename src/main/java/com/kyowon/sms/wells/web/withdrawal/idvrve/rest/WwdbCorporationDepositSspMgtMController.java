package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.CreateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbCorporationDepositSspMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbCorporationDepositSspMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[수납입출금 - 개별수납] 법인입금 예정관리/대사처리 -SSP제휴")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/corporation-dp-ssp")
@Slf4j
public class WwdbCorporationDepositSspMgtMController {

    private final WwdbCorporationDepositSspMgtService service;

    @ApiOperation(value = "법인입금 SSP제휴 조회", notes = "검색조건을 받아 SSP제휴 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "실적일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "rveDt", value = "수납일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "crpAcno", value = "계좌번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "alncmpDprNm", value = "입금자명", paramType = "query", required = false),
        @ApiImplicitParam(name = "rveCd", value = "수납코드", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getselectCorporationDepositSspPages(SearchReq dto, PageInfo pageInfo) {
        return service.getselectCorporationDepositSspPages(dto, pageInfo);
    }

    @ApiOperation(value = "법인입금 SSP제휴 엑셀다운", notes = "검색조건을 받아 SSP제휴 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "실적일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "rveDt", value = "수납일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "crpAcno", value = "계좌번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "alncmpDprNm", value = "입금자명", paramType = "query", required = false),
        @ApiImplicitParam(name = "rveCd", value = "수납코드", paramType = "query", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getselectCorporationDepositSspExcels(SearchReq dto) {
        return service.getselectCorporationDepositSspExcels(dto);
    }

    @ApiOperation(value = "법인입금 SSP제휴 엑셀업로드", notes = "업로드 된 파일의 내용을 읽어서 업로드시킨다.")
    @PostMapping("/excel-upload")
    public UploadRes getUploadExcels(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {

        List<WwdbCorporationDepositSspMgtDvo> list = service.getUploadExcels(file);

        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        return UploadRes.builder()
            .status("S")
            .excelData(list)
            .errorInfo(excelUploadErrorDvos)
            .build();
    }

    @ApiOperation(value = "법인입금 SSP제휴 저장", notes = "법인입금 SSP제휴 내용을 저장한다.")
    @PostMapping
    public SaveResponse createCorporationDepositSsps(
        @RequestBody
        @Valid
        List<CreateReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createCorporationDepositSsps(dto))
            .build();
    }

}
