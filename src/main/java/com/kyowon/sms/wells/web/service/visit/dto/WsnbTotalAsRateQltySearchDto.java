package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0248M01 총 A/S율 현황(품질)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.01
 */
public class WsnbTotalAsRateQltySearchDto {

    @ApiModel(value = "WsnbTotalAsRateQltySearchDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYear, //기준년월
        String taskTypeCd, //중분류(서비스유형)
        String badDvCd, //소분류(불량구분)
        String pdGrpCd, //상품그룹
        String pdCd //상품코드
    ) {}

    @ApiModel(value = "WsnbTotalAsRateQltySearchDto-SearchRes")
    public record SearchRes(
        String gbNm,
        int totalCnt,
        int col01,
        int col02,
        int col03,
        int col04,
        int col05,
        int col06,
        int col07,
        int col08,
        int col09,
        int col10,
        int col11,
        int col12
    ) {}

}
