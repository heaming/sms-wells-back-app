package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * K-W-SV-U-0112M01 자재보유현
 * </pre>
 *
 * @author segi 홍세기
 * @since 2023-07-27
 */
public class WsnaMaterialsHavePresentStateDto {

    @Builder
    @ApiModel(value = "WsnaMaterialsHavePresentStateDto-SearchReq")
    public record SearchReq(

        String stFromYmd,
        String edToYmd,
        String wareNo,
        String wareDtlDvCd,
        String itmGdCd,
        String useYn,
        String itmKndCd,
        String itmPdCd,
        String csmbPdCdStrt,
        String csmbPdCdEnd,
        String sapMatCdStrt,
        String sapMatCdEnd,
        String wareDvCd,
        String csmbPdCd
    ) {}

    @ApiModel(value = "WsnaMaterialsHavePresentStateDto-SearchRes")
    public record SearchRes(
        String wareNo,
        String wareNm,
        String hgrWareNo,
        String orderNo,
        String onCnt1,
        String onQty1,
        String onCnt2,
        String onQty2,
        String onCnt3,
        String onQty3

    ) {}

}
