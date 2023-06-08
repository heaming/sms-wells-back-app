package com.kyowon.sms.wells.web.service.stock.rest;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsStoreService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 반품입고 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-store")
public class WsnaReturningGoodsStoreController {

    private final WsnaReturningGoodsStoreService service;

    @ApiOperation(value = "반품입고 관리 조회", notes = "조회조건에 해당하는 반품입고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목출고번호", paramType = "query", example = "212"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "200005"),
        @ApiImplicitParam(name = "stFnlVstFshDtFrom", value = "처리일자From", paramType = "query", example = "20230109"),
        @ApiImplicitParam(name = "edFnlVstFshDtTo", value = "처리일자To", paramType = "query", example = "20230109")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getReturningGoodsStores(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getReturningGoodsStores(dto, pageInfo);
    }

    @ApiOperation(value = "반품입고 저장", notes = "창고장이나 매니저/엔지니어가 반품입고를받아 물류센터로 반품출고요청을 한다.")
    @PostMapping
    public SaveResponse saveReturningGoodsStores(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos

    ) throws ParseException {
        return SaveResponse.builder().processCount(this.service.saveReturningGoodsStores(dtos)).build();
    }

    @ApiOperation(value = "반품입고 관리 엑셀다운로드", notes = "검색조건을 받아 엑셀다운로드용 반품입고 관리를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReturningGoodsStoresExcelDownload(
        SearchReq dto
    ) {
        return service.getReturningGoodsStoresExcelDownload(dto);
    }

}
