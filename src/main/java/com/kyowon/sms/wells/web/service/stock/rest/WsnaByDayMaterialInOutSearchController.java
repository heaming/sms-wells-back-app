package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaByDayMaterialInOutSearchDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaByDayMaterialInOutSearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/by-day-material-inout-search")
@Api(tags = "[WSNA] 일자별 자재 입출고 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnaByDayMaterialInOutSearchController {

    private final WsnaByDayMaterialInOutSearchService service;

    @ApiOperation(value = "일자별 자재 입출고 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareDvCd", value = "출고창고분류", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "hgrWareNo", value = "출고창고", paramType = "query", example = "200017"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목그룹", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목코드", paramType = "query", example = "WM01100808"),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230814"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230814"),
        @ApiImplicitParam(name = "ostrDtrmYn", value = "출고확정여부", paramType = "query", example = "1"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getByDayMaterialInOutSearchPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getByDayMaterialInOutSearchPages(dto, pageInfo);
    }

    @ApiOperation(value = "일자별 자재 입출고 관리 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareDvCd", value = "출고창고분류", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "hgrWareNo", value = "출고창고", paramType = "query", example = "200017"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목그룹", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목코드", paramType = "query", example = "WM01100808"),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자 From", paramType = "query", example = "20230814"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자 To", paramType = "query", example = "20230814"),
        @ApiImplicitParam(name = "ostrDtrmYn", value = "출고확정여부", paramType = "query", example = "1"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getByDayMaterialInOutSearchExcelDownload(SearchReq dto) {
        return service.getByDayMaterialInOutSearchExcelDownload(dto);
    }

    @ApiOperation(value = "출고요청내역 삭제")
    @DeleteMapping
    public SaveResponse deleteOutOfStorageAskItemization(
        @Valid
        @RequestBody
        @NotEmpty
        List<DeleteReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.deleteOutOfStorageAskItemization(dtos))
            .build();
    }
}
