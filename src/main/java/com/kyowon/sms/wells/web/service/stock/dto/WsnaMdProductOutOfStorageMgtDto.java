package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageSaveProductDvo;
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
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,

        @NotBlank
        String cntrNo,

        @NotBlank
        String cntrSn,

        @NotBlank
        String svBizDclsfCd,

        @NotBlank
        String svBizHclsfCd,

        @NotBlank
        String prtnrNo,

        @NotBlank
        String ogTpCd,

        @NotBlank
        String ogId,

        @NotBlank
        String pdctPdCd,

        @NotBlank
        String wkWareNo,

        String sellTpCd,

        String istDt,

        List<WsnaMdProductOutOfStorageSaveProductDvo> products
    ) {}
}
