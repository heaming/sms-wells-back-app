package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.service.WsnaIndividualWareOstrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

@Api(tags = "[] 개인창고출고관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/individual-ware-ostrs")
public class WsnaIndividualWareOstrController {

    private final WsnaIndividualWareOstrService service;

    @ApiOperation(value = "개인창고출고관리 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getIndividualWareOstrs(
        @Valid
        SearchReq dto
    ) {
        return service.getIndividualWareOstrs(dto);
    }

    @ApiOperation(value = "개인창고출고관리 물류출고창고 조회.", notes = "조회조건인 출고창고에 해당하는 물류창고 조회")
    @GetMapping("logistic")
    public List<LogisticRes> getLogistic(
        LogisticReq dto
    ) {
        return service.getLogistic(dto);
    }

    @ApiOperation(value = "개인창고출고관리 품목 조회.", notes = "조회조건인 품목구분에 해당하는 상품을 조회한다.")
    @GetMapping("filter-items")
    public List<ItmRes> getItemKndCode(
        ItmReq dto
    ) {
        return service.getItemKndCode(dto);
    }

    @ApiOperation(value = "개인창고출고관리 출고요청 저장", notes = "선택한 품목을 출고요청을 한다.")
    @PostMapping
    public SaveResponse createIndividualWareOstrs(
        @RequestBody
        @Valid
        List<CreateReq> list
    ){
        return SaveResponse.builder()
            .processCount(service.createIndividualWareOstr(list))
            .build();

    }
    //    @ApiOperation(value = "개인창고출고관리 엑셀 다운로드", notes = "")
    //    @ApiImplicitParams(value = {
    //        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    //    })
    //    @GetMapping("/excel-download")
    //    public List<SearchRes> getIndividualWareOstrsForExcelDownload(
    //        @Valid
    //        SearchReq dto
    //    ) {
    //        return service.getIndividualWareOstrsForExcelDownload(dto);
    //    }
}
