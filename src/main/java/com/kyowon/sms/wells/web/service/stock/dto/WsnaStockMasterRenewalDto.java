package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-U-0279M01 재고마스터갱신
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.03.13
 */
public class WsnaStockMasterRenewalDto {

    @ApiModel("WsnaStockMasterRenewalDto-EditReq")
    public record EditReq(
        @NotBlank
        String mngtYm, // 관리년월
        @NotBlank
        String wareDvCd, // 창고구분코드
        @NotBlank
        String rnwOjAcd // 갱신대상
    ) {}

}
