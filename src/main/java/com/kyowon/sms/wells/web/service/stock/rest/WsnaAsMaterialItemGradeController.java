package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialItemGradeConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradeDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialItemGradeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-material-item-grades")
public class WsnaAsMaterialItemGradeController {

    private final WsnaAsMaterialItemGradeConverter converter;

    private final WsnaAsMaterialItemGradeService service;

    /**
     * 창고리스트 조회
     * @param dto
     * @return
     */
    @GetMapping("/ware-houses")
    @ApiOperation(value = "AS자재 품목등급관리 창고 조회", notes = "AS자재 품목등급관리 창고 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20")
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(@Valid
    WsnaAsMaterialItemGradeDto.SearchWareReq dto) {
        return this.service.getWareHouses(dto);
    }

    /**
     * AS자재 품목등급관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    @GetMapping("/paging")
    @ApiOperation(value = "AS자재 품목등급관리 페이징 조회", notes = "AS자재 품목등급을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "itmMngtGdCd", value = "품목관리등급", paramType = "query", example = "S"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "6", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07100214"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public PagingResult<WsnaAsMaterialItemGradeDto.SearchRes> getAsMaterialsItemGradePages(@Valid
    WsnaAsMaterialItemGradeDto.SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getAsMaterialItemGradePages(dto, pageInfo);
    }

    /**
     * AS자재 품목등급관리 엑셀 다운로드
     * @param dto
     * @return
     */
    @GetMapping("/excel-download")
    @ApiOperation(value = "AS자재 품목등급관리 엑셀 다운로드", notes = "조회조건에 일치하는 AS자재 품목등급 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "itmMngtGdCd", value = "품목관리등급", paramType = "query", example = "S"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "6", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07100214"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public List<WsnaAsMaterialItemGradeDto.SearchRes> getAsMaterialsItemGradeExcelDownload(@Valid
    WsnaAsMaterialItemGradeDto.SearchReq dto) {
        return this.service.getAsMaterialItemGradesExcelDownload(dto);
    }

    /**
     * AS자재 품목등급 데이터 생성 중복 체크
     * @param dto
     * @return
     */
    @GetMapping("/duplication-check")
    @ApiOperation(value = "AS자재 품목등급관리 데이터 생성 중복 체크", notes = "AS자재 품목등급 데이터 생성 중복 체크를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6", required = true)
    })
    public String getAsMaterialsItemGradePages(@Valid
    WsnaAsMaterialItemGradeDto.CreateReq dto) {
        WsnaAsMaterialItemGradeDvo dvo = this.converter.mapCreateReqToWsnaAsMaterialItemGradeDvo(dto);

        return this.service.getCreateAsMaterialDuplication(dvo);
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
        WsnaAsMaterialItemGradeDto.CreateReq dto
    ) throws Exception {

        WsnaAsMaterialItemGradeDvo dvo = this.converter.mapCreateReqToWsnaAsMaterialItemGradeDvo(dto);

        return SaveResponse.builder().processCount(this.service.createAsMaterialItemGrades(dvo)).build();
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
        List<WsnaAsMaterialItemGradeDto.SaveReq> dtos
    ) throws Exception {

        List<WsnaAsMaterialItemGradeDvo> dvos = this.converter.mapAllSaveReqToWsnaAsMaterialItemGradeDvo(dtos);

        return SaveResponse.builder().processCount(this.service.saveAsMaterialItemGrades(dvos)).build();
    }

}
