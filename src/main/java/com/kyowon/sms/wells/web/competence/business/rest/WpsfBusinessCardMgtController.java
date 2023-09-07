package com.kyowon.sms.wells.web.competence.business.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.business.service.WpsfBusinessCardMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WPSF] 명함첩 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/business/partner")
public class WpsfBusinessCardMgtController {
    private final WpsfBusinessCardMgtService service;

    @ApiOperation(value = "명함첩 조회 - 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fnm", value = "성명"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getPartnerContactBasePages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getPartnerCustomerContactBasePages(dto, pageInfo);
    }

    @ApiOperation(value = "명함첩 조회 ", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fnm", value = "성명"),
    })
    @GetMapping
    public List<SearchRes> getPartnerCustomerContactBase(
        @Valid
        SearchReq dto
    ) {
        return service.getPartnerCustomerContactBase(dto);
    }

    @ApiOperation(value = "명함첩 연락처 저장", notes = "명함첩 연락처를 저장한다.")
    @PostMapping
    public SaveResponse savePartnerCustomerContactBase(
        @RequestBody
        @Valid
        WpsfBusinessCardMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.editReqPartnerCustomerContactBase(dto))
            .build();
    }

    @ApiOperation(value = "명함첩 연락처  삭제", notes = "명함첩 연락처를 삭제한다.")
    @DeleteMapping("/{ctplcSn}")
    public SaveResponse removePartnerCustomerContactBase(
        @NotEmpty
        @PathVariable("ctplcSn")
        String ctplcSn
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removePartnerCustomerContactBase(ctplcSn))
            .build();
    }

    @ApiOperation(value = "내명함 조회 ", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호"),
    })
    @GetMapping("/contact-base")
    public WpsfBusinessCardMgtDto.SearchPartnerRes getPartnerContactBase(
        @Valid
        SearchReq dto
    ) {
        return service.getPartnerContactBase(dto);
    }

    @ApiOperation(value = "내명함 등록", notes = "내명함 등록.")
    @PostMapping("/contact-base")
    public SaveResponse savePartnerContactBase(
        @RequestBody
        @Valid
        WpsfBusinessCardMgtDto.EditPartnerReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.savePartnerContactBase(dto))
            .build();
    }

    @ApiOperation(value = "내명함 발송", notes = "내명함 발송.")
    @PutMapping("/contact-base")
    public SaveResponse sendPartnerContactBase(
        @RequestBody
        @Valid
        List<WpsfBusinessCardMgtDto.SendReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendPartnerContactBase(dtos))
            .build();
    }

}
