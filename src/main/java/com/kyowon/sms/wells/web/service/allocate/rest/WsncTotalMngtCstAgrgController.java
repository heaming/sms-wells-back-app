package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTotalMngtCstAgrgDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncTotalMngtCstAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_WELLS_SERVICE + "/total-customers")
@Api(tags = "[WSNC] W-SV-U-0228M01 총 관리고객 집계 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncTotalMngtCstAgrgController {

    private final WsncTotalMngtCstAgrgService service;

    @ApiOperation(value = "총 관리고객 집계")
        @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "year", value = "년도", paramType = "query", example = "2022"),
        @ApiImplicitParam(name = "pdGdCd", value = "상품등급코드", paramType = "query", example = "A"),
    })
    @GetMapping
    public List<WsncTotalMngtCstAgrgDto.SearchRes> selectTotalMngtCstAgrgs(
        WsncTotalMngtCstAgrgDto.SearchReq dto
    ) {
        return service.selectTotalMngtCstAgrgs(dto);
    }

}
