package com.kyowon.sms.wells.web.service.orgcode.rest;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesOperationJrnlMgtDvo;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndBusinessVehiclesOperationJrnlMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSND] 업무차량 운행일지")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wwsnd-business-vehicles-operation-jrnl-mgt")
public class WsndBusinessVehiclesOperationJrnlMgtController {
    private final WsndBusinessVehiclesOperationJrnlMgtService service;

    @ApiOperation(value = "업무차량 운행일지 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto
    ) {
        return service.getBusinessVehiclesOperationJrnl(dto);
    }

    @ApiOperation(value = "엔지니어 지급차량 조회(paging)", notes = "조회조건에 일치하는 정보를 페이징 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBusinessVehiclesOperationJrnl(dto, pageInfo);
    }

    @ApiOperation(value = "주유량, 주유금액 저장", notes = "선택정보에 대한 주유량, 주유금액 저장")
    @PostMapping
    public SaveResponse editBusinessVehiclesOperationJrnl(
        @RequestBody
        List<WsndBusinessVehiclesOperationJrnlMgtDvo> dvos
    ) {
        return SaveResponse.builder().processCount(service.editBusinessVehiclesOperationJrnlMgt(dvos)).build();
    }
}
