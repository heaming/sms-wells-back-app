package com.kyowon.sms.wells.web.service.common.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnyCustomersDto {
    @ApiModel(value = "WsnyCustomerBaseInformationDto-SearchReq")
    public record SearchReq(
        String cstNm,
        String cntrNo,
        String cntrSn,
        String mpNo,
        String telNo,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String bzrno,
        String bcNo,
        String canCtIncYn
    ) {
        public SearchReq {
            exnoEncr = DbEncUtil.enc(exnoEncr);
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnyCustomerBaseInformationDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String cstNm,
        String pdNm,
        String pdCd,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String adrNm,
        String ogCd,
        String ogNm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String istDt,
        String reqdDt,
        String bcNo,
        String sellTpCd,
        String sellTpNm,
        String svStpDt,
        //        String recapDutyPtrmN,
        String frisuBfsvcPtrmN,
        String frisuAsPtrmN,
        String cycleCode,
        String cycleNm,
        String bzrno
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }
}
