package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.FindProductRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcessingDvo;
import com.kyowon.sms.wells.web.service.visit.service.WsnbServiceProcessingService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-processing")
@Api(tags = "[WSNB] 서비스처리 내역 REST API")
@RequiredArgsConstructor
public class WsnbServiceProcessingController {

    private final WsnbServiceProcessingService service;

    /**
     * 상품 조회
     * @param pdGrpCd 상품그룹코드
     * @return 특정 상품그룹에 속한 상품 목록
     */
    @ApiOperation(value = "상품 조회", notes = "특정 상품군에 속한 상품 목록을 조회한다.")
    @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "1")
    @GetMapping("/products")
    public List<FindProductRes> getProducts(@RequestParam
    String pdGrpCd) {
        return this.service.getProducts(pdGrpCd);
    }

    /**
     * 서비스처리 내역 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 서비스처리 내역
     */
    @ApiOperation(value = "서비스처리 내역 조회", notes = "조회조건에 해당하는 고객 방문 후 서비스 처리 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "serviceType", value = "서비스유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "엔지니어", paramType = "query", example = ""),
        @ApiImplicitParam(name = "refriType", value = "유무상구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "12"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM03100193"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형(서비스업무세분류코드)", paramType = "query", example = "3100"),
        @ApiImplicitParam(name = "inquiryBase", value = "조회기준", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자From", paramType = "query", example = "20230320"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자To", paramType = "query", example = "20230320"),
        @ApiImplicitParam(name = "wkPrgsStatCd", value = "작업상태", paramType = "query", example = "10"),
        @ApiImplicitParam(name = "installBase", value = "설치기준", paramType = "query", example = "1"),
    })
    @GetMapping("/paging")
    public PagingResult<WsnbServiceProcessingDvo> getServiceProcessings(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getServiceProcessings(dto, pageInfo);
    }

    /**
     * 서비스처리 내역 조회 (엑셀 다운로드)
     * @param dto 조회조건
     * @return 서비스처리 내역
     */
    @ApiOperation(value = "서비스처리 내역 조회 (엑셀 다운로드)", notes = "조회조건에 해당하는 고객 방문 후 서비스 처리 내역을 조회한다.")
    @GetMapping("/excel-download")
    public List<WsnbServiceProcessingDvo> getServiceProcessingsForExcel(SearchReq dto) {
        return this.service.getServiceProcessingsForExcel(dto);
    }

}
