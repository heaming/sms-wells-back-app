package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsniRegistrationBarCodeDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniRegistrationBarCodeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniRegistrationBarCodeDto-SearchRes")
    public record SearchRes(
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
        public SearchRes {
            hnoNo = locaraTno + (StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr)
                + idvTno;
            mngHpNo = cralLocaraTno + (StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr)
                + cralIdvTno;
        }
    }

}
