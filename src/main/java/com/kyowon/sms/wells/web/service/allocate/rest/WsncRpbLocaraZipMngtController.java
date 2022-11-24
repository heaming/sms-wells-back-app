package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbLocaraZipMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.ServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
@RestController
@RequestMapping(ServiceConst.REST_URL_WELLS_SERVICE + "/rpb-locara-zip-mngt")
@Api(tags = "[WSNC] 책임지역 우편번호 관리 REST API")
@RequiredArgsConstructor
public class WsncRpbLocaraZipMngtController {

    private final WsncRpbLocaraZipMngtService rpbLocaraZipMngtService;

    /**
     * 책임지역 우편번호 관리 - 조회 (페이징)
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @param pageInfo
     * @return 조회결과
     */
    @ApiOperation(value = "책임지역 우편번호 조회", notes = "조회조건에 일치하는 책임지역 우편번호 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10"),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101")
    })
    @GetMapping("/paging")
    public PagingResult<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipMngtPages(
        WsncRpbLocaraZipMngtDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.rpbLocaraZipMngtService.getRpbLocaraZipMngtPages(dto, pageInfo);
    }

    /**
     * 책임지역 우편번호 관리 - 엑셀 다운로드
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @return 조회결과
     */
    @ApiOperation(value = "책임지역 우편번호 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 우편번호 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10"),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101")
    })
    @GetMapping("/excel-download")
    public List<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipMngtExcelDownload(
        WsncRpbLocaraZipMngtDto.SearchReq dto
    ) {
        return this.rpbLocaraZipMngtService.getRpbLocaraZipsForExcelDownload(dto);
    }

}
