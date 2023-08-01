package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDetailDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaNormalOutOfStorageService;
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
 * W-SV-U-0142M01 정상출고 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-31
 */

@Api(tags = "[WSNA] 정상출고 관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/normal-out-of-storages")
public class WsnaNormalOutOfStorageController {

    private final WsnaNormalOutOfStorageService service;

    @GetMapping("/ware-houses")
    @ApiOperation(value = "정상출고 창고 조회", notes = "정상출고 창고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "기준년월", paramType = "query", example = "202303", required = true),
    })
    public List<SearchWarehouse> getWarehouses(@RequestParam(name = "apyYm")
    String apyYm) {
        return this.service.getWarehouses(apyYm);
    }

    @GetMapping("/paging")
    @ApiOperation(value = "정상출고 페이징 조회", notes = "정상출고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고대상창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "출고품목코드", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "ostrCnfm", value = "출고확정코드", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "1", required = true)
    })
    public PagingResult<SearchRes> getNormalOutOfStorage(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo
    ) {
        return this.service.getNormalOutOfStorage(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "정상출고 데이터 엑셀 다운로드", notes = "조회조건에 일치하는 정상출고 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고대상창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "출고품목코드", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "ostrCnfm", value = "출고확정코드", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "1", required = true)
    })
    public List<SearchRes> excelDownload(@Valid
    SearchReq dto) {
        return this.service.getNormalOutOfStorage(dto);
    }

    @GetMapping("/itm-ostr-ak")
    @ApiOperation(value = "정상출고 등록 정보 조회", notes = "출고요청번호에 해당하는 정상출고 등록 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrAkNo", value = "출고요청번호", paramType = "query", example = "310200812300000011", required = true),
        @ApiImplicitParam(name = "ostrAkSn", value = "출고요청일련번호", paramType = "query", example = "1", required = true)
    })
    public SearchItmOstrAkRes getItmOstrAk(@Valid
    SearchItmOstrAkReq dto) {
        return this.service.getItmOstrAk(dto);
    }

    @GetMapping("/standard-ware")
    @ApiOperation(value = "표준창고 조회", notes = "표준창고 여부를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "적용년월", paramType = "query", example = "202307", required = true),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "201698", required = true)
    })
    public StandardWareRes getStandardWareHouse(@Valid
    StandardWareReq dto) {
        return this.service.getStandardWareHouse(dto);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "정상출고 상세 정보 조회", notes = "정상출고 상세 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310", required = true),
        @ApiImplicitParam(name = "ostrAkNo", value = "출고요청번호", paramType = "query", example = "310200812300000009", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200043", required = true),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "stckStdGb", value = "표준창고 적용여부", paramType = "query", example = "Y", required = true),
        @ApiImplicitParam(name = "rgstDt", value = "출고요청등록일자", paramType = "query", example = "20230314", required = true)
    })
    public PagingResult<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgsts(@Valid
    DetailReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getNormalOstrRgsts(dto, pageInfo);
    }

    @GetMapping("/detail/excel-download")
    @ApiOperation(value = "정상출고 상세 정보 조회 엑셀 다운로드", notes = "정상출고 상세 정보를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = "310", required = true),
        @ApiImplicitParam(name = "ostrAkNo", value = "출고요청번호", paramType = "query", example = "310200812300000009", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200043", required = true),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "stckStdGb", value = "표준창고 적용여부", paramType = "query", example = "Y", required = true),
        @ApiImplicitParam(name = "rgstDt", value = "출고요청등록일자", paramType = "query", example = "20230314", required = true)
    })
    public List<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsExcelDownload(@Valid
    DetailReq dto
    ) {
        return this.service.getNormalOstrRgstsExcelDownload(dto);
    }

    @GetMapping("/detail-remove")
    @ApiOperation(value = "정상출고 상세 정보 조회(삭제용)", notes = "정상출고 상세 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "222", required = true),
        @ApiImplicitParam(name = "itmOstrNo", value = "품목출고요청번호", paramType = "query", example = "222202210060000001", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200043", required = true),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "stckStdGb", value = "표준창고 적용여부", paramType = "query", example = "Y", required = true),
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230314", required = true)
    })
    public PagingResult<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsForRemove(
        @Valid
        DetailRemoveReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getNormalOstrRgstsForRemove(dto, pageInfo);
    }

    @GetMapping("/detail-remove/excel-download")
    @ApiOperation(value = "정상출고 상세 정보 엑셀 다운로드(삭제용)", notes = "정상출고 상세 정보를 엑셀 다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "222", required = true),
        @ApiImplicitParam(name = "itmOstrNo", value = "품목출고요청번호", paramType = "query", example = "222202210060000001", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200043", required = true),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "stckStdGb", value = "표준창고 적용여부", paramType = "query", example = "Y", required = true),
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230314", required = true)
    })
    public List<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsExcelDownloadForRemove(
        @Valid
        DetailRemoveReq dto
    ) {
        return this.service.getNormalOstrRgstsExcelDownloadForRemove(dto);
    }

    @PutMapping("/standard-ware")
    @ApiOperation(value = "표준 창고 적용", notes = "표준 창고여부를 저장한다.")
    public int editStandardWareHouse(
        @RequestBody
        @Valid
        StandardWareReq dto
    ) {
        return this.service.editStandardWareHouse(dto);
    }

    @GetMapping("/ware-close-yn")
    @ApiOperation(value = "창고 마감여부 조회", notes = "창고 마감여부를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고창고번호", paramType = "query", example = "200017", required = true),
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230314", required = true)
    })
    public String getWareCloseYn(@RequestParam(name = "ostrDt")
    String ostrDt, @RequestParam(name = "ostrOjWareNo")
    String ostrOjWareNo) {
        return this.service.getWareCloseYn(ostrDt, ostrOjWareNo);
    }

    @DeleteMapping("/detail")
    @ApiOperation(value = "정상출고 삭제", notes = "정상출고 데이터를 삭제한다.")
    public SaveResponse removeNormalOstrRgsts(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.removeNormalOstrRgsts(dtos))
            .build();
    }

    @GetMapping("/person-center/paging")
    @ApiOperation(value = "개인창고 요청 자재 보유현황 페이징 조회", notes = "개인창고 요청 자재 보유현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM01100001", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "201784", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "2", required = true)
    })
    public PagingResult<AskRes> getAskMaterialsHavePss(@Valid
    AskReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getAskMaterialsHavePss(dto, pageInfo);
    }

    @GetMapping("/organization-center/paging")
    @ApiOperation(value = "조직창고 요청 자재 보유현황 페이징 조회", notes = "조직창고 요청 자재 보유현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM01100001", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "201784", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "2", required = true)
    })
    public PagingResult<CenterRes> getAskMaterialsCenter(@Valid
    CenterReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getAskMaterialsCenterPresentState(dto, pageInfo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "정상출고 확정", notes = "정상출고 데이터를 확정한다.")
    public SaveResponse saveNormalOstrRgsts(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveNormalOstrRgsts(dtos))
            .build();
    }

    @GetMapping("/confirm-count")
    @ApiOperation(value = "확정 건수 조회", notes = "확정 건수를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "list", value = "출고요청리스트", paramType = "query", example = "[]", required = true)
    })
    public int getOstrCnfmCount(@Valid
    CheckReq dto
    ) {
        return this.service.getOstrCnfmCount(dto);
    }

}
