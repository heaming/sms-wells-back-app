package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto.CreateReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto.District;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbLocaraCdMngtService;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbLocaraPsicMngtService;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbLocaraZipMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/responsible-areas")
@Api(tags = "[WSNC] 책임지역 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsncResponsibleAreaMngtController {

    private final WsncRpbLocaraZipMngtService zipMngtService;

    private final WsncRpbLocaraCdMngtService codeService;

    private final WsncRpbLocaraPsicMngtService psicMngtService;

    @ApiOperation(value = "책임지역 우편번호 조회", notes = "조회조건에 일치하는 책임지역 우편번호 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true)
    })
    @GetMapping("/zip-nos/paging")
    public PagingResult<SearchRes> getZipNoPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return zipMngtService.getZipNoPages(dto, pageInfo);
    }

    @ApiOperation(value = "책임지역 우편번호 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 우편번호 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true)
    })
    @GetMapping("/zip-nos/excel-download")
    public List<SearchRes> getZipNosForExcelDownload(
        SearchReq dto
    ) {
        return zipMngtService.getZipNosForExcelDownload(dto);
    }

    @ApiOperation(value = "책임지역 법정동 행정동 리스트 조회", notes = "책임지역 법정동 행정동 리스트 조회한다.")
    @GetMapping("/districts")
    public List<District> getDistricts() {
        return zipMngtService.getDistricts();
    }

    @ApiOperation(value = "책임지역 우편번호 저장", notes = "책임지역 우편번호를 저장한다.")
    @PostMapping("/zip-nos")
    public SaveResponse createZipNos(
        @Valid
        @RequestBody
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(zipMngtService.createZip(dtos))
            .build();
    }

    @ApiOperation(value = "책임지역 지역코드 조회", notes = "조회조건에 일치하는 책임지역 지역코드 정보를 조회한다.")
    @GetMapping("/area-codes/paging")
    public PagingResult<WsncRpbLocaraCdMngtDto.SearchRes> getLocalAreaCodePages(
        WsncRpbLocaraCdMngtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return codeService.getLocalAreaCodePages(dto, pageInfo);
    }

    @ApiOperation(value = "책임지역 지역코드 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 지역코드 목록을 조회한다.")
    @GetMapping("/area-codes/excel-download")
    public List<WsncRpbLocaraCdMngtDto.SearchRes> getLocalAreaCodePagesExcelDownload(
        WsncRpbLocaraCdMngtDto.SearchReq dto
    ) {
        return codeService.getLocalAreaCodePagesExcelDownload(dto);
    }

    @ApiOperation(value = "책임지역 지역코드 저장", notes = "책임지역 지역코드를 저장한다.")
    @PostMapping("/area-codes")
    public SaveResponse createLocalAreaCodes(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncRpbLocaraCdMngtDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(codeService.createLocalAreaCodes(dtos))
            .build();
    }

    @ApiOperation(value = "책임지역 담당자 관리", notes = "조회조건에 일치하는 책임지역별 책임담당자, 작업그룹, 서비스센터(지점) 등을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true),
        @ApiImplicitParam(name = "rpbLocaraCd", value = "지역코드", paramType = "query", example = "001")
    })
    @GetMapping("/person-in-charges/paging")
    public PagingResult<WsncRpbLocaraPsicMngtDto.SearchRes> getPersonInChargePages(
        WsncRpbLocaraPsicMngtDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return psicMngtService.getPersonInChargePages(dto, pageInfo);
    }

    @ApiOperation(value = "책임지역 담당자 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 담당자 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true),
        @ApiImplicitParam(name = "rpbLocaraCd", value = "지역코드", paramType = "query", example = "001")
    })
    @GetMapping("/person-in-charges/excel-download")
    public List<WsncRpbLocaraPsicMngtDto.SearchRes> getPersonInChargesForExcelDownload(
        WsncRpbLocaraPsicMngtDto.SearchReq dto
    ) {
        return psicMngtService.getPersonInChargesForExcelDownload(dto);
    }

    @ApiOperation(value = "책임지역 담당자 저장", notes = "책임지역별 적용시작일자, 적용종료일자, 담당파트너번호, 예비담당파트너번호1~5 정보를 저장한다.")
    @PostMapping("/person-in-charges")
    public SaveResponse createPersonInCharges(
        @Valid
        @RequestBody
        List<WsncRpbLocaraPsicMngtDto.CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(psicMngtService.createPersonInCharge(dtos))
            .build();
    }

}
