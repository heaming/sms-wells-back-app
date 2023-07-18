package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.common.web.fee.schedule.dto.ZfeyFeeScheduleMgtDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SaveContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeyContractBsFeeExService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEY] 계약별 BS 수수료 예외 기준정보")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/contract-bs-fee")
@Slf4j
public class WfeyContractBsFeeExController {

    private final WfeyContractBsFeeExService service;


    @ApiOperation(value = "계약별 BS 수수료 예외 기준정보 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
            @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query"),
            @ApiImplicitParam(name = "vstMcn", value = "방문개월", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
            @ApiImplicitParam(name = "svFeePdDvCd", value = "BS상품그룹", paramType = "query"),
    })
    @GetMapping()
    public List<SearchContractBsFeeExRes> getContractBsFeeEx(@ApiParam @Valid SearchContractBsFeeExReq req) {
        return service.getContractBsFeeExList(req);
    }

    @ApiOperation(value = "계약별 BS 수수료 예외 기준정보 조회 페이징")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
            @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
            @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query"),
            @ApiImplicitParam(name = "vstMcn", value = "방문개월", paramType = "query"),
            @ApiImplicitParam(name = "apyStrtYm", value = "적용시작년월 ", paramType = "query"),
            @ApiImplicitParam(name = "apyEndYm", value = "적용종료년월 ", paramType = "query"),
            @ApiImplicitParam(name = "svFeePdDvCd", value = "BS상품그룹", paramType = "query"),
    })
    @GetMapping("/pages")
    public PagingResult<SearchContractBsFeeExRes> getContractBsFeeEx(@ApiParam @Valid SearchContractBsFeeExReq req, @Valid PageInfo pageInfo) {
        return service.getContractBsFeeExList(req, pageInfo);
    }

    @ApiOperation(value = "계약별 BS 수수료 예외 기준정보 계약추가 차수 조회", notes = "차수 찾아서 MAX + 1채번")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "path", example = ""),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "path", example = ""),
    })
    @GetMapping("/next-order/{cntrNo}-{cntrSn}")
    public int getStep(@PathVariable("cntrNo") String cntrNo, @PathVariable("cntrSn") int cntrSn) throws Exception {
        return service.getMaxBaseChTcnt(cntrNo, cntrSn);
    }

    @ApiOperation(value = "계약별 BS 수수료 예외 기준정보 저장", notes = "계약별 BS 수수료 예외 기준정보 데이터를 수정한다.")
    @PostMapping()
    public SaveResponse saveContractBsFeeEx(@RequestBody @Valid List<SaveContractBsFeeExReq> dto) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveContractBsFeeEx(dto))
            .build();
    }

    @ApiOperation(value = "계약별 BS 수수료 예외 기준정보 엑셀 업로드", notes = "엑셀 업로드")
    @PostMapping("/excel-upload")
    public ExcelUploadDto.UploadRes saveContractBsFeeExExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveContractBsFeeExExcelUpload(file);
    }


}
