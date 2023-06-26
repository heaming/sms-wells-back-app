package com.kyowon.sms.wells.web.bond.credit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.service.WbndRentalCbMgtObjectService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBND] 렌탈CB 관리 - 대상 조회 ")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-cb-mgt/objects")
public class WbndRentalCbMgtObjectController {

    private final WbndRentalCbMgtObjectService service;

    @ApiOperation(value = "렌탈CB 관리 대상 조회", notes = "렌탈CB 연체대상 건 조회 및 알림톡 발송 대상 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "selGbn", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getRentalCbMgtObjects(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalCbMgtObjects(dto);
    }

    @ApiOperation(value = "렌탈CB 관리 대상 save", notes = "렌탈CB 관리 대상의 알림수신여부를 save 하고 history 를 insert 한다.")
    @PutMapping
    public SaveResponse saveRentalCbMgtObjects(
        @Valid
        SearchReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveRentalCbMgtObjects(dto)).build();
    }

    @ApiOperation(value = "렌탈CB 납입정보 팝업 조회", notes = "고객별 렌탈CB 대상 또는 기 등록된 고객의 납입 정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
    })
    @GetMapping("/{cstNo}/paging")
    public List<SearchPaymentRes> getRentalCbMgtPaymentInfos(
        @PathVariable
        @Valid
        @NotBlank
        String cstNo,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRentalCbMgtPaymentInfos(cstNo, pageInfo);
    }

    @ApiOperation(value = "렌탈CB 납입정보 팝업 엑셀다운로드", notes = "고객별 렌탈CB 대상 또는 기 등록된 고객의 납입 정보 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
    })
    @GetMapping("/{cstNo}/excel-download")
    public List<SearchPaymentRes> getRentalCbMgtPaymentInfoForExcelDownload(
        @PathVariable
        @Valid
        @NotBlank
        String cstNo
    ) {
        return service.getRentalCbMgtPaymentInfoForExcelDownload(cstNo);
    }
}
