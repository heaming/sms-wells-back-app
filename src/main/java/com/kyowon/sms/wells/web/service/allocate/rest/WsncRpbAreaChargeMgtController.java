package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbAreaChargeMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/responsible-area-charges")
@Api(tags = "[WSNC] 책임지역 담당자 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsncRpbAreaChargeMgtController {

    private final WsncRpbAreaChargeMgtService service;

    /**
     * 책임지역 담당자 조회
     * @param dto 조회조건
     * @return 책임지역 담당자 목록
     */
    @ApiOperation(value = "책임지역 담당자 조회", notes = "조회조건에 일치하는 책임지역별 책임담당자, 작업그룹, 서비스센터(지점) 등을 조회한다.")
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
    @GetMapping
    public List<SearchRes> getAreaCharges(SearchReq dto) {
        return this.service.getAreaCharges(dto);
    }

    /**
     * 책임지역 담당자 저장
     * @param dtos 저장할 담당자 배열
     * @return 처리건수
     * @throws Exception
     */
    @ApiOperation(value = "책임지역 담당자 저장", notes = "책임지역별 적용시작일자, 적용종료일자, 담당파트너번호, 예비담당파트너번호1~5 정보를 저장한다.")
    @PostMapping
    public SaveResponse createAreaCharges(
        @Valid
        @RequestBody
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.createAreaCharges(dtos))
            .build();
    }

}
