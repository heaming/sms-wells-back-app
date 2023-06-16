package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialsItemGradeConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialsItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialsItemGradeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0115M01 AS자재 품목등급관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-15
 */

@Api(tags = "[WSNA] AS자재 품목등급관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-materials-item-grade")
public class WsnaAsMaterialsItemGradeController {

    private final WsnaAsMaterialsItemGradeConverter converter;

    private final WsnaAsMaterialsItemGradeService service;

    /**
     * AS자재 품목등급관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    @GetMapping("/paging")
    @ApiOperation(value = "AS자재 품목등급관리 페이징 조회", notes = "AS자재 품목등급을 조회한다.")
    public PagingResult<WsnaAsMaterialsItemGradeDto.SearchRes> getAsMaterialsItemGradePages(@Valid
    WsnaAsMaterialsItemGradeDto.SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getAsMaterialsItemGradePages(dto, pageInfo);
    }

    /**
     * AS자재 품목등급 데이터 생성
     * @param dto   (필수) 품목등급 데이터 생성 dto
     * @return
     * @throws Exception
     */
    @PostMapping("/item-grades")
    @ApiOperation(value = "AS자재 품목등급관리 데이터 생성", notes = "AS자재 품목등급 데이터를 생성한다.")
    public SaveResponse createAsMaterialsItemGrades(
        @RequestBody
        @Valid
        WsnaAsMaterialsItemGradeDto.CreateReq dto
    ) throws Exception {

        WsnaAsMaterialsItemGradeDvo dvo = this.converter.mapCreateReqToWsnaAsMaterialsItemGradeDvo(dto);

        return SaveResponse.builder().processCount(this.service.createAsMaterialsItemGrade(dvo)).build();
    }

    /**
     * AS자재 품목등급 데이터 저장
     * @param dtos  (필수) 변경된 데이터 리스트
     * @return
     * @throws Exception
     */
    @PostMapping
    @ApiOperation(value = "AS자재 품목등급관리 데이터 저장", notes = "AS자재 품목등급 데이터를 저장한다.")
    public SaveResponse saveAsMaterialsItemGrades(
        @RequestBody
        @Valid
        @NotEmpty
        List<WsnaAsMaterialsItemGradeDto.SaveReq> dtos
    ) throws Exception {

        List<WsnaAsMaterialsItemGradeDvo> dvos = this.converter.mapAllSaveReqToWsnaAsMaterialsItemGradeDvo(dtos);

        return SaveResponse.builder().processCount(this.service.saveAsMaterialsItemGrades(dvos)).build();
    }

}
