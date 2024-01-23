package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnbServiceProcDetailListDto {

    @ApiModel(value = "WsnbServiceProcDetailListDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String cstSvAsnNo,
        @NotBlank
        String gubun

    ) {}

    @ApiModel(value = "WsnbServiceProcDetailListDto-SaveCttNwRgstReq")
    @Builder
    public record SaveCttNwRgstReq(
        @NotNull
        String cntrNo,// 계약번호
        @NotNull
        String cntrSn, // 계약일련번호
        @NotNull
        String cttRcpDtm, // 컨택일[컨택접수일시]
        @NotNull
        String cttDuedt, // 방문예정일[컨택예정일]
        @NotNull
        String cttMoCn, // 전달사항[컨택요청내용]
        String cttOjId, // 컨택대상ID
        String cttSn, // 컨택일련번호
        @NotNull
        String wkKnd, // 작업종류
        String beforeRcpDtm, // 기등록된 컨택일
        String beforeDuedt, // 기등록된 방문예정일
        String beforeMoCn // 기등록된 메모
    ){}

    @ApiModel(value = "WsnbServiceProcDetailListDto-CttIzReq")
    public record CttIzReq(
        String cntrNo,
        String cntrSn,
        String cttOjId,
        String cttSn,
        String cttMoCn,
        String cttRcpDtm,
        String cttDuedt
    ) {}

    @ApiModel(value = "WsnbServiceProcDetailListDto-SaveWkCanRgstReq")
    public record SaveWkCanRgstReq(
        @NotNull
        String canDv, // 취소구분
        @NotNull
        String canRson, // 취소사유
//        @NotNull
//        String charFwYn, // 문자발송여부
        @NotNull
        String canRsonDtlIz, // 취소사유 상세내역
        @NotNull
        String cstSvAsnNo,
        String cntrNo,
        String cntrSn
    ){}
}
