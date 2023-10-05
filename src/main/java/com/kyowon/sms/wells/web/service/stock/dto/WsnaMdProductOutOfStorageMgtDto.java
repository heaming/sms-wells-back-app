package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * MD 상품 출고관리
 * </pre>
 *
 * @author junggheejin
 * @since 2023.09.24
 */
public class WsnaMdProductOutOfStorageMgtDto {

    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String findGb,

        String selCnt,

        String vstFshDt,

        String prtnrBzsCd,

        String firstSppGb,

        String cntrNo,

        String cstNm,

        String cralIdvTno,

        String serialNo
    ) {}

    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SaveReq")
    public record SaveReq() {

    }
}
