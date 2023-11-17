package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * 자동 선납할인제외 관리 DTO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-20
 */
public class WwdbAutoPrepaymentDiscountExcludeMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 자동 선납할인제외 관리 조회 Request Dto
    public record SearchReq(
        String prmDscExcdStrtYm, // 선납할인제외시작년월
        String prmDscExcdEndYm, // 선납할인제외종료년월
        String cntr,
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String baseYm // 기준년월
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 자동 선납할인제외 관리 조회 Result Dto
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
    ) { }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 자동 선납할인제외 관리 계약정보 조회 Request Dto
    public record SearchContractReq(
        String cntrNo,
        String cntrSn
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 자동 선납할인제외 관리 계약정보 조회 Result Dto
    public record SearchContractRes(
        String cntrNo, //계약번호
        String cntrSn, //일련번호
        String cstKnm, //고객명
        String sellTpCd, //판매유형
        String pdCd, //상품코드
        String pdNm //상품명
    ) { }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 자동 선납할인제외 관리 저장 Request Dto
    public record SaveReq(
        String rowState,
        @NotBlank
        String cntr, //계약
        String cntrNo, //계약번호
        String cntrSn, //일련번호
        @NotBlank
        String prmDscExcdStrtYm, //선납제외시작월
        String prmDscExcdEndYm //선납제외종료월
    ) { }

}
