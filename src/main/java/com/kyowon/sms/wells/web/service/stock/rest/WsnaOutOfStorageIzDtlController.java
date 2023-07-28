package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageIzDtlService;
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
 * W-SV-U-0144M01 출고내역 상세 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-28
 */

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-iz-dtls")
@Api(tags = "[WSNA] 출고내역상세 관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageIzDtlController {

    private final WsnaOutOfStorageIzDtlService service;

    @GetMapping("/products")
    @ApiOperation(value = "출고내역 상세 관리 품목 조회", notes = "품목을 조회한다.")
    public List<SearchPdRes> getProducts() {
        return this.service.getProducts();
    }

    @GetMapping("/paging")
    @ApiOperation(value = "출고내역상세 관리 페이징 조회", notes = "출고 상세내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "1", required = true),
        @ApiImplicitParam(name = "ostrHgrWareNo", value = "상위출고창고번호", paramType = "query", example = "100002"),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100009"),
        @ApiImplicitParam(name = "strWareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "strHgrWareNo", value = "상위입고창고번호", paramType = "query", example = "300001"),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급코드", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y")
    })
    public PagingResult<SearchRes> getOutOfStorageIzDtlsPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo
    ) {
        return this.service.getOutOfStorageIzDtlsPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "출고내역상세 관리 엑셀 다운로드", notes = "조회조건에 일치하는 출고 상세 데이터를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "1", required = true),
        @ApiImplicitParam(name = "ostrHgrWareNo", value = "상위출고창고번호", paramType = "query", example = "100002"),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100009"),
        @ApiImplicitParam(name = "strWareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "strHgrWareNo", value = "상위입고창고번호", paramType = "query", example = "300001"),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급코드", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y")
    })
    public List<SearchRes> getOutOfStorageIzDtlsExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getOutOfStorageIzDtlsExcelDownload(dto);
    }

}
