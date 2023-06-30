package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemOrderQuantityDto.SearchReq;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantityDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaItemOrderQuantityService;
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
 * W-SV-U-0296M01 BS자재 발주수량 산출 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-29
 */

@Api(tags = "[WSNA] BS자재 발주수량 산출")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-order-quantity")
public class WsnaItemOrderQuantityController {

    private final WsnaItemOrderQuantityService service;

    @GetMapping("/paging")
    @ApiOperation(value = "BS자재 발주수량 산출 페이징 조회", notes = "BS자재 발주수량 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrYm", value = "조회년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public PagingResult<WsnaItemOrderQuantityDvo> getItemOrderQuantityPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {

        return this.service.getItemOrderQuantityPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "BS자재 발주수량 산출 엑셀 다운로드", notes = "조회조건에 일치하는 BS자재 발주수량 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrYm", value = "조회년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목상품코드 리스트", paramType = "query", example = "[WM07104077]"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public List<WsnaItemOrderQuantityDvo> getItemOrderQuantityExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getItemOrderQuantityExcelDownload(dto);
    }

}
