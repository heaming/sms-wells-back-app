package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <pre>
 * 월별입금/환불금액조회 DTO
 * </pre>
 *
 * @author
 * @since 2023-05-08
 */
public class WwdbDepositRefundItemizationDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 월별입금/환불금액 조회 Request Dto
    @ApiModel(value = "WwdbDepositRefundItemizationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstNo, // 고객번호
        @NotBlank
        String perfY, // 실적년
        @NotBlank
        String perfM, // 실적월
        String perfYm // 실적년월
    ) {
        public SearchReq {
            perfYm = perfY + "" + perfM + "";
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별입금/환불금액 조회 Result Dto
    @ApiModel(value = "WwdbDepositRefundItemizationDto-SearchRes")
    public record SearchRes(
        String vacBnkCd, // 입금금액
        String vacBnkNm // 환불금액
    ) { }
}
