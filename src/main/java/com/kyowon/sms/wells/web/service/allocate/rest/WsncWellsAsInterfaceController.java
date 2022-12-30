package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto.SearchCustInfoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto.SearchCustInfoRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto.SearchRecInfoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto.SearchRecInfoRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncWellsAsInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@InterfaceController
//@RestController
@RequestMapping(SnServiceConst.INTERFACE_URL_V1 + "/wells-as-interfaces")
@Api(tags = "[WSNC] Wells 인터페이스 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncWellsAsInterfaceController {

    private final WsncWellsAsInterfaceService service;

    //    @ApiOperation(value = "고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    //    @ApiImplicitParams(value = {
    //        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    //        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "1"),
    //        @ApiImplicitParam(name = "cstKnm", value = "고객한글명", paramType = "query"),
    //        @ApiImplicitParam(name = "hpno", value = "핸드폰번호", paramType = "query"),
    //        @ApiImplicitParam(name = "newAdrZip", value = "주소", paramType = "query"),
    //        @ApiImplicitParam(name = "pdGrpId", value = "제품그룹ID", paramType = "query"),
    //    })
    //    @GetMapping("/customer-informations")
    //    public List<WsncWellsAsInterfaceDto.SearchCustInfoRes> getCustomerInformations(
    //        WsncWellsAsInterfaceDto.SearchCustInfoReq req
    //    ) {
    //        return service.getCustomerInformations(req);
    //    }

    @ApiOperation(value = "고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/customer-informations")
    public EaiWrapper getCustomerInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchCustInfoReq> reqWrapper
    ) {
        EaiWrapper<List<WsncWellsAsInterfaceDto.SearchCustInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getCustomerInformations(reqWrapper.getBody()));
        return resWrapper;
    }

//    @ApiOperation(value = "고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
//    @ApiImplicitParams(value = {
//        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
//        @ApiImplicitParam(name = "svBizDclsfCd", value = "서비스업무세분류코드", paramType = "query"),
//    })
//    @GetMapping("/receipt-Informations")
//    public List<SearchRecInfoRes> getReceiptInformations(
//        SearchRecInfoReq req
//    ) {
//        return service.getReceiptInformations(req);
//    }
    @ApiOperation(value = "고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/receipt-Informations")
    public EaiWrapper getReceiptInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchRecInfoReq> reqWrapper
    ) {
        EaiWrapper<List<WsncWellsAsInterfaceDto.SearchRecInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getReceiptInformations(reqWrapper.getBody()));
        return resWrapper;
    }

}
