package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockAcinspRgstMngtDto.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStockAcinspRgstMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto.DownloadReq;
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
 * W-SV-U-0139M01 월별 재고실사 등록 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.26
 */
@Api(tags = "[WSNA] 월별 재고실사 등록 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-acinp-rgst")
public class WsnaStockAcinspRgstMngtController {

    private final WsnaStockAcinspRgstMngtService service;

    @ApiOperation(value = "월별 재고실사 등록 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "201453"),
        @ApiImplicitParam(name = "useYn", value = "상태구분", paramType = "query", example = "1")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getStockAcinspRgstMngtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getStockAcinspRgstMngtPages(dto, pageInfo);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "월별 재고실사 등록 관리 창고 조회", notes = "월별 재고실사 등록 관리 창고 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20")
    })
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(@Valid
    SearchWareReq dto) {
        return this.service.getWareHouses(dto);
    }

    @ApiOperation(value = "월별 재고실사 등록 관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "201453"),
        @ApiImplicitParam(name = "useYn", value = "상태구분", paramType = "query", example = "1")
    })
    @PostMapping("/bulk-excel-download")
    public void getStockAcinspRgstMngtsForExcelDownload(
        @RequestBody
        DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getStockAcinspRgstMngtsForExcelDownload(req, response);
    }

    @ApiOperation(value = "월별 재고실사 등록 관리 재고적용 버튼클릭 이벤트", notes = "월별 재고실사 등록관리 재고적용 버튼클릭 이벤트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202307", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "pdCd", value = "품목코드", paramType = "query", example = "WM07101301"),
        @ApiImplicitParam(name = "pitmStocAGdQty", value = "시점재고수량", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "cstPitmStocAGdQty", value = "시점재고수량", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "cnfmPitmOstrGapQty", value = "확정시점출고차이수량", paramType = "query", example = "0"),

    })
    @PostMapping("/stoc-apy")
    public SaveResponse saveStockAcinspRgstStocApy(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveStockAcinspRgstStocApy(dtos)).build();

    }

    @ApiOperation(value = "월별 재고실사 등록 관리 저장", notes = "월별 재고실사 등록관리 저장")
    @PostMapping
    public SaveResponse saveStockAcinspRgst(
        @Valid
        @RequestBody
        List<SaveAcinspReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveStockAcinspRgst(dtos)).build();
    }

    @ApiOperation(value = "월별 재고실사 등록 관리 실사확정", notes = "월별 재고실사 등록관리 실사확정")
    @PostMapping("/acinsp-cnfm")
    public SaveResponse saveStockAcinspRgstCnfm(
        @Valid
        @RequestBody
        List<SaveAcinspCnfmReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveStockAcinspRgstCnfm(dtos)).build();
    }

    @ApiOperation(value = "월별 재고실사 등록 관리 실사확정취소", notes = "월별 재고실사 등록관리 실사확정을 취소한다")
    @PostMapping("/stoc-cancel")
    public SaveResponse deleteStockAcinspRgstCancel(
        @Valid
        @RequestBody
        List<SaveCancelReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.deleteStockAcinspRgstCancel(dtos)).build();

    }

    @ApiOperation(value = "웰스매니저 재고조정등록 화면 조회 취소", notes = "재고조정등록 화면에서 차이수량 및 실사완료 처리를 취소한다.")
    @DeleteMapping
    public SaveResponse removeManagerStockControl(
        @RequestBody
        List<RemoveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeManagerStockControl(dtos)).build();
    }

}
