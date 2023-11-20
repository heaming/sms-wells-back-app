package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 지로 수신처 관리 DTO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-05-19
 */
public class WwdbGiroPlaceReceivedMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로 수신처 조회 Request Dto
    @ApiModel(value = "WwdbGiroPlaceReceivedDto-FindReq")
    public record FindReq(
        String cntrNo,
        String cntrSn
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로 수신처 조회 Result Dto
    @ApiModel(value = "WwdbGiroPlaceReceivedDto-FindRes")
    public record FindRes(
        String cntrNo,
        String cntrSn,
        String giroBizDvCd, //지로업무구분코드
        String giroBizTpCd, //지로업무유형코드 판매유형
        String cstFnm, //고객명
        String zip, //우편번호
        String basAdr, //기본주소
        String dtlAdr, //상세주소
        String fstRgstUsrId, //업무담당
        String giroPlrcvRgstDt, //일시
        String giroPlrcvAdrId //주소ID

    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로 수신처 저장 Request Dto
    @ApiModel(value = "WwdbGiroPlaceReceivedDto-SaveReq")
    public record SaveReq(
        String state,
        String cntrNo,
        String cntrSn,
        String giroBizDvCd, //지로업무구분코드
        String giroBizTpCd, //지로업무유형코드 판매유형
        @NotBlank
        String cstFnm, //고객명
        //        String zip, //우편번호
        //        String adrDvCd, //주소구분코드
        //        String basAdr, //기본주소
        //        String dtlAdr, //상세주소
        String fstRgstUsrId, //업무담당
        String giroPlrcvAdrId, //주소ID
        String giroPlrcvRgstDt //등록일자

    ) {}
}
