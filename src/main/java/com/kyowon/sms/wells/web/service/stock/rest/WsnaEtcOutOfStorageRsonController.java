package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaEtcOutOfStorageRsonService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/etc-out-of-storage-resons")
@Api(tags = "[WSNA] 기타출고 사유내역 REST API")
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageRsonController {

    private final WsnaEtcOutOfStorageRsonService service;

    @ApiOperation(value = "기타출고 사유내역 조회", notes = "조회조건에 일치하는 기타출고 사유내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "출고시작일", paramType = "query", example = "20230101", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "출고종료일", paramType = "query", example = "20230117"),
        @ApiImplicitParam(name = "bilRsonCd", value = "청구사유", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "pdGdCd", value = "등급", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "서비스센터", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "startItemCd", value = "시작품목코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "endItemCd", value = "종료품목코드", paramType = "query", example = "", required = true),
    })
    @GetMapping
    public List<SearchRes> getEtcOutOfStorageRsons(
        SearchReq dto
    ) {
        return this.service.getEtcOutOfStorageRsons(dto);
    }

    @ApiOperation(value = "기타출고 사유내역 조회", notes = "조회조건에 일치하는 기타출고 사유내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "출고시작일", paramType = "query", example = "20230101", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "출고종료일", paramType = "query", example = "20230117"),
        @ApiImplicitParam(name = "bilRsonCd", value = "청구사유", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "pdGdCd", value = "등급", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrWareNo", value = "서비스센터", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "startItemCd", value = "시작품목코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "endItemCd", value = "종료품목코드", paramType = "query", example = "", required = true),
    })
    @GetMapping("business")
    public List<SearchRes> getEtcOutOfStorageRsonBusiness(SearchReq dto) {
        return this.service.getEtcOutOfStorageRsonBusiness(dto);
    }

    @ApiOperation(value = "조회조건 서비스센터 콤보박스 조회", notes = "조회조건에 일치하는 기타출고 사유내역 콤보정보를 조회한다.")
    @GetMapping("service-Center")
    public List<CenterRes> getServiceCenter(SearchReq dto) {
        return this.service.getServiceCenters(dto);
    }

    @ApiOperation(value = "조회조건 영업센터 콤보박스 조회", notes = "조회조건에 일치하는 기타출고 사유내역 콤보정보를 조회한다.")
    @GetMapping("business-Center")
    public List<BusinessRes> getBusinessCenter(SearchReq dto) {
        return this.service.getBusinessCenter(dto);
    }

}
