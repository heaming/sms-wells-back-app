package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdbDepositDto {

    // *********************************************************
    // Object Dto
    // *********************************************************

    @ApiModel("WwdbDepositDto-SearchReq")
    @Builder
    public record SearchReq(
        String searchDv, // 조회구분 (1 : 입금내역, 2 : 당월납부내역)
        String cntrNo, /*계약번호*/
        String sellTpCd, /*판매유형코드(업무구분)*/
        String sellTpDtlCd /*판매유형상세코드(업무유형)*/
    ) {}

    @ApiModel("WwdbDepositDto-SaveReq")
    public record SaveReq(
        String dpDt, /*입금일자*/
        String perfDt, /*실적년도*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String sellTpCd, /*업무구분*/
        String sellTpDtlCd, /*업무유형*/
        String rveCd, /*수납코드*/
        String dpTpCd, /*입금구분*/
        String dpAmt, /*입금금액*/
        String cardNo, /*카드번호*/
        String istmMcn, /*할부개월*/
        String aprno, /*승인번호*/
        String cdonrNm, /*카드주명*/
        String hdwrRgstYn, /*수기카드*/
        String dpMesCd, /*입금구분*/
        String prtnrNo, /*파트너번호*/
        String ogTpCd /*조직유형코드*/

    ) {}

    @ApiModel("WwdbDepositDto-SaveRes")
    public record SaveRes(
        String rveNo, /*수납번호*/
        String procsRsCd, /*결과코드*/
        String procsRsCntn /*결과내용*/

    ) {}
}
