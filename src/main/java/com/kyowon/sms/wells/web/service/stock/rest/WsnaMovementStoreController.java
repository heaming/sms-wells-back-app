package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaMovementStoreService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 이동입고현황 REST API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/movement-stores")
public class WsnaMovementStoreController {

    private final WsnaMovementStoreService service;

    @ApiOperation(value = "이동입고현황 조회", notes = "조회조건에 해당하는 이동입고 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNoM", value = "출고창고번호마스터", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "ostrWareNoD", value = "출고창고번호디테일", paramType = "query"),

    })
    @GetMapping()
    public List<SearchRes> getMovementStores(
        @Valid
        SearchReq dto
    ) {
        return service.getMovementStores(dto);
    }

    @ApiOperation(value = "이동입고현황 엑셀 다운로드", notes = "조회조건에 해당하는 이동입고 현황을 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNoM", value = "출고창고번호마스터", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "ostrWareNoD", value = "출고창고번호디테일", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getMovementStoresExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMovementStoresExcelDownload(dto);
    }

    //TODO: 확인필요
    @ApiOperation(value = "이동입고 관리 조회", notes = "조회조건에 해당하는 이동입고 관리 페이지를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
    })
    @GetMapping("/management")
    public PagingResult<MovementRes> getMovementStoresMngt(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getMovementStrIzs(dto, pageInfo);
    }

    //TODO: 확인필요
    @ApiOperation(value = "이동입고관리 엑셀 다운로드", notes = "조회조건에 해당하는 이동입고 현황을 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
    })
    @GetMapping("/management/excel-download")
    public List<MovementRes> getMngtExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMovementStrIzsExcelDownload(dto);
    }

    @ApiOperation(value = "이동입고 등록 조회", notes = "조회조건에 해당하는 이동입고 등록 페이지를  한다.")
    @ApiImplicitParams(value = {

    })
    @GetMapping("/registration")
    public PagingResult<MovementOstrMngtRes> getMovementStoresReg(
        MovementOstrMngtReq dto,
        PageInfo pageInfo
    ) {

        return service.getMovementStoresReg(dto, pageInfo);
    }

    @ApiOperation(value = "이관입고등록 confirm", notes = "품목입고내역,품목출고내역,고객서비스품목재고내역 data를 update 한다.")
    @PutMapping("/registration")
    public int saveStrMovementConfrim(
        @RequestBody
        @NotEmpty
        List<MovementStrSaveReq> list
    ) {
        return service.saveStrMovementConfrim(list);
    }

    @ApiOperation(value = "이동입고 등록 엑셀 다운로드", notes = "조회조건에 해당하는 이동입고 등록을 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {

    })
    @GetMapping("/registration/excel-download")
    public List<MovementOstrMngtRes> getMovementStoresRegExcelDownload(
        MovementOstrMngtReq dto
    ) {
        return service.getMovementStoresReg(dto);
    }

    @GetMapping("/strware-monthly-end")
    public int getStrWareMonthlyClosed(warehouseMonthlyReq dto) {
        return service.getStrWareMonthlyClosed(dto);
    }

    @ApiOperation(value = "이관입고 등록 삭제", notes = "등록된 이관입고 내역을 삭제한다.")
    @DeleteMapping("/registration")
    public SaveResponse removeStrMovement(
        @RequestBody
        @NotEmpty
        List<MovementStrSaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(this.service.removeMovement(dtos))
            .build();
    }

}
