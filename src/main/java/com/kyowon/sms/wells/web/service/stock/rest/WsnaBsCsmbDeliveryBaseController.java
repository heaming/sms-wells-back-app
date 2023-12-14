package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbDeliveryBaseService;
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
 * W-SV-U-0295M01 BS소모품 배부기준 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-29
 */

@Api(tags = "[WSNA] BS소모품 배부기준")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/delivery-bases")
public class WsnaBsCsmbDeliveryBaseController {

    private final WsnaBsCsmbDeliveryBaseService service;

    @GetMapping("/item-information")
    @ApiOperation(value = "상품정보 조회", notes = "상품정보 조회")
    public List<SearchItemsRes> getAllItemInformation() {
        return service.getAllItemInformation();
    }

    @GetMapping("/paging")
    @ApiOperation(value = "BS소모품 배부기준 목록 조회", notes = "입력된 데이터에 따른 BS소모품 배부기준 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "goDvCd", value = "발주구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "csmbPdCd", value = "품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "itmKnms", value = "품목코드 리스트", paramType = "query", example = "[WM07101334]"),
        @ApiImplicitParam(name = "csmbPdCdStrt", value = "시작 품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "csmbPdCdEnd", value = "종료 품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "sapMatCdStrt", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "sapMatCdEnd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public PagingResult<SearchRes> getDeliveryBasesPages(SearchReq dto, PageInfo pageInfo) {
        return service.getDeliveryBasesPages(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "BS소모품 배부기준 엑셀 다운로드", notes = "조회조건에 해당하는 BS소모품 배부기준 데이터를 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "goDvCd", value = "발주구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "csmbPdCd", value = "품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "itmKnms", value = "품목코드 리스트", paramType = "query", example = "[WM07101334]"),
        @ApiImplicitParam(name = "csmbPdCdStrt", value = "시작 품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "csmbPdCdEnd", value = "종료 품목코드", paramType = "query", example = "WM07101334"),
        @ApiImplicitParam(name = "sapMatCdStrt", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "sapMatCdEnd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public List<SearchRes> getDeliveryBases(SearchReq dto) {
        return service.getDeliveryBases(dto);
    }

    @PostMapping("/next-month")
    @ApiOperation(value = "BS소모품 배부기준 이월", notes = "당월 기준 직전월 배부기준 자료등록")
    public SaveResponse createDeliveryBasesNextMonth(
        @RequestBody
        @Valid
        CreateCrdovrReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createDeliveryBasesNextMonth(dto))
            .build();
    }

    @PostMapping
    @ApiOperation(value = "BS소모품 배부기준 등록", notes = "입력된 데이터에 따른 BS소모품 배부기준 등록")
    public SaveResponse createDeliveryBase(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createDeliveryBase(dtos))
            .build();
    }

    @GetMapping("/{mngtYm}-{csmbPdCd}")
    @ApiOperation(value = "BS소모품 배부기준 조회", notes = "BS소모품 배부기준 단건 조회")
    public SearchRes getDeliveryBase(
        @PathVariable
        String mngtYm,
        @PathVariable
        String csmbPdCd
    ) {
        return service.getDeliveryBase(mngtYm, csmbPdCd);
    }

    @PutMapping
    @ApiOperation(value = "BS소모품 배부기준 저장", notes = "입력된 데이터에 따른 BS소모품 배부기준을 저장한다.")
    public SaveResponse editDeliveryBase(
        @RequestBody
        @Valid
        @NotEmpty
        List<EditReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.editDeliveryBase(dtos))
            .build();
    }
}
