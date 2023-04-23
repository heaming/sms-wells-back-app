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
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 정상출고 관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/normal-outofstorages")
public class WsnaNormalOutOfStorageController {

    private final WsnaNormalOutOfStorageService service;

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
    @GetMapping
    public PagingResult<SearchRes> getNormalOutOfStorage(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getNormalOutOfStorage(dto, pageInfo);
    }

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
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {
        return service.getNormalOutOfStorage(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strHopDtStr", value = "입고희망일 시작일", paramType = "query", example = "20230314", required = true),
        @ApiImplicitParam(name = "strHopDtEnd", value = "입고희망일 종료일", paramType = "query", example = "20230314", required = true),
    })
    @GetMapping("/warehouses")
    public List<SearchWarehouse> getWarehouses(SearchReq dto) {
        return service.getWarehouses(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고번호", paramType = "query", example = "", required = true),
    })
    @GetMapping("/person-center/paging")
    public PagingResult<AskRes> getAskMaterialsHavePss(
        AskReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAskMaterialsHavePss(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고번호", paramType = "query", example = "", required = true),
    })
    @GetMapping("/organization-center/paging")
    public PagingResult<CenterRes> getAskMaterialsCenter(
        AskReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAskMaterialsCenterPresentState(dto, pageInfo);
    }

    @GetMapping("/detail")
    public PagingResult<DetailRes> getNormalOutOfStoragesDetails(
        DetailReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getNormalOutOfStoragesDetails(dto, pageInfo);
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

    @GetMapping("/checked")
    public int getNormalOstrRgstChecked(
        @RequestBody
        CheckedReq dto
    ) {
        return service.getNormalOstrRgstChecked(dto);
    }

    @PutMapping("/standard-ware")
    public int saveStandardWareHouse(StandardWareReq dto) {
        return service.saveStandardWareHouse(dto);
    }

    @GetMapping("/standard-ware")
    public StandardWareRes getStandardWareHouse(StandardWareReq dto) {
        return service.getStandardWareHouse(dto);
    }
}
