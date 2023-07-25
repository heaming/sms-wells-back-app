package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

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

@Api(tags = "[WSNA] 정상출고 관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/normal-out-of-storages")
public class WsnaNormalOutOfStorageController {

    private final WsnaNormalOutOfStorageService service;

    @GetMapping("/paging")
    @ApiOperation(value = "정상출고 페이징 조회", notes = "정상출고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "ostrCnfm", value = "출고확정코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고대상창고번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "itmKndCd", value = "출고품목코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareLocaraCd", value = "입고희망일 종료일", paramType = "query", example = ""),
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
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "ostrCnfm", value = "출고확정코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고대상창고번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "itmKndCd", value = "출고품목코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareLocaraCd", value = "입고희망일 종료일", paramType = "query", example = ""),
    })
    public List<SearchRes> excelDownload(@Valid
    SearchReq dto) {
        return this.service.getNormalOutOfStorage(dto);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "정상출고 창고 조회", notes = "정상출고 창고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
    })
    public List<SearchWarehouse> getWarehouses(SearchReq dto) {
        return this.service.getWarehouses(dto);
    }

    @GetMapping("/person-center/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고번호", paramType = "query", example = "", required = true),
    })
    public PagingResult<AskRes> getAskMaterialsHavePss(@Valid
    AskReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getAskMaterialsHavePss(dto, pageInfo);
    }

    @GetMapping("/organization-center/paging")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고번호", paramType = "query", example = "", required = true),
    })
    public PagingResult<CenterRes> getAskMaterialsCenter(@Valid
    AskReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getAskMaterialsCenterPresentState(dto, pageInfo);
    }

    @GetMapping("/detail")
    public PagingResult<DetailRes> getNormalOstrRgsts(@Valid
    DetailReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getNormalOstrRgsts(dto, pageInfo);
    }

    @GetMapping("/detail-remove")
    public PagingResult<DetailRes> removeNormalOstrRgsts(
        DetailReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.removeNormalOstrRgsts(dto, pageInfo);
    }

    @PutMapping("/detail")
    public SaveResponse saveNormalOstrRgsts(
        @RequestBody
        List<CreateReq> list
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveNormalOstrRgsts(list))
            .build();
    }

    @DeleteMapping
    public SaveResponse removeNormalOstrRgsts(
        @RequestBody
        List<CreateReq> list
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeNormalOstrRgsts(list))
            .build();
    }

    @GetMapping("/checked")
    public int getNormalOstrRgstChecked(
        @RequestBody
        CheckedReq dto
    ) {
        return service.getNormalOstrRgstChecked(dto);
    }

    @GetMapping("/standard-ware")
    public StandardWareRes getStandardWareHouse(StandardWareReq dto) {
        return service.getStandardWareHouse(dto);
    }

    @PutMapping("/standard-ware")
    public int saveStandardWareHouse(@RequestBody
    StandardWareReq dto) {
        return service.saveStandardWareHouse(dto);
    }

    @GetMapping("/itm-ostr-ak")
    public SearchItmOstrAkRes getItmOstrAk(SearchItmOstrAkReq dto) {
        return service.getItmOstrAk(dto);
    }
}
