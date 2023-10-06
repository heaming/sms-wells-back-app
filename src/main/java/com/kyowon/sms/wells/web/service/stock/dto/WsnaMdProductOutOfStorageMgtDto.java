package com.kyowon.sms.wells.web.service.stock.dto;

import com.sds.sflex.common.utils.DbEncUtil;

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

        String firstSppGb,

        String selCnt,

        String ostrCnfmDt,

        String prtnrBzsCd,

        String cntrDtlNo,

        String rcgvpKnm,

        String serialNo,

        String cralLocaraTno,

        String mexnoEncr,

        String cralIdvTno

    ) {
        public SearchReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SaveReq")
    public record SaveReq() {

    }
}
