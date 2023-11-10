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
}
