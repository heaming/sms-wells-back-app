package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedingSowPlanService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0130M01 모종 파종 예정리스트 조회 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-06
 */

@Api(tags = "[WSNA] 모종 파종 예정리스트 조회")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seeding-sow-plans")
public class WsnaSeedingSowPlanController {

    private final WsnaSeedingSowPlanService service;

    @GetMapping("/paging")
    @ApiOperation(value = "모종 파종 예정리스트 페이징 조회", notes = "모종 파종 예정리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", example = "2022")
    })
    public PagingResult<SearchRes> getSeedingSowPlansPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getSeedingSowPlansPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "모종 파종 예정리스트 엑셀 다운로드", notes = "조회조건에 일치하는 모종 파종 예정리스트 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query", example = "2022")
    })
    public List<SearchRes> getSeedingSowPlansExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getSeedingSowPlansExcelDownload(dto);
    }

}
