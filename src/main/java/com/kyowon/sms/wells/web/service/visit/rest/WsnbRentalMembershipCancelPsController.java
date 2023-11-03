package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbRentalMembershipCancelPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/rental-membership-cancel-ps")
@Api(tags = "[WSNB] 렌탈,멤버십 취소 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbRentalMembershipCancelPsController {

    private final WsnbRentalMembershipCancelPsService service;

    @ApiOperation(value = "렌탈,멤버십 취소 현황", notes = "조회조건에 일치하는 렌탈,멤버십 취소 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRentalMembershipCancelPsPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getRentalMembershipCancelPsPages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈,멤버십 취소 현황 (엑셀 다운로드)", notes = "조회조건에 해당하는 렌탈,멤버십 취소 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRentalMembershipCancelPsForExcel(SearchReq dto) {
        return service.getRentalMembershipCancelPss(dto);
    }

}
