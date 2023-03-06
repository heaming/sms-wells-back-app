package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbAutoPrepaymentDiscountExcludeMgtService;
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
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/auto-prepayment-discount-exclude")
public class WwdbAutoPrepaymentDiscountExcludeMgtController {

    private final WwdbAutoPrepaymentDiscountExcludeMgtService service;

    @ApiOperation(value = "자동 선납할인제외 관리 조회", notes = "검색조건을 받아 자동 선납할인제외 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAutoPrepaymentDiscountExcludePages(SearchReq dto, PageInfo pageInfo) {
        return service.getAutoPrepaymentDiscountExcludePages(dto, pageInfo);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 엑셀 다운로드", notes = "검색조건을 받아 자동 선납할인제외 관리 목록을 엑셀 다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getAutoPrepaymentDiscountExcludeExcels(SearchReq dto) {
        return service.getAutoPrepaymentDiscountExcludeExcels(dto);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 계약정보 조회", notes = "검색조건을 받아 자동 선납할인제외 관리 계약 정보을 조회한다.")
    @GetMapping("/contracts")
    public SearchContractRes getAutoPrepaymentDiscountExcludeContractInformation(
        SearchContractReq dto
    ) {
        return service.getAutoPrepaymentDiscountExcludeContractInformation(dto);
    }

    @ApiOperation(value = "자동 선납할인제외 관리 저장", notes = " RDS 적요 자동 선납할인제외 관리 등록 및 수정한다.")
    @PostMapping
    public SaveResponse saveAutoPrepaymentDiscountExcludes(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAutoPrepaymentDiscountExcludes(dto))
            .build();
    }

}
