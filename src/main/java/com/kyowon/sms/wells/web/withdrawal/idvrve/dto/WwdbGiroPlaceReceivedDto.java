package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WwdbGiroPlaceReceivedDto {

    @ApiModel(value = "WwdbGiroPlaceReceivedDto-FindReq")
    public record FindReq(
        String cntrNo,
        String cntrSn
    ) {

    }

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
