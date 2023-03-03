package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbAutomaticPrepaymentDiscountExcludeMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 자동 선납할인제외 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/automatic-prepayment-discount-exclude")
public class WwdbAutomaticPrepaymentDiscountExcludeMgtController {

    private final WwdbAutomaticPrepaymentDiscountExcludeMgtService service;

    @ApiOperation(value = "자동 선납할인제외 관리 조회", notes = "검색조건을 받아 자동 선납할인제외 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAutomaticPrepaymentDiscountExcludePages(SearchReq dto, PageInfo pageInfo) {
        return service.getAutomaticPrepaymentDiscountExcludePages(dto, pageInfo);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 엑셀 다운로드", notes = "검색조건을 받아 자동 선납할인제외 관리 목록을 엑셀 다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getAutomaticPrepaymentDiscountExcludeExcels(SearchReq dto) {
        return service.getAutomaticPrepaymentDiscountExcludeExcels(dto);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 계약정보 조회", notes = "검색조건을 받아 자동 선납할인제외 관리 계약 정보을 조회한다.")
    @GetMapping("/contract-information")
    public SearchContractInformationRes getAutomaticPrepaymentDiscountExcludeContractInformation(
        SearchContractInformationReq dto
    ) {
        return service.getAutomaticPrepaymentDiscountExcludeContractInformation(dto);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 저장", notes = " RDS 적요 자동 선납할인제외 관리 등록 및 수정한다.")
    @PostMapping
    public SaveResponse saveAutomaticPrepaymentDiscountExcludes(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAutomaticPrepaymentDiscountExcludes(dto))
            .build();
    }

}
