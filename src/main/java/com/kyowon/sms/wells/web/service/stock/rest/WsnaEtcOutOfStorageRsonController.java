package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaEtcOutOfStorageRsonService;
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
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songtaesung
 * @since 2023.01.13
 */

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/etc-out-of-storage-resons")
@Api(tags = "[WSNA] 기타출고 사유내역 REST API")
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageRsonController {

    private final WsnaEtcOutOfStorageRsonService service;

    @ApiOperation(value = "기타출고 사유내역 조회", notes = "조회조건에 일치하는 기타출고 사유내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "출고시작일", paramType = "query", example = "20230101", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "출고종료일", paramType = "query", example = "20230117"),
        @ApiImplicitParam(name = "bilRsonCd", value = "청구사유", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "pdGdCd", value = "등급", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareNoM", value = "창고번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareNoD", value = "창고번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "startItemCd", value = "시작품목코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "endItemCd", value = "종료품목코드", paramType = "query", example = "", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getEtcOutOfStorageRsons(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getEtcOutOfStorageRsons(dto, pageInfo);
    }

    @ApiOperation(value = "기타출고 사유내역 엑셀다운로드", notes = "조회조건에 일치하는 기타출고 사유내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "출고시작일", paramType = "query", example = "20230101", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "출고종료일", paramType = "query", example = "20230117"),
        @ApiImplicitParam(name = "bilRsonCd", value = "청구사유", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "pdGdCd", value = "등급", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "서비스센터", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "startItemCd", value = "시작품목코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "endItemCd", value = "종료품목코드", paramType = "query", example = "", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getEtcOutOfStorageRsonsForExcelDownload(SearchReq dto) {
        return this.service.getEtcOutOfStorageRsonsForExcelDownload(dto);
    }

}
