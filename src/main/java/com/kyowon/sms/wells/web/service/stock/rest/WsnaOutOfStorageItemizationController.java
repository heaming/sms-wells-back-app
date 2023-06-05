package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageItemizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-itemizations")
@Api(tags = "[WSNA] 출고관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageItemizationController {

    private final WsnaOutOfStorageItemizationService service;

    @ApiOperation(value = "출고관리 목록 조회", notes = "조회조건에 일치하는 출고관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2"),

    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOutOfStorageItemizations(SearchReq dto, PageInfo pageInfo) {
        return this.service.getOutOfStorageItemizations(dto, pageInfo);
    }

    @ApiOperation(value = "출고관리 목록 엑셀 다운로드", notes = "조회조건에 일치하는 출고관리 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "2"),
    })
    @GetMapping("excel-download")
    public List<SearchRes> getOutOfStorageItemizationExcelDownload(
        SearchReq dto
    ) {
        return this.service.getOutOfStorageItemizationExcelDownload(dto);

    }

}
