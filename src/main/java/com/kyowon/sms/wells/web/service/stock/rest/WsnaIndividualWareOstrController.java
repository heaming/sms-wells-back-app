package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaIndividualWareOstrService;
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
 * W-SV-U-0192M01 개인창고 출고 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-19
 */

@Api(tags = "[WSNA] 개인창고출고관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/individual-ware-ostrs")
public class WsnaIndividualWareOstrController {

    private final WsnaIndividualWareOstrService service;

    @GetMapping("/out-of-storage-wares")
    @ApiOperation(value = "출고창고 조회", notes = "출고창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202212", required = true)
    })
    public List<WsnzWellsCodeWareHouseDvo> getIndividualOstrWares(@RequestParam(name = "apyYm")
    String apyYm) {
        return this.service.getIndividualOstrWares(apyYm);
    }

    @GetMapping("/products")
    @ApiOperation(value = "품목 조회", notes = "품목을 조회한다.")
    public List<SearchPdRes> getIndividualOstrWares() {
        return this.service.getIndividualProducts();
    }

    @GetMapping("/storage-wares")
    @ApiOperation(value = "입고창고 조회", notes = "입고창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "31", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "1", required = true),
        @ApiImplicitParam(name = "hgrStrWareNo", value = "상위입고창고번호", paramType = "query", example = "300001")
    })
    public List<WsnzWellsCodeWareHouseDvo> getQomAsnStrWares(SearchWareReq dto) {
        return this.service.getIndividualStrWares(dto);
    }

    @GetMapping("/paging")
    @ApiOperation(value = "개인창고 출고관리 페이징 조회", notes = "개인창고 출고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]"),
        @ApiImplicitParam(name = "totOutQty", value = "총출고", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "osrtDt", value = "출고일자", paramType = "query", example = "20230723"),
        @ApiImplicitParam(name = "hgrStrWareNo", value = "상위입고창고번호", paramType = "query", example = "300001", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public PagingResult<WsnaIndividualWareOstrDvo> getIndividualWareOstrsPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getIndividualWareOstrsPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "개인창고 출고관리 엑셀 다운로드", notes = "조회조건에 일치하는 개인창고 출고 데이터를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]"),
        @ApiImplicitParam(name = "totOutQty", value = "총출고", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "osrtDt", value = "출고일자", paramType = "query", example = "20230723"),
        @ApiImplicitParam(name = "hgrStrWareNo", value = "상위입고창고번호", paramType = "query", example = "300001", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public List<WsnaIndividualWareOstrDvo> getIndividualWareOstrsExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getIndividualWareOstrsExcelDownload(dto);
    }

    @PostMapping
    @ApiOperation(value = "개인창고 출고관리 저장", notes = "개인창고 출고관리 데이터를 저장한다.")
    public SaveResponse saveIndividualWareOstrs(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveIndividualWareOstrs(dtos)).build();
    }

}
