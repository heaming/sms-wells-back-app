package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfebB2bFeeMgtDto {
    public record SearchPerformanceReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        @NotBlank
        String strtYm, /* 시작년월 */
        @NotBlank
        String endYm, /* 종료년월 */
        String feeTcntDvCd, /* 차수 */
        String cancelStrtYm, /* 취소시작일 */
        String cancelEndYm /* 취소종료일 */
    ) {}

    public record Performance(
        String baseYm, /* 기준년월 */
        String coCdNm, // 업체명(지구명)
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
        Integer ackmtPerfCt // 신규건수
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
        String ogCd, /* 소속코드 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 업체명 */
        Integer cnt, /* 실적 */
        Long amtW040001, /* 기본수수료 */
        Long amtW040005, /* 알선수수료 */
        Long amtW040004, /* 프로모션 */
        Long amtW040020, /* 재지급 */
        Long amtW040003, /* 인센티브 */
        Long amt01, /* RDS */
        String amt01Cn, /* 보증예치금수정사유 */
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
