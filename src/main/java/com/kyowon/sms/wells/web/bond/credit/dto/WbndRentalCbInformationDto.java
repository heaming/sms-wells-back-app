package com.kyowon.sms.wells.web.bond.credit.dto;

import io.swagger.annotations.ApiModel;

public class WbndRentalCbInformationDto {

    @ApiModel("WbndRentalCbInformationDto-SearchContractPresentStateReq")
    public record SearchContractPresentStateReq(
        String keyDiv, /*식별자구분*/
        String rsdnNo, /*식별번호*/
        String cstNo, /*고객번호*/
        String inqRsnCd, /*조회사유*/
        String inqwtcnRsnCd, /*조회동의*/
        String insAdrZP1, /*우편번호1*/
        String insAdrZP2, /*우편번호2*/
        String insAdrWAD1, /*주소*/
        String insAdrWAD2, /*상세주소1*/
        String insAdrWAD3, /*상세주소2*/
        String insHpNo1, /*핸드폰번호1*/
        String insHpNo2, /*핸드폰번호2*/
        String insHpNo3, /*핸드폰번호3*/
        String insHomNo1, /*전화번호1*/
        String insHomNo2, /*전화번호2*/
        String insHomNo3 /*전화번호3*/

    ) {}
}
