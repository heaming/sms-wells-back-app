package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * 배정관리 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
public class WsncAsAssignPsDto {

    @ApiModel(value = "WsncAsAssignPsDto-SearchTotalCustomerReq")
    public record SearchTotalCustomerReq(
        String year,
        String pdGrpCd,
        String pdCd
    ) {}

    @ApiModel(value = "WsncAsAssignPsDto-SearchTotalCustomerRes")
    public record SearchTotalCustomerRes(
        String filtSellTpCd,
        String mngtYm,
        String yyyy,
        String typ,
        String typNm,
        String acol1,
        String acol2,
        String acol3,
        String acol4,
        String acol5,
        String acol6,
        String acol7,
        String acol8,
        String acol9,
        String acol10,
        String acol11,
        String acol12,
        int tcnt,
        int per
    ) {}

    @ApiModel(value = "WsncAsAssignPsDto-SearchProductServicesReq")
    public record SearchProductServicesReq(
        String wkExcnDt,
        String mngrDvCd,
        String pdGrpCd,
        String pdCd
    ) {}

    @ApiModel(value = "WsncAsAssignPsDto-SearchProductServicesRes")
    public record SearchProductServicesRes(
        String wkExcnDt,
        String svBizHclsfCd,
        String svBizHclsfNm,
        String acol1,
        String acol2,
        String acol3,
        String acol4,
        String acol5,
        String acol6,
        String acol7,
        String acol8,
        String acol9,
        String acol10,
        String acol11,
        String acol12,
        String totalCount
    ) {}
}
