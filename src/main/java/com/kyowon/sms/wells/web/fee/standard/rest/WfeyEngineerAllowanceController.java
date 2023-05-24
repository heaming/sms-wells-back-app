package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.CreateAllowanceUnitPriceReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.EditAllowanceUnitPriceReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.RemoveAllowanceUnitPriceReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeyEngineerAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.SearchAllowanceUnitPriceReq;

@Api(tags = "[WFEY] 엔지니어 수당 지급단가")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/engineer-allowances")
@Slf4j
public class WfeyEngineerAllowanceController {

    private final WfeyEngineerAllowanceService engineerAllowanceService;

    @ApiOperation(value = "엔지니어 수당 단가 목록 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "regionLevelDivideCode", value = "급지구분코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "productGroupCode", value = "상품그룹코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "productGroupDetailCode", value = "상품그룹상세코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "currentlyApplyDataYn", value = "현재적용데이터여부", paramType = "query", required = true),
    })
    @GetMapping("/unit-prices")
    public List<SearchAllowanceUnitPriceRes> getEngineerAwUprcs(@ApiParam @Valid SearchAllowanceUnitPriceReq req) {
        return engineerAllowanceService.getEngineerAwUprcs(req);
    }

    @ApiOperation(value = "엔지니어 수당 단가 신규생성")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "svTpCd", value = "서비스유형코드", paramType = "body", required = true),
            @ApiImplicitParam(name = "siteAwAtcCd", value = "현장수당항목코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "siteAwGrpCd", value = "현장수당그룹코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "rglvlDvCd", value = "급지구분코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "apyStrtdt", value = "적용시작일자 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "apyEnddt", value = "적용종료일자 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "fuleyAwAmt", value = " 정직원수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1TopmrAwAmt", value = " 직무1급수석수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1UplrAwAmt", value = " 직무1급상급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1MdlyrAwAmt", value = " 직무1급중급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1LolyrAwAmt", value = " 직무1급하급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2UplrAwAmt", value = " 직무2급상급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2MdlyrAwAmt", value = " 직무2급중급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2LolyrAwAmt", value = " 직무2급하급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL3AwAmt", value = " 직무3급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "crwkAwAmt", value = " 계약직수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "indvEntrpAwAmt", value = " 개인사업자수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "mngerWkUprc", value = " 매니저작업단가 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rmkCn", value = " 비고내용 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "useYn", value = " 사용여부 ", paramType = "body", required = false),
    })
    @PostMapping("/unit-prices")
    public SaveResponse createEngineerAwUprcs(@RequestBody @Valid CreateAllowanceUnitPriceReq req) {
        return SaveResponse.builder()
                .processCount(engineerAllowanceService.createEngineerAwUprcs(req))
                .build();
    }

    @ApiOperation(value = "엔지니어 수당 단가 수정")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "svTpCd", value = "서비스유형코드", paramType = "body", required = true),
            @ApiImplicitParam(name = "siteAwAtcCd", value = "현장수당항목코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "siteAwGrpCd", value = "현장수당그룹코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "rglvlDvCd", value = "급지구분코드 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "apyStrtdt", value = "적용시작일자 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "apyEnddt", value = "적용종료일자 ", paramType = "body", required = true),
            @ApiImplicitParam(name = "fuleyAwAmt", value = " 정직원수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1TopmrAwAmt", value = " 직무1급수석수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1UplrAwAmt", value = " 직무1급상급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1MdlyrAwAmt", value = " 직무1급중급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolLyr1LolyrAwAmt", value = " 직무1급하급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2UplrAwAmt", value = " 직무2급상급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2MdlyrAwAmt", value = " 직무2급중급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL2LolyrAwAmt", value = " 직무2급하급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rolL3AwAmt", value = " 직무3급수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "crwkAwAmt", value = " 계약직수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "indvEntrpAwAmt", value = " 개인사업자수당금액 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "mngerWkUprc", value = " 매니저작업단가 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "dsbBaseSn", value = " 지급기준일련번호 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "rmkCn", value = " 비고내용 ", paramType = "body", required = false),
            @ApiImplicitParam(name = "useYn", value = " 사용여부 ", paramType = "body", required = false),
    })
    @PutMapping("/unit-prices")
    public SaveResponse editEngineerAwUprcs(@RequestBody @Valid EditAllowanceUnitPriceReq req) {
        return SaveResponse.builder()
                .processCount(engineerAllowanceService.editEngineerAwUprcs(req))
                .build();
    }

    @ApiOperation(value = "엔지니어 수당 단가 삭제")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "svTpCd", value = "서비스유형코드", paramType = "query", required = true),
            @ApiImplicitParam(name = "siteAwAtcCd", value = "현장수당항목코드 ", paramType = "query", required = true),
            @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드 ", paramType = "query", required = true),
            @ApiImplicitParam(name = "rglvlDvCd", value = "급지구분코드 ", paramType = "query", required = true),
            @ApiImplicitParam(name = "dsbBaseSn", value = " 지급기준일련번호 ", paramType = "query", required = true),
    })
    @DeleteMapping("/unit-prices")
    public SaveResponse removeEngineerAwUprcs(@ApiParam @Valid RemoveAllowanceUnitPriceReq req) {
        return SaveResponse.builder()
                .processCount(engineerAllowanceService.removeEngineerAwUprcs(req))
                .build();
    }

}
