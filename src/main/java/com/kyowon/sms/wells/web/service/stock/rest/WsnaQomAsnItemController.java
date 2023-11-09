package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindDetailRes;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindRes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaQomAsnItemService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 물량배정 품목 조회 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-09
 */

@Api(tags = "[WSNA] 물량배정 품목조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/qom-asn-items")
public class WsnaQomAsnItemController {

    private final WsnaQomAsnItemService service;

    @GetMapping("/master-information")
    @ApiOperation(value = "물량배정 품목 상단 정보 조회", notes = "물량배정 품목의 마스터 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmOstrNo", value = "품목출고번호", paramType = "query", example = "222200902010000001", required = true)
    })
    public FindRes getQomAsnItemMasterInfo(@RequestParam(name = "itmOstrNo")
    String itmOstrNo) {
        return this.service.getQomAsnItemMasterInfo(itmOstrNo);
    }

    @GetMapping
    @ApiOperation(value = "물량배정 품목 조회", notes = "물량배정 품목을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmOstrNo", value = "품목출고번호", paramType = "query", example = "222200902010000001", required = true)
    })
    public List<FindDetailRes> getQomAsnItems(@RequestParam(name = "itmOstrNo")
    String itmOstrNo) {
        return this.service.getQomAsnItems(itmOstrNo);
    }

}
