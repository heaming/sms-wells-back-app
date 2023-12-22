package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdccProductAccountHomeCardDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 홈카드 계정순증 Search Result Dto
    @Builder
    @ApiModel("WdccProductAccountHomeCardDto-SearchRes")
    public record SearchRes(
        String sumSumMmAgrgCnt, /*전체*/
        String rentlSum, /*렌탈*/
        String leaseSum, /*리스*/
        String rgldvSum, /*구독*/
        String membsSum /*멤버십*/
    ) {}

    @Builder
    @ApiModel("WdccProductAccountHomeCardDto-PercentageSearchRes")
    public record PercentageSearchRes(
        String sumAllAgrgCnt, /* 계정 누적정보 */
        String sumSumMmAgrgCnt, /* 당월순증합계 */
        String etExitRate /* 순수이탈율 */
    ) {}
}
