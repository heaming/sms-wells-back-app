package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadErrorDvo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaMdProductOutOfStorageExcelUploadDto {

    @Builder
    @ApiModel(value = "WsnaMdProductOutOfStorageExcelUploadDto-ValidateReq")
    public record ValidateReq(

        String cstSvAsnNo, // 배정번호

        String cntrSn, // 계약번호

        String cntrNo, // 계약일련번호

        String sppIvcNo, // 송장번호

        String sppBzsPdId, // SR번호

        String pcsvCompDv // 택배사구분

    ) {}

    @Builder
    @ApiModel(value = "WsnaMdProductOutOfStorageExcelUploadDto-ValidateRes")
    public record ValidateRes(
        boolean result,
        List<WsnaMdProductOutOfStorageExcelUploadErrorDvo> errors
    ) {}
}
