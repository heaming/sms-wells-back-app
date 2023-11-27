package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingByFilterTypeDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaShippingByFilterTypeService;
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
 * W-SV-U-0261M01 필터 종류별 출고내역 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

@Api(tags = "[WSNA] 필터종류별 출고내역")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/shipping-by-filter-types")
public class WsnaShippingByFilterTypeController {

    private final WsnaShippingByFilterTypeService service;

    @GetMapping("/products")
    @ApiOperation(value = "품목 조회", notes = "품목을 조회한다.")
    public List<SearchPdRes> getProducts() {
        return this.service.getShippingByFilterProducts();
    }

    @GetMapping("/paging")
    @ApiOperation(value = "필터 종류별 출고내역 페이징 조회", notes = "필터 종류별 출고내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "방문시작일자", paramType = "query", example = "20230809", required = true),
        @ApiImplicitParam(name = "endDt", value = "방문종료일자", paramType = "query", example = "20230809", required = true),
        @ApiImplicitParam(name = "gbYn", value = "반납여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "업무유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200609"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200898"),
        @ApiImplicitParam(name = "itmGrCd", value = "품목그룹코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "ostrConfDt", value = "수거일자", paramType = "query", example = "20230809")
    })
    public PagingResult<SearchRes> getShippingByFilterTypesPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getShippingByFilterTypesPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "필터 종류별 출고내역 엑셀 다운로드", notes = "조회조건에 해당하는 필터 종류별 출고내역을 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "방문시작일자", paramType = "query", example = "20230809", required = true),
        @ApiImplicitParam(name = "endDt", value = "방문종료일자", paramType = "query", example = "20230809", required = true),
        @ApiImplicitParam(name = "gbYn", value = "반납여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "업무유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200609"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200898"),
        @ApiImplicitParam(name = "itmGrCd", value = "품목그룹코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "ostrConfDt", value = "수거일자", paramType = "query", example = "20230809")
    })
    public List<SearchRes> getShippingByFilterTypesExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getShippingByFilterTypesExcelDownload(dto);
    }

    @PutMapping
    @ApiOperation(value = "필터 종류별 출고내역 저장", notes = "필터 종류별 출고내역을 저장한다.")
    public SaveResponse editShippingByFilterTypes(
        @RequestBody
        @Valid
        @NotEmpty
        List<EditReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.editShippingByFilterTypes(dtos)).build();
    }

}
