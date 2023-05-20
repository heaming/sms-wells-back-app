package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsniBarcodeProductInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchCustReq")
    public record SearchCustReq(
        @NotBlank
        String barcode
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchRes")
    public record SearchRes(
        int resultcode,
        int regi
    ) {}

    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchJsonRes")
    public record SearchJsonRes(
        String resultCode,
        String regi,
        String resultMessage
    ) {}

    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchCustRes")
    public record SearchCustRes(
        String cntrNo,
        int cntrSn,
        String istDt,
        String useMonth,
        String managerName,
        String managerTel,
        String nextSchedule,
        String rentalFee,
        String deviceName,
        String serviceTypeName,
        String serviceTime,
        String serviceName,

        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {
        public SearchCustRes {
            if(StringUtils.isNotEmpty(mexnoEncr)){
                mexnoEncr = DbEncUtil.dec(mexnoEncr);
                managerTel = cralLocaraTno.concat("-").concat(mexnoEncr).concat("-").concat(cralIdvTno);
            }
        }
    }

    @ApiModel(value = "WsniBarcodeProductInterfaceDto-SearchCustJsonRes")
    public record SearchCustJsonRes(
        String istDt,
        String useMonth,
        String managerName,
        String managerTel,
        String nextSchedule,
        String rentalFee,
        String deviceName,
        String serviceTypeName,
        String serviceTime,
        String serviceName
    ) {}
}
