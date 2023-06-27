package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.*;
import static com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyExcelDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedingOstrQtyService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-27
 */
@Api(tags = "[WSNA] 모종 출고가능수량 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seeding-out-of-storage-qtys")
public class WsnaSeedingOstrQtyController {

    private final WsnaSeedingOstrQtyService service;

    @GetMapping("/paging")
    @ApiOperation(value = "모종 출고가능수량 페이징 조회", notes = "모종 출고가능수량을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230627", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230627", required = true),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "업무유형코드", paramType = "query", example = "2")
    })
    public PagingResult<SearchRes> getSeedingOstrQtysPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {

        return this.service.getSeedingOstrQtysPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "모종 출고가능수량 엑셀 다운로드", notes = "조회조건에 일치하는 모종 출고가능수량 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", example = "20230627", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230627", required = true),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "업무유형코드", paramType = "query", example = "2")
    })
    public List<SearchRes> getSeedingOstrQtysExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getSeedingOstrQtysExcelDownload(dto);
    }

    @PutMapping
    @ApiOperation(value = "모종 출고가능수량 저장", notes = "모종 출고가능수량 데이터를 저장한다.")
    public SaveResponse editSeedingOstrQtys(
        @RequestBody
        @Valid
        @NotEmpty
        List<EditReq> dtos
    ) throws Exception {

        return SaveResponse.builder().processCount(this.service.editSeedingOstrQtys(dtos)).build();
    }

    @PostMapping("/excel-upload")
    @ApiOperation(value = "모종 출고가능수량 엑셀 업로드", notes = "모종 출고가능수량 데이터를 엑셀 업로드를 통해 일괄 등록한다.")
    public UploadRes createSeedingOstrQtysExcelUpload(@RequestParam("file")
    MultipartFile file) throws Exception {
        WsnaSeedingOstrQtyExcelDvo dvo = this.service.createSeedingOstrQtysExcelUpload(file);
        List<ExcelUploadErrorDvo> errorDvos = dvo.getErrorDvos();
        List<WsnaSeedingOstrQtyDvo> seedingDvos = dvo.getSeedingDvos();

        String status = CollectionUtils.isEmpty(errorDvos) ? PdProductConst.EXCEL_UPLOAD_SUCCESS
            : PdProductConst.EXCEL_UPLOAD_ERROR;

        return UploadRes.builder()
            .status(status)
            .excelData(seedingDvos)
            .errorInfo(errorDvos)
            .build();
    }

}
