package com.kyowon.sms.wells.web.service.stock.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.service.stock.service.WsnaPcsvSppBzsIvcMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 택배 배송업체 송장관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/pcsv-spp-bzs-out-of-storage")
public class WsnaPcsvSppBzsIvcMgtController {

    private final WsnaPcsvSppBzsIvcMgtService service;

    @ApiOperation(value = "택배 배송 업체 송장관리 엑셀업로드", notes = "택배 배송 업체 송장관리 엑셀업로드를 한다.")
    @PostMapping("/excel-upload")
    public UploadRes saveExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.savePcsvSppBzsOutOfStorageExcelUpload(file);
    }
}
