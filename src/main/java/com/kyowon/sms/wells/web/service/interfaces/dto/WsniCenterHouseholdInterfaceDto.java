package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

public class WsniCenterHouseholdInterfaceDto {
    @ApiModel(value = "WsniCenterHouseholdInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        Integer cntrSn
    ) {

    }
    @ApiModel(value = "WsniCenterHouseholdInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        Integer cntrSn,
        @JsonProperty(value = "SALE_PRTNR_NO")
        String salePrtnrNo,
        @JsonProperty(value = "SALE_HP_TEL1")
        String saleHpTel1,
        @JsonProperty(value = "SALE_HP_TEL2")
        String saleHpTel2,
        @JsonProperty(value = "SALE_HP_TEL3")
        String saleHpTel3,
        @JsonProperty(value = "CUST_NM")
        String custNm,
        @JsonProperty(value = "CUST_BIZNO")
        String custBizno,
        @JsonProperty(value = "CUST_HP_TEL1")
        String custHpTel1,
        @JsonProperty(value = "CUST_HP_TEL2")
        String custHpTel2,
        @JsonProperty(value = "CUST_HP_TEL3")
        String custHpTel3,
        @JsonProperty(value = "CUST_AR_TEL1")
        String custArTel1,
        @JsonProperty(value = "CUST_AR_TEL2")
        String custArTel2,
        @JsonProperty(value = "CUST_AR_TEL3")
        String custArTel3,
        @JsonProperty(value = "CUST_ZIP")
        String custZip,
        @JsonProperty(value = "CUST_ADDR1")
        String custAddr1,
        @JsonProperty(value = "CUST_ADDR2")
        String custAddr2,
        @JsonProperty(value = "PDCT_PD_CD")
        String pdctPdCd,
        @JsonProperty(value = "SELL_TP_CD")
        String sellTpCd,
        @JsonProperty(value = "SV_HSHD_NO")
        String svHshdNo,
        @JsonProperty(value = "FXN_PRTNR_NO")
        String fxnPrtnrNo,
        @JsonProperty(value = "VST_NMN_N")
        Integer vstNmnN,
        @JsonProperty(value = "PD_ABBR_NM")
        String pdAbbrNm,
        @JsonProperty(value = "SELL_TP_NM")
        String sellTpNm,
        @JsonProperty(value = "FXN_PRTNR_NM")
        String fxnPrtnrNm
    ) {
        public SearchRes {
            if (StringUtils.isNotEmpty(saleHpTel2))
                saleHpTel2 = DbEncUtil.dec(saleHpTel2);
            if (StringUtils.isNotEmpty(custHpTel2))
                custHpTel2 = DbEncUtil.dec(custHpTel2);
            if (StringUtils.isNotEmpty(custArTel2))
                custArTel2 = DbEncUtil.dec(custArTel2);
        }
    }
}
