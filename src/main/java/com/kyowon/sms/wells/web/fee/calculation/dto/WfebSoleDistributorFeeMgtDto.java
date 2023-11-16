package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfebSoleDistributorFeeMgtDto {
    public record SearchPerformanceReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        String feeTcntDvCd, /* 차수 */
        @NotBlank
        String strtYm, /* 시작일 */
        @NotBlank
        String endYm, /* 종료일 */
        String cancelStrtYm, /* 취소시작일 */
        String cancelEndYm /* 취소종료일 */
    ) {}

    public record Performance(
        String baseYm, /* 기준년월 */
        String coCdNm, // 업체명
        String brchNm, // 지점명
        String prtnrKnm, // 판매자
        String prtnrNo, // 번호
        String cntrNo, // 계약상세번호
        String cstKnm, // 고객성명
        String basePdCd, // 상품코드
        String pdNm, // 상품명
        String sellTpCd, /* 판매유형 */
        String sellDscDvCd, /* 할인구분 */
        String sellDscrCd, /* 할인유형 */
        String sellDscTpCd, /* 할인제도 */
        String sppDvCd, /* 결합구분 */
        String svPdTpCd, /* 용도구분 */
        String mgNm, /* 관리유형 */
        String bfsvcPrdCd, /* 방문주기 */
        String rcpdt, // 접수일자
        String slDt, // 매출일자
        String canDt, // 취소일자
        Integer perfVal, // 수수료
        Integer ackmtPerfCt, // 신규건수
        Integer ackmtPerfAmt // 인정실적
    ) {}

     public record SearchFeeReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        String feeTcntDvCd /* 차수 */
    ) {}
    public record Fee(
        String baseYm, /* 실적년월 */
        String feeTcntDvCd, /* 차수 */
        String coCd, /* 회사코드 */
        String coCdNm, /* 업체명 */
        String ogCd, /* 소속 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 명 */
        Integer cnt, /* 실적 */
        Long amtW050001, /* 기본수수료 */
        Long amtW050002, /* 장려수수료 */
        Long amtW050003, /* 인센티브 */
        Long amtW050020, /* 재지급 */
        Long amtW050004, /* 분기성과 */
        Long amt01, /* RDS */
        Long amt08 /* 환수되물림 */
    ) {}

    public record SaveReq(
        List<Fee> changedRows
    ) {};
    public record CreateReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        String feeTcntDvCd /* 차수 */
    ) {};
}
