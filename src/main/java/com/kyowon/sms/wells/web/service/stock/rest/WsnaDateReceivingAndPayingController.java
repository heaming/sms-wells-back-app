package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.service.WsnaDateReceivingAndPayingService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaDateReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 일자별수불현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/date-receivings")
public class WsnaDateReceivingAndPayingController {

    private final WsnaDateReceivingAndPayingService service;

    @ApiOperation(value = "일자별수불현황 페이징 조회", notes = "파라미터로 넘어온 데이터로 일자별 수불현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getDateReceivingAndPayings(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getDateReceivingAndPayings(dto, pageInfo);
    }

    @ApiOperation(value = "일자별수불현황 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getDateReceivingAndPayingsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getDateReceivingAndPayingsForExcelDownload(dto);
    }
}
