package com.kyowon.sms.wells.web.closing.standard.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdcyCloseHourBulkRegDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 마감시간 일괄등록
    @ApiModel("WdcyCloseHourBulkRegDto-CreateReq")
    public record CreateReq(

        @NotBlank
        String kwGrpCoCd,
        String clBizTpCd,
        String clPsicNo,
        String prtnrNo,

        // 생성일자
        String crtDt,
        String crtDtTmFrom,
        String crtDtTmTo,
        String crtDtPerfDtDvVal,

        String rentalRcpClDdTmFrom,
        String rentalRcpClDdTmTo,
        String rentalRcpClDdPerfDtDvVal,

        // 마감일자
        String clDt,
        String ddClDtTmFrom,
        String ddClDtTmTo,
        String ddClPerfDtDvVal,

        String rentalRcpClNxdTmForm,
        String rentalRcpClNxdTmTo,
        String rentalRcpClNxdPerfDtDvVal,

        // 말일까지 일자
        String spayRcpClDdTmFrom,
        String spayRcpClDdTmTo,
        String spayRcpClDdPerfDtDvVal,

        String spayRcpClNxdTmFrom,
        String spayRcpClNxdTmTo,
        String spayRcpClNxdPerfDtDvVal
    ) {
    }
}
