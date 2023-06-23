package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemPdDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaComputationExcludeItemService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 산출 제외품목 등록")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/computation-exclude-items")
public class WsnaComputationExcludeItemController {

    private final WsnaComputationExcludeItemService service;

    /**
     * 산출 제외품목 등록 품목 조회
     * @return
     */
    @GetMapping("/products")
    @ApiOperation(value = "산출 제외품목 등록 품목 조회", notes = "산출 제외품목 품목 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrYm", value = "조회년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6")
    })
    public List<WsnaComputationExcludeItemPdDvo> getProducts() {

        return this.service.getProducts();
    }

    /**
     * 산출 제외품목 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    @GetMapping("/paging")
    @ApiOperation(value = "산출 제외품목 페이징 조회", notes = "산출 제외품목을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrYm", value = "조회년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public PagingResult<SearchRes> getCountExcludeItemsPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {

        return this.service.getCountExcludeItemsPaging(dto, pageInfo);
    }

    /**
     * 산출 제외품목 엑셀 다운로드
     * @param dto
     * @return
     */
    @GetMapping("/excel-download")
    @ApiOperation(value = "산출 제외품목 엑셀 다운로드", notes = "조회조건에 일치하는  산출 제외품목 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrYm", value = "조회년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "6"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목상품코드", paramType = "query", example = "WM07104077"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248")
    })
    public List<SearchRes> getCountExcludeItemsExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getCountExcludeItemsExcelDownload(dto);
    }

    /**
     * 산출 제외품목 삭제
     * @param dtos
     * @return
     * @throws Exception
     */
    @DeleteMapping
    @ApiOperation(value = "산출 제외품목 삭제", notes = "산출 제외품목 데이터를 삭제한다.")
    public SaveResponse removeCountExcludeItems(
        @RequestBody
        @Valid
        List<RemoveReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(this.service.updateCountExcludeItemForRemove(dtos)).build();
    }

    /**
     * 산출 제외품목 저장
     * @param dtos
     * @return
     * @throws Exception
     */
    @PostMapping
    @ApiOperation(value = "산출 제외품목 저장", notes = "산출 제외품목 데이터를 저장한다.")
    public SaveResponse saveCountExcludeItems(
        @RequestBody
        @Valid
        List<SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(this.service.saveCountExcludeItem(dtos)).build();
    }

    /**
     * 산출 제외품목 데이터 이관
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/item-transfers")
    @ApiOperation(value = "산출 제외품목 데이터 이관", notes = "전월 산출 제외품목 데이터를 이번달로 적용한다.")
    public SaveResponse createCountExcludeItemForTransfers(
        @RequestBody
        @Valid
        TransferReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.createCountExcludeItemForTransfers(dto)).build();
    }

}
