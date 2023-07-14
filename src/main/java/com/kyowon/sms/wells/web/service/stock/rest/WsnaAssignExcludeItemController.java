package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAssignExcludeItemService;
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
 * W-SV-U-0189P01 배정제외품목 등록 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-14
 */

@Api(tags = "[WSNA] 배정제외품목 등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/assign-exclude-items")
public class WsnaAssignExcludeItemController {

    private final WsnaAssignExcludeItemService service;

    @GetMapping("/warehouse")
    @ApiOperation(value = "배정제외품목 등록 창고 조회", notes = "배정제외품목 등록 창고를 조회한다.")
    public List<WsnzWellsCodeWareHouseDvo> getWarehouse() {
        return this.service.getWarehouse();
    }

    @GetMapping("/paging")
    @ApiOperation(value = "배정제외품목 등록 페이징 조회", notes = "배정제외품목을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnExcdDvCd", value = "제외유형", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "제외품목", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "wareNo", value = "영업센터", paramType = "query", example = "300001")
    })
    public PagingResult<SearchRes> getAssignExcludeItemsPaging(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getAssignExcludeItemsPaging(dto, pageInfo);
    }

    @DeleteMapping
    @ApiOperation(value = "배정제외품목 삭제", notes = "배정제외품목을 삭제한다.")
    public SaveResponse removeAssignExcludeItem(
        @RequestBody
        @Valid
        RemoveReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.removeAssignExcludeItem(dto)).build();
    }

    @PostMapping
    @ApiOperation(value = "배정제외품목 등록", notes = "배정제외품목을 등록한다.")
    public SaveResponse createAssignExcludeItems(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.createAssignExcludeItems(dtos)).build();
    }
}
