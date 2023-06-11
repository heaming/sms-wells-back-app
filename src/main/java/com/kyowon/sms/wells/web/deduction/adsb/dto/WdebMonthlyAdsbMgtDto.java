package com.kyowon.sms.wells.web.deduction.adsb.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdebMonthlyAdsbMgtDto {
    @Builder
    @ApiModel("WdeeRealTimeDlqAdamtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String redfAdsbOcYm, // 발생년월
        String pstnDvCd, // 직급구분
        String ogTpCd, // 조직유형
        String prtnrNo, // 파트너번호
        String cltnYn // 해약여부
    ) {}

    @ApiModel("WdeeRealTimeDlqAdamtDto-SearchPartnerhRes")
    public record SearchPartnerhRes(
        String redfAdsbOcYm, // 발생년월
        String perfYm, // 실적년월
        String ogCd, // 소속코드
        String prtnrNo, // 파트너번호
        String prtnrKnm, // 파트너성명
        String rsbDvNm, // 직급
        String cltnYm, // 해약년월
        String redfAdsbDvNm, // 지급구분
        String prAdsbAmt, // 재지급액.개인
        String ogAdsbAmt, // 재지급액.조직
        String sumAdsbAmt // 재지급액 계
    ) {}

    @ApiModel("WdeeRealTimeDlqAdamtDto-SearchContractRes")
    public record SearchContractRes(
        String ocDt, // 처리일자
        String perfDt, // 귀속일자(실적일자)
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrNoSn, // 계약상세번호
        String prtnrNo, // 파트너번호.판매자
        String prtnrKnm, // 파트너성명.판매자
        String sellPrtnrAdsbAmt, // 재지급액.판매자
        String brchPrtnrNo, // 파트너번호.지점장
        String brchPrtnrKnm, // 파트너성명.지점장
        String brchAdsbAmt // 재지급액.지점장
    ) {}
}
