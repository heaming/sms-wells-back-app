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
        String lcncde,
        String lciuse,
        String gdsCd,
        String farmYn,
        String itemNm,
        String custCd,
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
        public SearchJsonRes {
            hnoNo = locaraTno + (StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr)
                + idvTno;
            mngHpNo = cralLocaraTno + (StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr)
                + cralIdvTno;
        }
    }

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
