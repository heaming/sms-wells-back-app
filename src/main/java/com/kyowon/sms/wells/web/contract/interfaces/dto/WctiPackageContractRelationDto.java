package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiPackageContractRelationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 패키지연관 계약건 Search Request Dto
    @ApiModel("WctiPackageContractRelationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO, //계약번호(필수)
        @NotBlank
        String CNTR_SN //계약일련번호(필수)
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 패키지연관 계약건 Search Result Dto
    @ApiModel("WctiPackageContractRelationDto-SearchRes")
    public record SearchRes(
        String DV_NM,			//구분명
        String SELL_TP_NM,		//판매유형명
        String RLTD_CNTR_NO,	//관련계약번호
        String RLTD_CNTR_SN,	//관련계약일련번호
        String PD_CD,			//상품코드
        String PD_NM,			//상품명
        String CNTR_REL_DTL_CN,	//계약관계상세내용
        String CNTR_CNFMDT,		//계약확정일자
        String DUEDT,			//예정일자
        String CAN_DT			//취소일자
    ) {}
}
