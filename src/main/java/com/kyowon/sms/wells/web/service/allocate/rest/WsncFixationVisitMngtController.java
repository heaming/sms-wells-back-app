package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncFixationVisitService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 고정방문 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/fixation-visit")
@Slf4j
public class WsncFixationVisitMngtController {
    private final WsncFixationVisitService wsncFixationVisitMgntService;

    @ApiOperation(value = "고정방문 관리 화면 - 고정방문 목록 조회", notes = "조회조건에 따른 고정방문 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrDvCd", value = "관리구분(1 : 매니저, 2 : 엔지니어)", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "방문담당자", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<WsncFixationVisitDto.SearchRes> getFixationVisits(
        WsncFixationVisitDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return wsncFixationVisitMgntService.getFixationVisits(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<WsncFixationVisitDto.SearchRes> getFixationVisitsExcelDownload(
        WsncFixationVisitDto.SearchReq dto
    ) {
        return wsncFixationVisitMgntService.getFixationVisitsExcelDownload(dto);
    }

    @ApiOperation(value = "고정방문 등록 팝업 - 고정방문 등록 팝업 조회", notes = "조회조건에 따른 고정방문 등록 팝업 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
    })
    @GetMapping
    public WsncFixationVisitDto.SearchRegRes getFixationVisit(
        WsncFixationVisitDto.SearchRegReq dto
    ) {
        //        return wsncFixationVisitMgntService.getFixationVisit(dto);
        WsncFixationVisitDto.SearchRegRes tempRes = wsncFixationVisitMgntService.getFixationVisit(dto);

        //TODO : Test를 위한 Garbage Data setting(추후 삭제)
        log.info("cntrNo ::: " + dto.cntrNo());
        if ("test".equals(dto.cntrNo())) {
            log.info("Test Data setting");
            tempRes = new WsncFixationVisitDto.SearchRegRes(
                "test1", //String cntrNo,
                "1", //String cntrSn,
                "1", //String chSn,
                "test4", //String cstKnm,
                "010", //String cralLocaraTno,
                "3333", //String mexnoEncr,
                "4444", //String cralIdvTno,
                "test8", //String rnadr,
                "test9", //String rdadr,
                "test10", //String rcgvpKnm,
                "010", //String cralLocaraTnoInstall,
                "1111", //String mexnoEncrInstall,
                "2222", //String cralIdvTnoInstall,
                "test14", //String rnadrInstall,
                "test15", //String rdadrInstall,
                "test16", //String pdNm,
//                "2", //String pdPrpVal01,(0~9)
                "용도3", //String pdPrpVal01,(0~9)
                //Insert Data
                "202212", //String apyStrtYm,
                "1", //String chRqrDvCd,
                "20221205105833", //String fnlMdfcDtm,
                "1", //String fxnPrtnrDvCd,
                "1",
                "test22", //String fxnPrtnrNo,
                "test33", //String fxnPrtnrKnm,
                "힘들어요", //String chRsonCn,
                "N", //String dtaDlYn,

                "test25", //String prtnrKnm,
                "99991231" //String rsgnDt
            );
        }
        return tempRes;
    }

    @ApiOperation(value = "고정방문 등록 팝업 - 고정방문 등록 및 수정", notes = "고정방문 등록")
    @PostMapping
    public SaveResponse saveFixationVisit(
        @Valid
        @RequestBody
        WsncFixationVisitDto.SaveRegReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsncFixationVisitMgntService.saveFixationVisit(dto))
            .build();
    }
}
