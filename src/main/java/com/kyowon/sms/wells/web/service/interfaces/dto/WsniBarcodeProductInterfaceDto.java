package com.kyowon.sms.wells.web.service.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsniBarcodeProductInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    @ApiModel(value = "WsniRegistrationBarcodeInterfaceDto-SearchCustReq")
    public record SearchCustReq(
        @NotBlank
        String barcode
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchRes")
    public record SearchRes(
        String bcNo,
        String basePdCd,
        String uswyDvCd,
        String pdctPdCd,
        String farmYn,
        String itemNm,
        String cntrNo,
        String cntrSn,
        String custNm,
        String hnoNo,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String csmrYr,
        String csmrCd,
        String addr,
        String zipno,
        String empId,
        String empNm,
        String deptNm,
        String mngHpNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String vstDt,
        String mngTyp,
        String mngCyc,
        String dbldNm,
        String filterYn,
        String rnk
    ) {
        public SearchRes {
            hnoNo = locaraTno + (StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr)
                + idvTno;
            mngHpNo = cralLocaraTno + (StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr)
                + cralIdvTno;
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchJsonRes")
    public record SearchJsonRes(
        @JsonProperty(value = "BC_NO")
        String bcNo,
        @JsonProperty(value = "CODE")
        String code,
        @JsonProperty(value = "MESSAGE")
        String message,
        @JsonProperty(value = "BASE_PD_CD")
        String basePdCd,
        @JsonProperty(value = "USWY_DV_CD")
        String uswyDvCd,
        @JsonProperty(value = "PDCT_PD_CD")
        String pdctPdCd,
        @JsonProperty(value = "FARM_YN")
        String farmYn,
        @JsonProperty(value = "ITEM_NM")
        String itemNm,
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,
        @JsonProperty(value = "CUST_NM")
        String custNm,
        @JsonProperty(value = "HNO_NO")
        String hnoNo,
        @JsonProperty(value = "ADDR")
        String addr,
        @JsonProperty(value = "ZIPNO")
        String zipno,
        @JsonProperty(value = "EMP_ID")
        String empId,
        @JsonProperty(value = "EMP_NM")
        String empNm,
        @JsonProperty(value = "DEPT_NM")
        String deptNm,
        @JsonProperty(value = "MNG_HP_NO")
        String mngHpNo,
        @JsonProperty(value = "VST_DT")
        String vstDt,
        @JsonProperty(value = "MNG_TYP")
        String mngTyp,
        @JsonProperty(value = "MNG_CYC")
        String mngCyc,
        @JsonProperty(value = "DBLD_NM")
        String dbldNm,
        @JsonProperty(value = "FILTER_YN")
        String filterYn
    ) {}

    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchCustRes")
    public record SearchCustRes(
        String istDt,
        String useMonth,
        String managerName,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String nextSchedule,
        String rentalFee,
        //String serviceTypeName,
        //String serviceTime,
        //String serviceName,
        String deviceName,
        String managerTel
    ) {
        public SearchCustRes {
            if (StringUtils.isNotEmpty(mexnoEncr)) {
                mexnoEncr = DbEncUtil.dec(mexnoEncr);
                managerTel = cralLocaraTno.concat("-").concat(mexnoEncr).concat("-").concat(cralIdvTno);
            }
        }
    }

    @Builder
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchCustJsonRes")
    public record SearchCustJsonRes(
        @JsonProperty("IST_DT")
        String istDt,
        @JsonProperty("USE_MONTH")
        String useMonth,
        @JsonProperty("MANAGER_NAME")
        String managerName,
        @JsonProperty("MANAGER_TEL")
        String managerTel,
        @JsonProperty("NEXT_SCHEDULE")
        String nextSchedule,
        @JsonProperty("RENTAL_FEE")
        String rentalFee,
        @JsonProperty("DEVICE_NAME")
        String deviceName,
        @JsonProperty("SERVICE_INFO")
        List<WsniBarcodeProductInterfaceDto.SearchCustServiceJsonRes> serviceInfo

    ) {}

    @ApiModel("WsniBarcodeProductInterfaceDto-SearchCustServiceJsonRes")
    public record SearchCustServiceJsonRes(
        @JsonProperty("SERVICE_TYPE_NAME")
        String serviceTypeName,
        @JsonProperty("SERVICE_TIME")
        String serviceTime,
        @JsonProperty("SERVICE_NAME")
        String serviceName
    ) {}
}
