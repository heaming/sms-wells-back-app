package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.CttIzReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SaveCttNwRgstReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SaveWkCanRgstReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.WkCanReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.WkCanRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;
import com.kyowon.sms.wells.web.service.visit.service.WsnbServiceProcDetailListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 서비스처리상세 내역 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-proc-detail-list")
public class WsnbServiceProcDetailListController {

    private final WsnbServiceProcDetailListService service;

    @ApiOperation(value = "서비스처리상세 내역 조회", notes = "조회조건에 일치하는 서비스처리상세 내역 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "gubun", value = "조회유형", paramType = "query", required = true),
    })
    @GetMapping
    public WsnbServiceProcDetailListDvo getServiceProcDetailList(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailList(dto);

    }

    @ApiOperation(value = "결제 내역 조회", notes = "조회조건에 일치하는 결제 내역 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "gubun", value = "조회유형", paramType = "query", required = true),
    })
    @GetMapping("/stlm-iz")
    public List<WsnbServiceProcDetailStlmIzDvo> getServiceProcDetailStlmIzs(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailStlmIzs(dto);

    }

    @ApiOperation(value = "청구 항목 조회", notes = "조회조건에 일치하는 청구 항목 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "gubun", value = "조회유형", paramType = "query", required = true),
    })
    @GetMapping("/bil-item")
    public List<WsnbServiceProcDetailBilItemDvo> getServiceProcDetailBilItems(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailBilItems(dto);

    }

    @ApiOperation(value = "투입 부품 조회", notes = "조회조건에 일치하는 투입 부품 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "gubun", value = "조회유형", paramType = "query", required = true),
    })
    @GetMapping("/pu-part")
    public List<WsnbServiceProcDetailPuPartDvo> getServiceProcDetailPuParts(
        @Valid
        SearchReq dto
    ) {
        return service.getServiceProcDetailPuParts(dto);
    }

    @ApiOperation(value = "컨택 신규 등록", notes = "서비스처리상세내역 > 외주직원 인 경우 컨택신규 등록 처리를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cttRcpDtm", value = "컨택일[컨택접수일시]", paramType = "query", required = true),
        @ApiImplicitParam(name = "cttDuedt", value = "방문예정일[컨택예정일]", paramType = "query", required = true),
        @ApiImplicitParam(name = "cttAkCn", value = "전달사항[컨택요청내용]", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cttOjId", value = "컨택대상ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "cttSn", value = "컨택일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkKnd", value = "작업종류[insert or update]", paramType = "query", required = true),
        @ApiImplicitParam(name = "beforeRcpDtm", value = "기등록된 컨택일", paramType = "query"),
        @ApiImplicitParam(name = "beforeDuedt", value = "기등록된 방문예정일", paramType = "query"),
        @ApiImplicitParam(name = "beforeMoCn", value = "기등록된 메모", paramType = "query"),
    })
    @PostMapping("/save-ctt")
    public SaveResponse saveCttNwRgst(@RequestBody @Valid @NotEmpty SaveCttNwRgstReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(this.service.saveCttNwRgst(dto))
            .build();
    }

    @ApiOperation(value = "컨택 내용 조회", notes = "외주직원인 경우 컨택내용을 조회한다.")
    @GetMapping("/ctt-iz")
    public CttIzReq getServiceProcDetailCttDatas(@Valid SearchReq dto) {
        return service.getServiceProcDetailCttDatas(dto);
    }

    @ApiOperation(value = "작업취소 여부 조회", notes = "외주직원인 경우 작업취소햇는지를 조회한다.")
    @GetMapping("/wk-can")
    public WkCanReq getServiceProcDetailWkCanDatas(@Valid WkCanRes dto) {
        return service.getServiceProcDetailWkCanDatas(dto);
    }

    @ApiOperation(value = "작업취소 등록", notes = "서비스처리상세내역 > 외주직원 인 경우 작업취소 등록 처리를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "canDv", value = "취소구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "canRson", value = "취소사유", paramType = "query", required = true),
        @ApiImplicitParam(name = "charFw", value = "문자발송여부", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/save-wk-can")
    public SaveResponse saveCttNwRgst(@RequestBody @Valid @NotEmpty SaveWkCanRgstReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(this.service.saveWkCanRgst(dto))
            .build();
    }
}
