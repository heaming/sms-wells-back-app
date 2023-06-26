package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncCompanyIstStateService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 회사설치 현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/company-ist-state")
@Slf4j
public class WsncCompanyIstStateController {

    private final WsncCompanyIstStateService service;

    @ApiOperation(value = "회사설치 현황 - 전체탭 조회", notes = "전체 현황 조회")
    @GetMapping("/all/paging")
    public PagingResult<SearchAllRes> getCompanyIstStateAll(
        SearchMainReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyIstStateAll(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 전체탭 엑셀 다운로드", notes = "전체 엑셀 다운로드")
    @GetMapping("/all/excel-download")
    public List<SearchAllRes> getCompanyIstStateAll(
        SearchMainReq dto
    ) {
        return service.getCompanyIstStateAll(dto);
    }

    @ApiOperation(value = "회사설치 현황 - 현황탭 조회", notes = "현황 조회")
    @GetMapping("/state/paging")
    public PagingResult<SearchPsRes> getCompanyIstStatePs(
        SearchPsReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyIstStatePs(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 현황탭 엑셀 다운로드", notes = "현황 엑셀 다운로드")
    @GetMapping("/state/excel-download")
    public List<SearchPsRes> getCompanyIstStatePs(
        SearchPsReq dto
    ) {
        return service.getCompanyIstStatePs(dto);
    }

    //    <!-- TODO 개발중 - K-W-SV-U-0270M02 K-W-SV-U-0270M03 -->
    //    @ApiOperation(value = "회사설치 현황 - 필터탭 조회", notes = "필터 조회")
    //    @GetMapping("/filter/paging")
    //    public PagingResult<SearchFltrSubMatRes> getCompanyIstStateFltr(
    //        SearchMainReq dto,
    //        @Valid
    //        PageInfo pageInfo
    //    ) {
    //        return service.getCompanyIstStateFltr(dto, pageInfo);
    //    }
    //
    //    @ApiOperation(value = "회사설치 현황 - 필터탭 엑셀 다운로드", notes = "필터 엑셀 다운로드")
    //    @GetMapping("/filter/excel-download")
    //    public List<SearchFltrSubMatRes> getCompanyIstStateFltr(
    //        SearchMainReq dto
    //    ) {
    //        return service.getCompanyIstStateFltr(dto);
    //    }
    //
    //    @ApiOperation(value = "회사설치 현황 - 부자재탭 조회", notes = "부자재 조회")
    //    @GetMapping("/sub-material/paging")
    //    public PagingResult<SearchFltrSubMatRes> getCompanyIstStateSubMat(
    //        SearchMainReq dto,
    //        @Valid
    //        PageInfo pageInfo
    //    ) {
    //        return service.getCompanyIstStateSubMat(dto, pageInfo);
    //    }
    //
    //    @ApiOperation(value = "회사설치 현황 - 부자재탭 엑셀 다운로드", notes = "부자재 엑셀 다운로드")
    //    @GetMapping("/sub-material/excel-download")
    //    public List<SearchFltrSubMatRes> getCompanyIstStateSubMat(
    //        SearchMainReq dto
    //    ) {
    //        return service.getCompanyIstStateSubMat(dto);
    //    }
}
