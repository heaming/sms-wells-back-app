package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebMutualAidFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEB] 상조 수수료 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/mutual-aid")
@Slf4j
public class WfebMutualAidFeeMgtController {

     private final WfebMutualAidFeeMgtService service;

    @ApiOperation(value = "상조 수수료 - 조회(개인)", notes = "상조 수수료를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "strtDt", value = "계약시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "번호", paramType = "query"),
        @ApiImplicitParam(name = "clasfctnFee", value = "수수료구분", paramType = "query"),
    })
    @GetMapping("/individual")
    public List<AidIndividual> getMutualAidIndividual(@Valid SearchAidReq req) throws Exception {
        return service.getMutualAidIndividual(req);
    }

    @ApiOperation(value = "상조 수수료 - 조회(조직)", notes = "상조 수수료를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "strtDt", value = "계약시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "번호", paramType = "query"),
        @ApiImplicitParam(name = "clasfctnFee", value = "수수료구분", paramType = "query"),
    })
    @GetMapping("/group")
    public List<AidGroup> getMutualAidGroup(@Valid SearchAidReq req) throws Exception {
        return service.getMutualAidGroup(req);
    }

    @ApiOperation(value = "상조 수수료 - 생성", notes = "상조 수수료 해당일자로 생성한다.")
    @PostMapping("/create")
    public SaveResponse createMutualAid(@RequestBody @Valid CreateAidReq req) throws Exception {
        return SaveResponse.builder().processCount(service.createMutualAid(req)).build();
    }

    @ApiOperation(value = "상조 수수료 - 되물림 생성", notes = "상조 수수료 해당일자로 되물림을 생성한다.")
    @PostMapping("/create-redf")
    public SaveResponse createRedfMutualAid(@RequestBody @Valid CreateAidReq req) throws Exception {
        return SaveResponse.builder().processCount(service.createRedfMutualAid(req)).build();
    }

    @ApiOperation(value = "상조 수수료 제휴주문 - 조회", notes = "상조 수수료 제휴주문을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogId3", value = "조직 3레벨", paramType = "query"),
        @ApiImplicitParam(name = "ogId2", value = "조직 2레벨", paramType = "query"),
        @ApiImplicitParam(name = "ogId1", value = "조직 1레벨", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너 번호", paramType = "query"),
    })
    @GetMapping("/order")
    public List<AidOrder> getMutualAidOrder(@Valid SearchAidOrderReq req) throws Exception {
        return service.getMutualAidOrder(req);
    }
}
