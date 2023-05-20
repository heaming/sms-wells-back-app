package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

public class WwdbAutoPrepaymentDiscountExcludeMgtDto {
    public record SearchReq(
        String prmDscExcdStrtYm,
        String prmDscExcdEndYm,
        String cntr,
        String cntrNo,
        String cntrSn,
        String baseYm
    ) {

    }

    public record SearchRes(
        String cntr,
        String cntrNo, //계약번호
        String cntrSn, //일련번호 
        String cstKnm, //고객명
        String sellTpCd, //판매유형
        String pdCd, //상품코드
        String pdNm, //상품명
        String prmDscExcdStrtYm, //선납제외시작일
        String prmDscExcdEndYm, //선납제외종료일  
        //선납시작일
        String slCtrAmt, //조정값
        String fstRgstDtm, //등록일시
        String fstRgstUsrId, //등록자 id
        String fnlMdfcDtm, //수정일시
        String fnlMdfcUsrId, //수정자 id  
        String prmStrtDate, //선납시작월 
        String fstRgstUsrNm,
        String fnlMdfcUsrNm
    ) {

    }

    public record SearchContractReq(
        String cntrNo,
        String cntrSn
    ) {

    }
    public record SearchContractRes(
        String cntrNo, //계약번호
        String cntrSn, //일련번호 
        String cstKnm, //고객명
        String sellTpCd, //판매유형
        String pdCd, //상품코드
        String pdNm //상품명
    ) {

    }

    public record SaveReq(
        String rowState,
        @NotBlank
        String cntr, //계약 
        String cntrNo, //계약번호
        String cntrSn, //일련번호
        @NotBlank
        String prmDscExcdStrtYm, //선납제외시작월
        String prmDscExcdEndYm //선납제외종료월
    ) {

    }

}
