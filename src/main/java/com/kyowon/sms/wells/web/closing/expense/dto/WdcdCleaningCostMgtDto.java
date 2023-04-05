package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WdcdCleaningCostMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리
    @Builder
    @ApiModel(value = "WwdcdMarketableSecuritiesExcdMgtDto-SearchRes")
    public record SearchReq(
        String flag,
        @NotBlank
        String fstRgstDtm,
        String clinrNm,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리
    @Builder
    @ApiModel(value = "WwdcdMarketableSecuritiesExcdMgtDto-SearchRes")
    public record SearchRes(
        String clinrRgno, /*청소원등록번호*/
        String rcpYm, /*접수년월*/
        String fstRgstDtm, /*등록일시*/
        String fnlMdfcDtm, /*변경일시*/
        String clinrNm, /*청소원*/
        String bldCd, /*빌딩코드*/
        String bldNm, /*빌딩 명*/
        String aplcDt, /*신청일*/
        String aplcnsNm, /*신청자*/
        String cntrwApnFileId, /*계약서*/
        String cntrLroreApnFileId, /*계약해지원*/
        String idfApnFileId, /*신분증사본*/
        String bnkbApnFileId, /*통장사본*/
        String fmnCoSpptAmt, /*회사지원금*/
        String clinrFxnAmt, /*고정금*/
        String taxDdctam, /*세금공제*/
        String amt, /*실지급액*/
        String wrkStrtdt, /*근무시작일자*/
        String wrkEnddt, /*근무종료일자*/
        String workStatus, /*근무여부*/
        String rrnoEncr, /*주민등록번호*/
        String telNum, /*연락처*/
        String address, /*주민등록상의주소*/
        String bnkCd, /*은행코드*/
        String bnkNm, /*은행명*/
        String acnoEncr /*계좌번호*/
    ) {
    }
}
