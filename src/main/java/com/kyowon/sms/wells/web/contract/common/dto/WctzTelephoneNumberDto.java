package com.kyowon.sms.wells.web.contract.common.dto;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WctzTelephoneNumberDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약공통전화번호 Search Result Dto
    @ApiModel("WctcSalesLimitsDto-SearchTnoRes")
    public record SearchTnoRes(
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno
    ) {
        public SearchTnoRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            tno = locaraTno + CtContractConst.TNO_DELIM + exnoEncr + CtContractConst.TNO_DELIM + idvTno;
        }
    }
    @ApiModel("WctcSalesLimitsDto-SearchMpnoRes")
    public record SearchMpnoRes(
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno
    ) {
        public SearchMpnoRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            mpno = cralLocaraTno + CtContractConst.TNO_DELIM + mexnoEncr + CtContractConst.TNO_DELIM + cralIdvTno;
        }
    }
}
