package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaQomAsnService;
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
 * W-SV-U-0190M01, W-SV-U-0191M01 개인창고, 독립창고 물량배정 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

@Api(tags = "[WSNA] 물량배정")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/qom-asn")
public class WsnaQomAsnController {

    private final WsnaQomAsnService service;

    @GetMapping("/out-of-storage-wares")
    @ApiOperation(value = "물량배정 출고창고 조회", notes = "물량배정 출고창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202212", required = true)
    })
    public List<WsnzWellsCodeWareHouseDvo> getQomAsnOstrWares(@RequestParam(name = "apyYm")
    String apyYm) {
        return this.service.getQomAsnOstrWares(apyYm);
    }

    @GetMapping("/storage-wares")
    @ApiOperation(value = "물량배정 입고창고 조회", notes = "물량배정 입고창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "31", required = true),
    })
    public List<WsnzWellsCodeWareHouseDvo> getQomAsnStrWares(@Valid
    SearchWareReq dto) {
        return this.service.getQomAsnStrWares(dto);
    }

    @GetMapping("/exist-check")
    @ApiOperation(value = "물량배정 건수 조회", notes = "물량배정 데이터 건수를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "입고창고세부구분코드", paramType = "query", example = "31", required = true)
    })
    public String getQomAsnExistCheck(@Valid
    SearchReq dto) {
        return this.service.getQomAsnExistCheck(dto);
    }

    @GetMapping("/individual-wares/paging")
    @ApiOperation(value = "개인창고 물량배정 페이징 조회", notes = "개인창고 물량배정 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "입고창고세부구분코드", paramType = "query", example = "31", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031")
    })
    public PagingResult<SearchRes> getQomAsnsForIndividual(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getQomAsnsForIndividual(dto, pageInfo);
    }

    @GetMapping("/individual-wares/excel-download")
    @ApiOperation(value = "개인창고 물량배정 엑셀 다운로드", notes = "조회조건에 일치하는 개인창고 물량배정 데이터를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "입고창고세부구분코드", paramType = "query", example = "31", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031")
    })
    public List<SearchRes> getQomAsnsExcelDownloadForIndividual(@Valid
    SearchReq dto) {
        return this.service.getQomAsnsExcelDownloadForIndividual(dto);
    }

    @PostMapping("/individual-wares")
    @ApiOperation(value = "개인창고 물량배정 데이터 생성", notes = "개인창고 물량배정 데이터를 생성한다.")
    public SaveResponse createQomAsnIndividualWares(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.createQomAsnIndividualWares(dto)).build();
    }

    @PostMapping("/independence-wares")
    @ApiOperation(value = "독립창고 물량배정 데이터 생성", notes = "독립창고 물량배정 데이터를 생성한다.")
    public SaveResponse createQomAsnIndependenceWares(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.createQomAsnIndependenceWares(dto)).build();
    }

    @GetMapping("/independence-wares/paging")
    @ApiOperation(value = "독립창고 물량배정 페이징 조회", notes = "독립창고 물량배정 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "입고창고세부구분코드", paramType = "query", example = "31", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031")
    })
    public PagingResult<SearchRes> getQomAsnsForIndependence(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getQomAsnsForIndependence(dto, pageInfo);
    }

    @GetMapping("/independence-wares/excel-download")
    @ApiOperation(value = "독립창고 물량배정 엑셀 다운로드", notes = "조회조건에 일치하는 독립창고 물량배정 데이터를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", example = "202305", required = true),
        @ApiImplicitParam(name = "cnt", value = "회차", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "100002", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "입고창고세부구분코드", paramType = "query", example = "31", required = true),
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "300031")
    })
    public List<SearchRes> getQomAsnsExcelDownloadForIndependence(@Valid
    SearchReq dto) {
        return this.service.getQomAsnsExcelDownloadForIndependence(dto);
    }

    @PutMapping("/ware-renewal")
    @ApiOperation(value = "물량배정 창고갱신", notes = "물량배정 창고번호를 갱신한다.")
    public SaveResponse editQomAsnForWareRenewalDvo(
        @RequestBody
        @Valid
        EditReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.editQomAsnForWareRenewalDvo(dto)).build();
    }

}
