package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedReleaseScheduleService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 모종 출고 예정리스트 조회")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seed-release-schedules")
public class WsnaSeedReleaseScheduleController {

    private final WsnaSeedReleaseScheduleService service;

    @GetMapping("/paging")
    @ApiOperation(value = "모종 출고 예정리스트 페이징 조회", notes = "모종 출고 예정리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svBizHclsfCd", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dtTpCd", value = "일자유형코드", paramType = "query", example = "1", required = true),
        @ApiImplicitParam(name = "dayOfWeek", value = "요일코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "refriDivCd", value = "유/무상구분코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "sppDvCd", value = "배송구분코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "fshProcsCd", value = "완료처리코드", paramType = "query", example = "00"),
        @ApiImplicitParam(name = "pkgDvCd", value = "패키지구분코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "ostrYn", value = "출고여부", paramType = "query", example = "Y")
    })
    public PagingResult<SearchRes> getSeedReleaseSchedulesPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {

        return this.service.getSeedReleaseSchedulesPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "모종 출고 예정리스트 엑셀 다운로드", notes = "조회조건에 일치하는 모종 출고 예정리스트 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svBizHclsfCd", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dtTpCd", value = "일자유형코드", paramType = "query", example = "1", required = true),
        @ApiImplicitParam(name = "dayOfWeek", value = "요일코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230703", required = true),
        @ApiImplicitParam(name = "refriDivCd", value = "유/무상구분코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "sppDvCd", value = "배송구분코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "fshProcsCd", value = "완료처리코드", paramType = "query", example = "00"),
        @ApiImplicitParam(name = "pkgDvCd", value = "패키지구분코드", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "ostrYn", value = "출고여부", paramType = "query", example = "Y")
    })
    public List<SearchRes> getSeedReleaseSchedulesExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getSeedReleaseSchedulesExcelDownload(dto);
    }

    @PutMapping
    @ApiOperation(value = "모종 출고 예정리스트 저장", notes = "모종 출고 예정리스트 데이터를 저장한다.")
    public SaveResponse editSeedReleaseSchedules(
        @RequestBody
        @Valid
        @NotEmpty
        List<EditReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(this.service.editSeedReleaseSchedules(dtos)).build();
    }

    @PostMapping
    @ApiOperation(value = "모종 출고 예정리스트 출고 확정", notes = "모종 출고 예정리스트 데이터를 출고 확정한다.")
    public SaveResponse createSeedReleaseSchedules(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(this.service.createSeedReleaseSchedulesForCnfm(dtos)).build();
    }

}
