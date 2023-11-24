package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * K-W-SV-U-0116M01 일자별 설치재고 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.07.11
 */
public class WsnaInstallationStockPsByDayDto {

    @ApiModel(value = "WsnaInstallationStockPsByDayDto-SearchReq")
    public record SearchReq(
        @NotNull
        String baseDt,
        String pdGdCd,
        String itmKndCd,
        String pdCd,
        String svCnr
    ) {}

    @ApiModel(value = "WsnaInstallationStockPsByDayDto-SearchResPsWareStr")
    public record SearchResPsWareStr(
        String itmPdCd,
        Integer qtyPajuSum,
        Integer qtyCenterSum,
        Integer qtyEngSum
    ) {}

    @ApiModel(value = "WsnaInstallationStockPsByDayDto-SearchResCenter")
    public record SearchResCenter(
        String ogId,
        String ogNm,
        String pdCd,
        String pdNm,
        Integer pajuQty,
        Integer prvMng,
        Integer engQty,
        Integer stockTotal,
        Integer stockdate1,
        Integer stockdate2,
        Integer stockdate3,
        Integer stockdate4,
        Integer stockdate5,
        Integer stockdate6,
        Integer stockdate7,
        Integer stockdate8,
        Integer stockdate9,
        Integer stockdate10,
        Integer stockdate11,
        Integer stockdate12,
        Integer stockdate13,
        Integer stockdate14,
        Integer installdate1,
        Integer installdate2,
        Integer installdate3,
        Integer installdate4,
        Integer installdate5,
        Integer installdate6,
        Integer installdate7,
        Integer installdate8,
        Integer installdate9,
        Integer installdate10,
        Integer installdate11,
        Integer installdate12,
        Integer installdate13,
        Integer installdate14,
        Integer istTotal
    ) {}

    @ApiModel(value = "WsnaInstallationStockPsByDayDto-SearchResPd")
    public record SearchResPd(
        String pdCd,
        String pdNm,
        Integer pajuQty,
        Integer centerQty,
        Integer engQty,
        Integer eotQty,
        Integer stocQty,
        Integer stockdate1,
        Integer stockdate2,
        Integer stockdate3,
        Integer stockdate4,
        Integer stockdate5,
        Integer stockdate6,
        Integer stockdate7,
        Integer stockdate8,
        Integer stockdate9,
        Integer stockdate10,
        Integer stockdate11,
        Integer stockdate12,
        Integer stockdate13,
        Integer stockdate14,
        Integer installdate1,
        Integer installdate2,
        Integer installdate3,
        Integer installdate4,
        Integer installdate5,
        Integer installdate6,
        Integer installdate7,
        Integer installdate8,
        Integer installdate9,
        Integer installdate10,
        Integer installdate11,
        Integer installdate12,
        Integer installdate13,
        Integer installdate14,
        Integer istTotal
    ) {}
}
