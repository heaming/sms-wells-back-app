package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfebB2bFeeMgtDto {
    public record SearchPerformanceReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String strtYm,
        @NotBlank
        String endYm,
        String feeTcntDvCd,
        String cancelStrtYm,
        String cancelEndYm
    ) {}

    public record Performance(
        String baseYm,
        String coCdNm, // 업체명
        String prtnrKnm, // 판매자
        String prtnrNo, // 번호
        String cntrNo, // 계약상세번호
        String cstKnm, // 고객성명
        String basePdCd, // 상품코드
        String pdNm, // 상품명
        String sellDscDvCd,
        String sellDscrCd,
        String sellDscTpCd,
        String sppDvCd,
        String svPdTpCd,
        String mgNm,
        String bfsvcPrdCd,
        String rcpdt, // 접수일자
        String slDt, // 매출일자
        String canDt, // 취소일자
        Integer perfVal, // 수수료
        Integer ackmtPerfCt // 신규건수
    ) {}

     public record SearchFeeReq(
        @NotBlank
        String perfYm,
        String feeTcntDvCd
    ) {}
    public record Fee(
        String baseYm,
        String feeTcntDvCd,
        String coCd,
        String coCdNm,
        String ogCd,
        String prtnrNo,
        Integer cnt,
        Long amtW040001,
        Long amtW040005,
        Long amtW040004,
        Long amtW040020,
        Long amtW040003,
        Long amt01,
        String amt01Cn,
        Long amt08
    ) {}

    public record SaveReq(
        List<Fee> changedRows
    ) {};
    public record CreateReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        String feeTcntDvCd
    ) {};
}
