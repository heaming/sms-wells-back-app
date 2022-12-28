package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncWellsAsInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wells-as-interfaces")
@Api(tags = "[WSNC] Wells 인터페이스 AS 고객 정보 조회 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncWellsAsInterfaceController {

    private final WsncWellsAsInterfaceService service;

    @ApiOperation(value = "고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객한글명", paramType = "query"),
        @ApiImplicitParam(name = "hpno", value = "핸드폰번호", paramType = "query"),
        @ApiImplicitParam(name = "newAdrZip", value = "주소", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpId", value = "제품그룹ID", paramType = "query"),
    })
    @GetMapping("/customer-informations")
    public List<WsncWellsAsInterfaceDto.SearchCustInfoRes> getCustomerInformations(
        WsncWellsAsInterfaceDto.SearchCustInfoReq req
    ) {
        return service.getCustomerInformations(req);
    }

}
