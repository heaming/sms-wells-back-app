package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbAreaCodeMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2022.11.22
 */
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/responsible-area-codes")
@Api(tags = "[WSNC] 책임 지역 지역 코드 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsncRpbAreaCodeMgtController {

    private final WsncRpbAreaCodeMgtService service;

    /**
     * 책임지역 지역코드 관리 - 조회
     */
    @ApiOperation(value = "책임지역 지역코드 목록 조회", notes = "조회조건에 일치하는 책임지역 지역코드 정보를 조회한다.")
    @GetMapping
    public List<WsncRpbAreaCodeMgtDto.SearchRes> getLocalAreaCodes(
        WsncRpbAreaCodeMgtDto.SearchReq dto
    ) {
        return service.getAreaCodes(dto);
    }

    /**
     * 책임지역 지역코드 관리 - 저장
     */
    @ApiOperation(value = "책임지역 지역코드 저장", notes = "책임지역 지역코드를 저장한다.")
    @PostMapping
    public SaveResponse createLocalAreaCodes(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncRpbAreaCodeMgtDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createAreaCodes(dtos))
            .build();
    }

    /**
     * 책임지역 지역코드 관리 - 지역코드 리스트(콤보박스용) 조회
     */
    @ApiOperation(value = "책임지역 지역코드 콤보박스용 목록 조회", notes = "콤보박스용 책임지역 지역코드 List를 조회한다.")
    @GetMapping("/locaraCds")
    public List<WsncRpbAreaCodeMgtDto.LocaraCd> getLocaraCds() {
        return service.getLocaraCds();
    }
}
