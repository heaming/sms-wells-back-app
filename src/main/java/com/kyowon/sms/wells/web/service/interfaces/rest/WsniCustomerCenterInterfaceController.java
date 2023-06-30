package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCustomerCenterInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + "고객센터 인터페이스")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/costumer-centers")
@RequiredArgsConstructor
@Validated
public class WsniCustomerCenterInterfaceController {

    private final WsniCustomerCenterInterfaceService service;

    @ApiOperation(value = "엔지니어 컨택 현황 조회", notes = "고객센터에서 엔지니어의 컨택 현황을 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asAkId", value = "AS요청ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping("/enginner-contact")
    public EaiWrapper getEngineerContactPs(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchContactRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerContactPs(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "엔지니어 약속변경 이력 조회", notes = "고객센터에서 엔지니어의 약속변경 이력 현황을 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asAkId", value = "AS요청ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkPrtnrNo", value = "작업파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkDt", value = "작업일자", paramType = "query", required = true)
    })
    @GetMapping("/engineer-promise-change")
    public EaiWrapper getEngineerPromChHist(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchPromChRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerPromChHist(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "엔지니어 취소 목록 조회", notes = "고객센터에서 설치 및 A/S배정된 대상 중 엔지니어 취소 목록을 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asAkId", value = "AS요청ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkPrtnrNo", value = "작업파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkDt", value = "작업일자", paramType = "query", required = true)
    })
    @GetMapping("/engineer-cancel")
    public EaiWrapper getEngineerCancels(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchCancelRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerCancels(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "모종 정기배송 제품 조회", notes = "고객센터에서 모종 정기배송 제품을 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/seeding-product")
    public EaiWrapper getSeedingRegularShippingPdct(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchSppPdctRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getSeedingRegularShippingPdct(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "모종 정기배송 방문정보 조회", notes = "고객센터에서 모종 정기배송 방문정보를 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/seeding-visit")
    public EaiWrapper getSeedingRegularShippingVst(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchSppVstRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getSeedingRegularShippingVst(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "AS 업체정보 조회", notes = "고객센터에서 Wells AS 업체정보를 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/as-business")
    public EaiWrapper getAfterServiceBusinessInf(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchAsRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getAfterServiceBusinessInf(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "필터 배송지 등록", notes = "고객센터에서 고객에게 배송할 필터 배송지 정보를 등록하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppTcnt", value = "배송차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query", required = true),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "locaraTno", value = "지역전화번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "exnoEncr", value = "전화국번호암호화", paramType = "query", required = true),
        @ApiImplicitParam(name = "idvTno", value = "개별전화번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppZip", value = "배송우편번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppBasAdr", value = "배송기본주소", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppDtlAdr", value = "배송상세주소", paramType = "query", required = true),
        @ApiImplicitParam(name = "refAdr", value = "참조주소", paramType = "query", required = true),
        @ApiImplicitParam(name = "adrDvCd", value = "주소구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppDptuDt", value = "배송출발일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppFshDt", value = "배송완료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", required = true),
    })
    @PostMapping("/filter-shpadr")
    public EaiWrapper<CreateShpadrRes> createFilterShippingAddress(
        @Valid
        @RequestBody
        EaiWrapper<CreateShpadrReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<CreateShpadrRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.createFilterShippingAddress(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "필터 배송지 수정", notes = "고객센터에서 고객에게 배송할 필터 배송지 정보를 수정하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppFshDt", value = "배송완료일자", paramType = "query", required = true),
    })
    @PutMapping("/filter-shpadr")
    public EaiWrapper<EditShpadrRes> editFilterShippingAddress(
        @Valid
        @RequestBody
        EaiWrapper<EditShpadrReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<EditShpadrRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.editFilterShippingAddress(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "부가 정보 조회", notes = "고객센터에서 설치 장소 상세위치, 서비스 부가정보 등 부가 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/additionals")
    public EaiWrapper<FindAdnInfRes> getAdditional(
        @Valid
        @RequestBody
        EaiWrapper<FindAdnInfReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<FindAdnInfRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getAdditional(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "정기배송 패키지 변경 이력 조회", notes = "고객센터에서 정기배송 패키지 변경 이력을 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/as-business")
    public EaiWrapper getPackageChangeHistory(
        @RequestBody
        @Valid
        EaiWrapper<FindAdnInfReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchPkgChRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getPackageChangeHistory(reqWrapper.getBody()));

        return resWrapper;
    }
}
