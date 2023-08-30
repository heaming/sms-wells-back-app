package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SendReq;
import com.kyowon.sms.wells.web.closing.performance.service.WdccPrepaymentExpirationService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDCC] 선납만료 고객현황 - 대상현황")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/prepayment-expiration")
@RequiredArgsConstructor
@Validated
public class WdccPrepaymentExpirationController {
    private final WdccPrepaymentExpirationService service;

    /*@ApiOperation(value = "죄회구분", notes = "죄회구분")
    @GetMapping("/inqrDvs")
    public List<SearchRes> getInqrDvs(
        @Valid
        SearchReq dto
    ) {
        return service.getInqrDvs(dto);
    }*/

    @ApiOperation(value = "선납만료 고객현황 - 대상현황", notes = "선납만료 고객현황 - 대상현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "관리년월", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "dpStDt", value = "입금시작월", paramType = "query"),
        @ApiImplicitParam(name = "dpEdDt", value = "입금종료월", paramType = "query"),
        @ApiImplicitParam(name = "ogcd1", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "ogcd2", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "ogcd3", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "bzrNo", value = "사업자등록번호", paramType = "query"),
        @ApiImplicitParam(name = "dpYn", value = "입금여부", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "취소여부", paramType = "query"),
        @ApiImplicitParam(name = "rentalStn", value = "렌탈회차_from", paramType = "query"),
        @ApiImplicitParam(name = "rentalEtn", value = "렌탈회차_to", paramType = "query"),
        @ApiImplicitParam(name = "upYn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "upNo", value = "업무담당 사번", paramType = "query"),
    })
    @GetMapping("/object-present-state")
    public List<SearchRes> getObjectPresentState(
        @Valid
        SearchReq dto
    ) {
        return service.getObjectPresentState(dto);
    }

    @ApiOperation(value = "선납만료 고객현황 - 문자발송업로드현황", notes = "선납만료 고객현황 - 문자발송업로드현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "관리년월", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "dpStDt", value = "입금시작월", paramType = "query"),
        @ApiImplicitParam(name = "dpEdDt", value = "입금종료월", paramType = "query"),
        @ApiImplicitParam(name = "ogcd1", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "ogcd2", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "ogcd3", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "bzrNo", value = "사업자등록번호", paramType = "query"),
        @ApiImplicitParam(name = "dpYn", value = "입금여부", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "취소여부", paramType = "query"),
        @ApiImplicitParam(name = "rentalStn", value = "렌탈회차_from", paramType = "query"),
        @ApiImplicitParam(name = "rentalEtn", value = "렌탈회차_to", paramType = "query"),
        @ApiImplicitParam(name = "upYn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "upNo", value = "업무담당 사번", paramType = "query"),
    })
    @GetMapping("/character-fw-uld")
    public List<SearchCharacterFwUldRes> getCharacterFwUld(
        @Valid
        SearchCharacterFwUldReq dto
    ) {
        return service.getCharacterFwUld(dto);
    }

    @ApiOperation(value = "선납만료 고객현황 - 문자발송내역", notes = "선납만료 고객현황 - 문자발송내역")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sndgDt", value = "발송일", paramType = "query"),
        @ApiImplicitParam(name = "cellNo", value = "전화번호", paramType = "query"),
        @ApiImplicitParam(name = "scsYn", value = "성공여부", paramType = "query"),
    })
    @GetMapping("/character-fw-iz")
    public List<SearchCharacterFwIzRes> getCharacterFwIz(
        @Valid
        SearchCharacterFwIzReq dto
    ) {
        return service.getCharacterFwIz(dto);
    }

    @ApiOperation(value = "선납만료 고객현황이력 문자발송", notes = "선납만료 고객현황이력 문자발송한다.")
    @PostMapping
    public SaveResponse sendPrepaymentExpirationHistorys(
        @RequestBody
        @Valid
        @NotEmpty
        List<SendReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.sendPrepaymentExpirationHistorys(dtos)).build();
    }
}
