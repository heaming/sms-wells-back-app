package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaEtcOutOfStorageService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 기타출고 등록 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/etc-out-of-storages")
public class WsnaEtcOutOfStorageController {

    private final WsnaEtcOutOfStorageService service;

    @ApiOperation(value = "기타출고 등록 청구부서 조회", notes = "화면 로드시 기타출고 등록 청구부서를 조회한다.")
    @GetMapping("/dept")
    public List<SearchDeptRes> getEtcOutOfStorageDepts() {
        return service.getEtcOutOfStorageDepts();
    }

    @ApiOperation(value = "기타출고 등록 조회", notes = "조회조건에 일치하는 기타출고 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getEtcOutOfStorages(
        SearchReq dto
    ) {
        return service.getEtcOutOfStorages(dto);
    }

    @ApiOperation(value = "기타출고 삭제", notes = "기타출고 삭제 처리 및 품목재고내역 삭제")
    @DeleteMapping
    public SaveResponse removeEtcOutOfStorages(
        @Valid
        @RequestBody
        @NotEmpty
        List<DeleteReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.removeEtcOutOfStorages(dtos))
            .build();

    }

    @ApiOperation(value = "기타출고 등록 조회 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getEtcOutOfStoragesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getEtcOutOfStoragesForExcelDownload(dto);
    }

    @ApiOperation(value = "기타출고 저장", notes = "기타출고 저장처리 및 품목재고내역 등록")
    @PostMapping
    public SaveResponse saveEtcOutOfStoragess(
        @Valid
        @RequestBody
        List<SaveReq> dtos

    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveEtcOutOfStoragess(dtos))
            .build();
    }

    @ApiOperation(value = "기타출고 영업센터 조직창고 조회", notes = "기타출고 영업센터인경우 영업센터조직창고 조회")
    @GetMapping("/wells-business/{apyYm}")
    public List<SearchCodeRes> getWellsCenterWarehouse(
        @PathVariable
        String apyYm
    ) {
        return this.service.getWellsCenterWarehouse(apyYm);
    }
}
