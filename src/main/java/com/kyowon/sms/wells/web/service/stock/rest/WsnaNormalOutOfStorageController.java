package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchWarehouse;
import com.kyowon.sms.wells.web.service.stock.service.WsnaNormalOutOfStorageService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/normal-outofstorages")
@Api(tags = "[WSNA] 정상출고 관리 REST API")
@RequiredArgsConstructor
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

}
