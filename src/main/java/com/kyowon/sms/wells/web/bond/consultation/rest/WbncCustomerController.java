package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindBaseYmRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveCounselReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveUnuitmCnReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncCustomerService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 채권상담 고객리스트")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/bond-counsel")
public class WbncCustomerController {

    private final WbncCustomerService service;

    @ApiOperation(value = "기준년월 조회", notes = "조회조건에 일치하는 기준년월 정보를 조회한다.")
    @GetMapping("/base-ym")
    public FindBaseYmRes getBaseYm() {
        return service.getBaseYm();
    }

    @ApiOperation(value = "채권상담 고객리스트", notes = "검색 조건을 입력 받아 채권상담 고객리스트 정보를 조회 한다.")
    @GetMapping("/customers")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schClctamNo", value = "집금번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstNm", value = "고객명", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCralLocaraTno", value = "계약휴대전화번호1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schMexnoEncr", value = "계약휴대전화번호2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCralIdvTno", value = "계약휴대전화번호3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstNm", value = "고객명", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schClctamPsic", value = "집금담당자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schSfK", value = "세이프키", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstCralLocaraTno", value = "설치휴대전화번호1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstMexnoEncr", value = "설치휴대전화번호2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstCralIdvTno", value = "설치휴대전화번호3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDlqMcntStrt", value = "from 연체개월", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDlqMcntEnd", value = "to 연체개월", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDv", value = "이체구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDtStrt", value = "from 이체일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDtEnd", value = "to 이체일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "seachOjBlamStrt", value = "from 대상잔액", paramType = "query", example = ""),
        @ApiImplicitParam(name = "seachOjBlamEnd", value = "to 대상잔액", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstDv", value = "고객구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCpsnRsgYn", value = "강제해지여부", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", example = "")
    })
    public List<SearchRes> getCustomers(SearchReq dto) throws Exception {
        return service.getCustomers(dto);
    }

    @ApiOperation(value = "채권상담 고객상세 조회", notes = "조회조건에 일치하는 채권상담 고객상세 정보를 조회한다.")
    @GetMapping("/customer-detail")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBaseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    public Optional<FindRes> getCustomerDetail(@Valid
    FindReq dto)
        throws Exception {
        return service.getCustomerDetail(dto);
    }

    @ApiOperation(value = "채권상담 고객상세 그리드조회", notes = "조회조건에 일치하는 채권상담 고객상세 그리드 정보를 조회한다.")
    @GetMapping("/customer-details")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBaseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    public List<FindCustomerDetailRes> getCustomerDetails(FindCustomerDetailReq dto) throws Exception {
        return service.getCustomerDetails(dto);
    }

    @ApiOperation(value = "고객상세 특이사항 조회", notes = "조회조건에 일치하는 고객상세 특이사항 정보를 조회한다.")
    @GetMapping("/unusual-articles")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBaseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    public FindUnusualArticlesRes getUnusualArticles(@Valid
    FindUnusualArticlesReq dto) {
        return service.getUnusualArticles(dto);
    }

    @ApiOperation(value = "채권상담 고객상세 상담이력조회", notes = "조회조건에 일치하는 채권상담 고객상세 상담이력 정보를 조회한다.")
    @GetMapping("/counsel-history")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schBaseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "schCntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    public List<FindCounselHistoryRes> getCounselHistorys(FindCounselHistoryReq dto) throws Exception {
        return service.getCounselHistorys(dto);
    }

    @ApiOperation(value = "고객상세 특이상항 내용 저장", notes = "고객상세 특이상항 내용을 저장한다.")
    @PostMapping("/customer-detail/unuitmCn")
    public SaveResponse saveUnuitmCn(
        @RequestBody
        @Valid
        SaveUnuitmCnReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveUnuitmCn(dto))
            .build();
    }

    @ApiOperation(value = "고객상세 상담/약속 내용 저장", notes = "고객상세 상담/약속 내용을 저장한다.")
    @PostMapping("/customer-detail/counsel")
    public SaveResponse saveCounsel(
        @RequestBody
        @Valid
        SaveCounselReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveCounsel(dto))
            .build();
    }

}
